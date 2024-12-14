package com.example.examTableProject.repository;

import com.example.examTableProject.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    /**
     * Finds all courses by their IDs.
     *
     * @param ids A list of IDs of courses to find.
     * @return A list of courses with the given IDs.
     */
    List<Course> findAllById(Iterable<Integer> ids);
}