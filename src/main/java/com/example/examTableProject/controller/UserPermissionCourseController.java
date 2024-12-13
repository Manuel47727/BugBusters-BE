package com.example.examTableProject.controller;

import com.example.examTableProject.model.Course;
import com.example.examTableProject.model.UserPermissionCourse;
import com.example.examTableProject.service.CourseService;
import com.example.examTableProject.service.UserPermissionCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userPermissionCourse")
public class UserPermissionCourseController {

    @Autowired
    private UserPermissionCourseService userPermissionCourseService;

    @Autowired
    private CourseService courseService;

    /**
     * Add a new UserPermissionCourse.
     *
     * @param userPermissionCourse The UserPermissionCourse to be added.
     * @return Response message indicating success or failure.
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody UserPermissionCourse userPermissionCourse) {
        if (userPermissionCourse.getUserId() <= 0 || userPermissionCourse.getCourseId() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\":\"Invalid user or course ID.\"}");
        }

        try {
            userPermissionCourseService.addUserPermissionCourse(userPermissionCourse);
            return ResponseEntity.ok("{\"message\":\"UserPermissionCourse saved successfully.\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error saving UserPermissionCourse: " + e.getMessage() + "\"}");
        }
    }

    /**
     * Get the list of courses for a specific user.
     *
     * @param userId The ID of the user.
     * @return List of courses the user has access to.
     */
    @GetMapping("/getCourses/{userId}")
    public ResponseEntity<?> getCoursesForUser(@PathVariable int userId) {
        if (userId <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"message\":\"Invalid user ID.\"}");
        }

        try {
            List<Integer> courseIds = userPermissionCourseService.getCourseIdsForUser(userId);

            if (courseIds.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("{\"message\":\"No courses found for user with ID " + userId + "\"}");
            }

            List<Course> courses = courseService.getCoursesByIds(courseIds);
            return ResponseEntity.ok(courses);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Error retrieving courses for user: " + e.getMessage() + "\"}");
        }
    }
}
