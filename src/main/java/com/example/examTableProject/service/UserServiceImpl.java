package com.example.examTableProject.service;

import com.example.examTableProject.model.User;
import com.example.examTableProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User authenticate(String username, String password) {
        // In a real application, you should use proper password hashing
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
