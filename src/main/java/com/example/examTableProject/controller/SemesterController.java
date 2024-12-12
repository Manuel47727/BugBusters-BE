package com.example.examTableProject.controller;

import com.example.examTableProject.model.Semester;
import com.example.examTableProject.model.UC;
import com.example.examTableProject.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semester")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @PostMapping("/save")
    public String add(@RequestBody Semester semester) {
        semesterService.saveSemester(semester);
        return "Semester saved";
    }

    @GetMapping("/getAll")
    public List<Semester> getAllSemesters() {
        return semesterService.getAllSemesters();
    }

    @PutMapping("/edit")
    public ResponseEntity<String> editSemester(@RequestBody Semester semester) {
        try {
            Semester existingSemester = semesterService.getSemesterById(semester.getId());

            if (existingSemester == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Error: Semester with ID " + semester.getId() + " not found.");
            }

            // Update the editable fields
            existingSemester.setStartDate(semester.getStartDate());
            existingSemester.setEndDate(semester.getEndDate());

            // Save the updated semester
            semesterService.updateSemester(existingSemester);
            return ResponseEntity.ok("Semester updated successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: " + e.getMessage());
        }
    }
}
