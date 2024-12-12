package com.example.examTableProject.controller;

import com.example.examTableProject.model.Course;
import com.example.examTableProject.model.UserPermissionCourse;
import com.example.examTableProject.service.CourseService;
import com.example.examTableProject.service.UserPermissionCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userPermissionCourse")
public class UserPermissionCourseController {

    @Autowired
    private UserPermissionCourseService userPermissionCourseService;

    @Autowired
    private CourseService courseService;

    @PostMapping("/add")
    public String add(@RequestBody UserPermissionCourse userPermissionCourse) {
        userPermissionCourseService.addUserPermissionCourse(userPermissionCourse);
        return "UserPermissionCourse saved";
    }

    @GetMapping("/getCourses/{userId}")
    public List<Course> getCoursesForUser(@PathVariable int userId) {
        // Get the list of course IDs the user has access to
        List<Integer> courseIds = userPermissionCourseService.getCourseIdsForUser(userId);

        // Fetch the course details for these course IDs
        return courseService.getCoursesByIds(courseIds);
    }
}
