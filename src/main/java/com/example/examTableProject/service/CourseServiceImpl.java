package com.example.examTableProject.service;

import com.example.examTableProject.model.Course;
import com.example.examTableProject.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Add a new course to the database.
     *
     * @param course The course to add.
     * @return The saved course.
     * @throws IllegalArgumentException if the course name is empty
     * @throws RuntimeException if there is an unexpected error
     */
    @Transactional
    public Course addCourse(Course course) {
        if (course.getCourseName() == null || course.getCourseName().trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be empty");
        }
        try {
            return courseRepository.save(course);
        } catch (Exception e) {
            logger.error("Error saving course: {}", e.getMessage());
            throw new RuntimeException("Failed to save course. Please try again later.");
        }
    }


    /**
     * Retrieve all courses from the database.
     *
     * @return A list of all courses. The list is empty if there are no courses.
     * @throws RuntimeException if there is an unexpected error
     */
    @Override
    public List<Course> getAllCourses() {
        try {
            return courseRepository.findAll(); // Retorna todos os cursos
        } catch (Exception e) {
            logger.error("Error fetching all courses: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch courses. Please try again later.");
        }
    }

    /**
     * Retrieve a list of courses by their IDs.
     *
     * @param courseIds List of course IDs to retrieve.
     * @return A list of courses corresponding to the provided IDs. The list is empty if no courses are found.
     * @throws RuntimeException if there is an unexpected error fetching the courses.
     */
    @Override
    public List<Course> getCoursesByIds(List<Integer> courseIds) {
        try {
            return courseRepository.findAllById(courseIds); // Retorna os cursos pelos IDs
        } catch (Exception e) {
            logger.error("Error fetching courses by IDs: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch courses by IDs. Please try again later.");
        }
    }
}
