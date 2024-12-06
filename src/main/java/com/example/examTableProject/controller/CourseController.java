package com.example.examTableProject.controller;

import com.example.examTableProject.model.Course;
import com.example.examTableProject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public String add(@RequestBody Course course) {
        courseService.addCourse(course);
        return "Course saved";
    }

    @GetMapping("/getAll")
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }
}
