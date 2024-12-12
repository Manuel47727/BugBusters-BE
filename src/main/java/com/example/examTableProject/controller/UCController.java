package com.example.examTableProject.controller;

import com.example.examTableProject.model.UC;
import com.example.examTableProject.service.UCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/uc")
public class UCController {

    @Autowired
    private UCService ucService;

    @PostMapping("/add")
    public String add(@RequestBody UC uc) {
        ucService.addUC(uc);
        return "UC saved";
    }

    @PostMapping("/close")
    public ResponseEntity<String> close(@RequestParam int ucId) {
        try {
            // Check if the evaluation weights sum to 100% before closing the UC
            boolean isValid = ucService.validateEvaluationWeights(ucId);
            if (!isValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: Total weight of evaluations must be 100% to close the UC.");
            }

            // Proceed to close the UC if validation passes
            ucService.closeUC(ucId);
            return ResponseEntity.ok("UC successfully closed");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }


    @PostMapping("/open")
    public String unclose(@RequestParam int ucId) {
        ucService.openUC(ucId);
        return "UC opened";
    }

    @GetMapping("/isUCClosed")
    public boolean isUCClosed(@RequestParam int ucId) {
        return ucService.isUCClosed(ucId);
    }

    @GetMapping("/all")
    public List<UC> getAllUCs() {
        return ucService.getAllUCs();
    }

    @GetMapping("/getUC")
    public UC getUC(@RequestParam int ucId) {
        return ucService.getUC(ucId);
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editUC(@RequestParam int ucId, @RequestBody UC updatedUC) {
        try {
            // Fetch the existing UC
            System.out.println(ucId);
            UC existingUC = ucService.getUC(ucId);

            if (existingUC == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Error: UC with ID " + ucId + " not found.");
            }

            // Update the editable fields
            existingUC.setName(updatedUC.getName());
            existingUC.setSemestre(updatedUC.getSemestre());
            existingUC.setAno(updatedUC.getAno());
            existingUC.setTipo(updatedUC.getTipo());
            existingUC.setMandatory(updatedUC.isMandatory());

            // Save the updated UC
            ucService.updateUC(existingUC);
            return ResponseEntity.ok("UC updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/current")
    public List<UC> getUCsForCurrentSemesterAndCourse(@RequestParam int courseId) {
        return ucService.getUCsForCurrentSemesterAndCourse(courseId);
    }

    @DeleteMapping("/delete")
    public String deleteUC(@RequestParam int ucId) {
        ucService.deleteUC(ucId);
        return "UC and its evaluations deleted successfully";
    }
}

