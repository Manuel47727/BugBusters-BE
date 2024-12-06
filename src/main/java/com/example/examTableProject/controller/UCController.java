package com.example.examTableProject.controller;

import com.example.examTableProject.model.UC;
import com.example.examTableProject.service.UCService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/all")
    public List<UC> getAllUCs() {
        return ucService.getAllUCs();
    }

    @GetMapping("/current")
    public List<UC> getUCsForCurrentSemesterAndCourse(@RequestParam int courseId) {
        return ucService.getUCsForCurrentSemesterAndCourse(courseId);
    }
}
