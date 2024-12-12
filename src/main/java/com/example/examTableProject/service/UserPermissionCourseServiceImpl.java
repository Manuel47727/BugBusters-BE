package com.example.examTableProject.service;

import com.example.examTableProject.model.UserPermissionCourse;
import com.example.examTableProject.repository.UserPermissionCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserPermissionCourseServiceImpl implements UserPermissionCourseService {

    @Autowired
    private UserPermissionCourseRepository userPermissionCourseRepository;

    @Override
    public UserPermissionCourse addUserPermissionCourse(UserPermissionCourse userPermissionCourse) {
        return userPermissionCourseRepository.save(userPermissionCourse);
    }

    @Override
    public List<Integer> getCourseIdsForUser(int userId) {
        return userPermissionCourseRepository.findAll()
                .stream()
                .filter(entry -> entry.getUserId() == userId)
                .map(entry -> entry.getCourseId())
                .collect(Collectors.toList());
    }
}
