package com.example.examTableProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String courseName;

    public Course() {
    }

    /**
     * Returns the id of the course
     *
     * @return the id of the course
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    /**
     * Sets the id of the course.
     *
     * @param id the id to set for the course
     */
    }

    /**
     * Returns the name of the course.
     *
     * @return the name of the course
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Sets the name of the course.
     *
     * @param courseName the name to set for the course
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
