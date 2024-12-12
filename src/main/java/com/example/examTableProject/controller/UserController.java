package com.example.examTableProject.controller;

import com.example.examTableProject.model.User;
import com.example.examTableProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String add(@RequestBody User user) {
        userService.addUser(user);
        return "User saved";
    }

    @GetMapping("/all")
    public List<User> findAll() {
        return userService.findAll();
    }


}
