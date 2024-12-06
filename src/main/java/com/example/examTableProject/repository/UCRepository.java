package com.example.examTableProject.repository;

import com.example.examTableProject.model.UC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UCRepository extends JpaRepository<UC, Integer> {
}