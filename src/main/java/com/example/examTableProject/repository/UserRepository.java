package com.example.examTableProject.repository;

import com.example.examTableProject.model.UC;
import com.example.examTableProject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Finds a user by their username and password.
     *
     * @param username the username
     * @param password the password
     * @return the user object if found, or null if not
     */
    User findByUsernameAndPassword(String username, String password);
}
