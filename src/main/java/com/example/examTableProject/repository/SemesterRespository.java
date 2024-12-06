package com.example.examTableProject.repository;

import com.example.examTableProject.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRespository extends JpaRepository<Semester, Integer> {
}
