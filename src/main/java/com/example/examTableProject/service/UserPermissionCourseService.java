package com.example.examTableProject.service;

import com.example.examTableProject.model.UserPermissionCourse;

import java.util.List;

public interface UserPermissionCourseService {

    public UserPermissionCourse addUserPermissionCourse(UserPermissionCourse userPermissionCourse);

    public List<Integer> getCourseIdsForUser(int userId);
}
