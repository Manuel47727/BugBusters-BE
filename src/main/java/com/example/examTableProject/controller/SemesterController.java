package com.example.examTableProject.controller;

import com.example.examTableProject.model.Semester;
import com.example.examTableProject.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
