package com.example.examTableProject.service;

import com.example.examTableProject.model.User;

import java.util.List;

public interface UserService {
    public User addUser(User user);
    public List<User> findAll();
    public User authenticate(String username, String password);
}
