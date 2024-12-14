package com.example.examTableProject.service;

import com.example.examTableProject.model.Course;
import java.util.List;

public interface CourseService {

    /**
     * Adds a new course to the repository.
     *
     * @param course The course to be added.
     * @return The saved course object.
     * @throws IllegalArgumentException if the course name is null or empty.
     * @throws RuntimeException if there is an error during the save operation.
     */
    public Course addCourse(Course course);
    /**
     * Retrieves all courses from the repository.
     *
     * @return A list of all courses in the repository.
     * @throws RuntimeException if there is an error during the retrieval operation.
     */
    public List<Course> getAllCourses();
    /**
     * Retrieves courses from the repository by IDs.
     *
     * @param courseIds A list of course IDs to retrieve.
     * @return A list of courses with the specified IDs.
     * @throws RuntimeException if there is an error during the retrieval operation.
     */
    public List<Course> getCoursesByIds(List<Integer> courseIds);
}
