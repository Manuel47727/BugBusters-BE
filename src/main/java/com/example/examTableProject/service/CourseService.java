package com.example.examTableProject.service;

import com.example.examTableProject.model.Course;
import java.util.List;

public interface CourseService {

    public Course addCourse(Course course);
    public List<Course> getAllCourses();
    public List<Course> getCoursesByIds(List<Integer> courseIds);
}
