package com.example.examTableProject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String name;
    private String email;
    private String password;
    private String role;

    public User() {
    }

    /**
     * Retrieves the ID of the user.
     *
     * @return the ID of the user
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id the new ID for the user
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retrieves the username of the user.
     *
     * @return the username of the user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username the new username for the user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name the new name for the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the email of the user.
     *
     * @return the email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the new email address for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the password of the user.
     *
     * <p>This method will return the hashed password of the user. Therefore, it is
     * not suitable for login purposes.
     *
     * @return the hashed password of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     *
     * @param password the new password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }


    /**
     * Retrieves the role of the user.
     *
     * @return the role of the user
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     *
     * @param role the new role for the user
     */
    public void setRole(String role) {
        this.role = role;
    }
}
