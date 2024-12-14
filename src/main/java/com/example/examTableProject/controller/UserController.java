package com.example.examTableProject.controller;

import com.example.examTableProject.model.User;
import com.example.examTableProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Saves a new user in the database.
     * @param user a User object to be saved
     * @return a ResponseEntity containing the result of the operation
     * @throws IllegalArgumentException if the user object is invalid
     * @throws RuntimeException if an unexpected error occurs
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody User user) {
        try {
            userService.addUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User saved successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/all")
    public List<User> findAll() {
    /**
     * Finds all users in the database.
     * @return a list of User objects representing all users in the database
     */
        return userService.findAll();
    }

    /**
     * Authenticates a user by username and password.
     * @param userObject a User object with the username and password to be authenticated
     * @return a ResponseEntity containing the authenticated user if successful, or an error message if not
     * @throws IllegalArgumentException if the user object is invalid
     * @throws RuntimeException if an unexpected error occurs
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User userObject) {
        try {
            User user = userService.authenticate(userObject.getUsername(), userObject.getPassword());
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to authenticate. Please try again later.");
        }
    }
}
