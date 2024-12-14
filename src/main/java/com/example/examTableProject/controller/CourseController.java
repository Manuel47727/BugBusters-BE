package com.example.examTableProject.controller;

import com.example.examTableProject.model.Course;
import com.example.examTableProject.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

/**
 * Add a new course.
 *
 * @param course The course to be added.
 * @return Response message indicating success or failure.
 */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody Course course) {
        try {
            courseService.addCourse(course);
            return ResponseEntity.status(HttpStatus.CREATED).body("Course saved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save course. Please try again later.");
        }
    }

    /**
     * Get all courses.
     *
     * @return ResponseEntity containing a list of all courses.
     *         On success, returns HTTP status 200 (OK) with the list of courses.
     *         On error, returns HTTP status 500 (Internal Server Error) with a null body.
     */
    @GetMapping("/getAll")
    public ResponseEntity<List<Course>> getAllCourses() {
        try {
            List<Course> courses = courseService.getAllCourses();
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
