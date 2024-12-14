package com.example.examTableProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserPermissionCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int courseId;

    public UserPermissionCourse() {
    }

    /**
     * Returns the ID of this UserPermissionCourse.
     *
     * @return the ID of this UserPermissionCourse
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of this UserPermissionCourse.
     *
     * @param id The ID to be set for this UserPermissionCourse
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the ID of the User associated with this UserPermissionCourse.
     *
     * @return the ID of the User associated with this UserPermissionCourse
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the User associated with this UserPermissionCourse.
     *
     * @param userId The ID of the User to be associated with this UserPermissionCourse
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the ID of the course associated with this UserPermissionCourse.
     *
     * @return the ID of the course
     */
    public int getCourseId() {
        return courseId;
    }
}

