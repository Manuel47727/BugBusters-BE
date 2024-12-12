package com.example.examTableProject.service;

import com.example.examTableProject.model.UserPermissionCourse;
import com.example.examTableProject.repository.UserPermissionCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPermissionCourseServiceImpl implements UserPermissionCourseService {

    @Autowired
    private UserPermissionCourseRepository userPermissionCourseRepository;

    @Override
    public UserPermissionCourse addUserPermissionCourse(UserPermissionCourse userPermissionCourse) {
        return userPermissionCourseRepository.save(userPermissionCourse);
    }
}
