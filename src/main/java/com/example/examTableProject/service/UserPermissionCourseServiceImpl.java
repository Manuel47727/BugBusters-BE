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

    /**
     * Adds a new user permission course to the repository.
     *
     * @param userPermissionCourse The user permission course to be added.
     * @return The saved user permission course.
     * @throws IllegalArgumentException if the user already has permission for the course.
     */
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

    /**
     * Gets the course IDs associated with a given user.
     *
     * @param userId The ID of the user to retrieve courses for.
     * @return A list of course IDs the user has access to.
     * @throws IllegalArgumentException if the user ID is invalid.
     */
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

    /**
     * Validates the given user permission course.
     * <p>
     * The following constraints are checked:
     * <ul>
     *     <li>User ID must be greater than 0.</li>
     *     <li>Course ID must be greater than 0.</li>
     * </ul>
     * <p>
     * If any of these constraints are violated, an
     * {@link IllegalArgumentException} is thrown.
     *
     * @param userPermissionCourse The user permission course to be validated.
     */
    private void validateUserPermissionCourse(UserPermissionCourse userPermissionCourse) {
        if (userPermissionCourse.getUserId() <= 0) {
            throw new IllegalArgumentException("User ID must be greater than 0");
        }

        if (userPermissionCourse.getCourseId() <= 0) {
            throw new IllegalArgumentException("Course ID must be greater than 0");
        }
    }
}
