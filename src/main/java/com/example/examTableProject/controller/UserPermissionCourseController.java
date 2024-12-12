package com.example.examTableProject.controller;

import com.example.examTableProject.model.UserPermissionCourse;
import com.example.examTableProject.service.UserPermissionCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userPermissionCourse")
public class UserPermissionCourseController {

    @Autowired
    private UserPermissionCourseService userPermissionCourseService;

    @PostMapping("/add")
    public String add(@RequestBody UserPermissionCourse userPermissionCourse) {
        userPermissionCourseService.addUserPermissionCourse(userPermissionCourse);
        return "UserPermissionCourse saved";
    }
}
