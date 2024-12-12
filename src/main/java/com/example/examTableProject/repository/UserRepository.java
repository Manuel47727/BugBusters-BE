package com.example.examTableProject.repository;

import com.example.examTableProject.model.UC;
import com.example.examTableProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
