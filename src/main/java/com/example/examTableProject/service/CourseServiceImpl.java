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


    @Override
    public List<Course> getAllCourses() {
        try {
            return courseRepository.findAll(); // Retorna todos os cursos
        } catch (Exception e) {
            logger.error("Error fetching all courses: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch courses. Please try again later.");
        }
    }

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
