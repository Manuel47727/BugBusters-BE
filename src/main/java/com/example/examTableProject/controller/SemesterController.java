package com.example.examTableProject.controller;

import com.example.examTableProject.model.Semester;
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

    /**
     * Save a new semester.
     *
     * @param semester The semester to be saved.
     * @return Response message indicating success or failure.
     */
    @PostMapping("/save")
    public ResponseEntity<String> add(@RequestBody Semester semester) {
        if (semester.getNumSemester() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Semester number must be positive.\"}");
        }
        if (semester.getStartDate() == null || semester.getEndDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Start and end dates must be provided.\"}");
        }
        if (semester.getStartDate().isAfter(semester.getEndDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Start date cannot be after end date.\"}");
        }

        try {
            semesterService.saveSemester(semester);
            return ResponseEntity.ok("{\"message\":\"Semester saved successfully\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error saving semester: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Get all semesters.
     *
     * @return List of all semesters.
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Semester>> getAllSemesters() {
        try {
            List<Semester> semesters = semesterService.getAllSemesters();
            return ResponseEntity.ok(semesters);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    /**
     * Edit an existing semester.
     *
     * @param semester The semester with updated information.
     * @return Response message indicating success or failure.
     */
    @PutMapping("/edit")
    public ResponseEntity<String> editSemester(@RequestBody Semester semester) {
        if (semester.getId() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Invalid semester ID.\"}");
        }
        if (semester.getStartDate() == null || semester.getEndDate() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Start and end dates must be provided.\"}");
        }
        if (semester.getStartDate().isAfter(semester.getEndDate())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"message\":\"Start date cannot be after end date.\"}");
        }

        try {
            Semester existingSemester = semesterService.getSemesterById(semester.getId());

            if (existingSemester == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"message\":\"Semester with ID " + semester.getId() + " not found.\"}");
            }

            existingSemester.setStartDate(semester.getStartDate());
            existingSemester.setEndDate(semester.getEndDate());

            semesterService.updateSemester(existingSemester);
            return ResponseEntity.ok("{\"message\":\"Semester updated successfully!\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error updating semester: " + e.getMessage() + "\"}");
        }
    }
}
