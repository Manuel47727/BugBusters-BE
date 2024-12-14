package com.example.examTableProject.service;

import com.example.examTableProject.model.UserPermissionCourse;

import java.util.List;

public interface UserPermissionCourseService {

    /**
     * Adds a new UserPermissionCourse to the repository.
     *
     * @param userPermissionCourse The UserPermissionCourse object to be added.
     * @return The saved UserPermissionCourse object.
     * @throws IllegalArgumentException if the userPermissionCourse is invalid or
     *         if the user already has permission for the specified course.
     */
    public UserPermissionCourse addUserPermissionCourse(UserPermissionCourse userPermissionCourse);

    /**
     * Retrieves the IDs of courses a user has access to.
     *
     * @param userId The ID of the user to retrieve the courses for.
     * @return A list of course IDs that the user has access to.
     * @throws IllegalArgumentException if the user ID is less than 1.
     */
    public List<Integer> getCourseIdsForUser(int userId);
}
