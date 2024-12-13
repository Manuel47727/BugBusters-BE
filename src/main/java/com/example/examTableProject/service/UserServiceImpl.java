package com.example.examTableProject.service;

import com.example.examTableProject.model.User;
import com.example.examTableProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        validateUser(user);  // Chama o método de validação

        try {
            return userRepository.save(user);  // Salva o usuário no repositório
        } catch (Exception e) {
            logger.error("Error saving user: {}", e.getMessage());
            throw new RuntimeException("Failed to save user. Please try again later.");
        }
    }

    @Override
    public List<User> findAll() {
        try {
            return userRepository.findAll();  // Retorna a lista de usuários
        } catch (Exception e) {
            logger.error("Error fetching all users: {}", e.getMessage());
            throw new RuntimeException("Failed to fetch users. Please try again later.");
        }
    }

    @Override
    public User authenticate(String username, String password) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        try {
            User user = userRepository.findByUsernameAndPassword(username, password);
            if (user == null) {
                throw new IllegalArgumentException("Invalid username or password");
            }
            return user;  // Retorna o usuário encontrado
        } catch (Exception e) {
            logger.error("Error during authentication: {}", e.getMessage());
            throw new RuntimeException("Failed to authenticate. Please try again later.");
        }
    }

    /**
     * Valida os campos do usuário antes de salvar ou atualizar.
     *
     * @param user O objeto de usuário para validar.
     */
    private void validateUser(User user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        if (user.getRole() == null || user.getRole().trim().isEmpty()) {
            throw new IllegalArgumentException("Role cannot be empty");
        }
    }
}
