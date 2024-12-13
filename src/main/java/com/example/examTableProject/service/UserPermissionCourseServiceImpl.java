package com.example.examTableProject.service;

import com.example.examTableProject.model.UserPermissionCourse;
import com.example.examTableProject.repository.UserPermissionCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserPermissionCourseServiceImpl implements UserPermissionCourseService {

    @Autowired
    private UserPermissionCourseRepository userPermissionCourseRepository;

    @Override
    public UserPermissionCourse addUserPermissionCourse(UserPermissionCourse userPermissionCourse) {
        validateUserPermissionCourse(userPermissionCourse);

        Optional<UserPermissionCourse> existingPermission = userPermissionCourseRepository
                .findAll().stream()
                .filter(entry -> entry.getUserId() == userPermissionCourse.getUserId()
                        && entry.getCourseId() == userPermissionCourse.getCourseId())
                .findFirst();

        if (existingPermission.isPresent()) {
            throw new IllegalArgumentException("User already has permission for this course.");
        }

        return userPermissionCourseRepository.save(userPermissionCourse);
    }

    @Override
    public List<Integer> getCourseIdsForUser(int userId) {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be greater than 0");
        }

        return userPermissionCourseRepository.findAll()
                .stream()
                .filter(entry -> entry.getUserId() == userId)
                .map(entry -> entry.getCourseId())
                .collect(Collectors.toList());
    }

    private void validateUserPermissionCourse(UserPermissionCourse userPermissionCourse) {
        if (userPermissionCourse.getUserId() <= 0) {
            throw new IllegalArgumentException("User ID must be greater than 0");
        }

        if (userPermissionCourse.getCourseId() <= 0) {
            throw new IllegalArgumentException("Course ID must be greater than 0");
        }
    }
}
