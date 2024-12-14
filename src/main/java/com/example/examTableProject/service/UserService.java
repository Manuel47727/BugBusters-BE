package com.example.examTableProject.service;

import com.example.examTableProject.model.User;

import java.util.List;

public interface UserService {
    /**
     * Adds a new user to the database.
     *
     * @param user The user to be added.
     * @return The newly added user.
     * @throws IllegalArgumentException if the user object is invalid.
     * @throws RuntimeException if an unexpected error occurs.
     */
    public User addUser(User user);
    /**
     * Retrieves all users from the database.
     *
     * @return A list of all users in the database.
     */
    public List<User> findAll();
    /**
     * Authenticates a user by username and password.
     *
     * @param username The username to authenticate.
     * @param password The password to authenticate.
     * @return The authenticated user if authentication is successful, or throws an exception if not.
     * @throws IllegalArgumentException if the username or password is invalid.
     * @throws RuntimeException if an unexpected error occurs.
     */
    public User authenticate(String username, String password);
}
