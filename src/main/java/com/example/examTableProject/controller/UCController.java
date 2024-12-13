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

    /**
     * Add a new UC.
     *
     * @param uc The UC to be added.
     * @return Response message indicating success or failure.
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UC uc) {
        if (uc.getName() == null || uc.getName().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\":\"UC name is required.\"}");
        }
        if (uc.getCourseId() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\":\"Invalid Course ID.\"}");
        }

        try {
            ucService.addUC(uc);
            return ResponseEntity.ok("{\"message\":\"UC saved successfully.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error saving UC: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Close a UC after validating evaluation weights.
     *
     * @param ucId The ID of the UC to be closed.
     * @return Response message indicating success or failure.
     */
    @PostMapping("/close")
    public ResponseEntity<String> close(@RequestParam int ucId) {
        try {
            boolean isValid = ucService.validateEvaluationWeights(ucId);
            if (!isValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"message\":\"Total weight of evaluations must be 100% to close the UC.\"}");
            }

            ucService.closeUC(ucId);
            return ResponseEntity.ok("{\"message\":\"UC successfully closed.\"}");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error closing UC: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Open a UC that was previously closed.
     *
     * @param ucId The ID of the UC to be opened.
     * @return Response message indicating success.
     */
    @PostMapping("/open")
    public ResponseEntity<String> unclose(@RequestParam int ucId) {
        try {
            ucService.openUC(ucId);
            return ResponseEntity.ok("{\"message\":\"UC opened successfully.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error opening UC: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Check if a UC is closed.
     *
     * @param ucId The ID of the UC.
     * @return Whether the UC is closed.
     */
    @GetMapping("/isUCClosed")
    public ResponseEntity<Boolean> isUCClosed(@RequestParam int ucId) {
        try {
            boolean isClosed = ucService.isUCClosed(ucId);
            return ResponseEntity.ok(isClosed);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Get all UCs.
     *
     * @return A list of all UCs.
     */
    @GetMapping("/all")
    public ResponseEntity<List<UC>> getAllUCs() {
        try {
            List<UC> ucs = ucService.getAllUCs();
            return ResponseEntity.ok(ucs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Get a specific UC by its ID.
     *
     * @param ucId The ID of the UC.
     * @return The UC information.
     */
    @GetMapping("/getUC")
    public ResponseEntity<UC> getUC(@RequestParam int ucId) {
        try {
            UC uc = ucService.getUC(ucId);
            if (uc == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
            return ResponseEntity.ok(uc);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Edit an existing UC.
     *
     * @param ucId The ID of the UC to be updated.
     * @param updatedUC The updated UC data.
     * @return Response message indicating success or failure.
     */
    @PutMapping("/edit")
    public ResponseEntity<String> editUC(@RequestParam int ucId, @RequestBody UC updatedUC) {
        try {
            UC existingUC = ucService.getUC(ucId);

            if (existingUC == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"message\":\"Error: UC with ID " + ucId + " not found.\"}");
            }

            existingUC.setName(updatedUC.getName());
            existingUC.setSemestre(updatedUC.getSemestre());
            existingUC.setAno(updatedUC.getAno());
            existingUC.setTipo(updatedUC.getTipo());
            existingUC.setMandatory(updatedUC.isMandatory());

            ucService.updateUC(existingUC);
            return ResponseEntity.ok("{\"message\":\"UC updated successfully!\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error updating UC: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Get UCs for the current semester and course.
     *
     * @param courseId The ID of the course.
     * @return A list of UCs for the current semester and course.
     */
    @GetMapping("/current")
    public ResponseEntity<List<UC>> getUCsForCurrentSemesterAndCourse(@RequestParam int courseId) {
        try {
            List<UC> ucs = ucService.getUCsForCurrentSemesterAndCourse(courseId);
            return ResponseEntity.ok(ucs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Delete a UC and its evaluations.
     *
     * @param ucId The ID of the UC to be deleted.
     * @return Response message indicating success.
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUC(@RequestParam int ucId) {
        try {
            ucService.deleteUC(ucId);
            return ResponseEntity.ok("{\"message\":\"UC and its evaluations deleted successfully.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error deleting UC: " + e.getMessage() + "\"}");
        }
    }
}
