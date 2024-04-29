package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "user")
@Component
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column(nullable = false)
    private string firstName;

    @Column(nullable = false)
    private string lastName;

    @Column(nullable = false, unique = true)
    private string username;

    @Column(nullable = false)
    private string password;

    @Column(nullable = false)
    private string role;

    //boilerplate code ----------------------------
    //no-arg constructor
    public User() {
    }

    //all arg constructor
    public User(int userId, string firstName, string lastName, string username, string password, string role) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    //getters and setters

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public string getFirstName() {
        return firstName;
    }

    public void setFirstName(string firstName) {
        this.firstName = firstName;
    }

    public string getLastName() {
        return lastName;
    }

    public void setLastName(string lastName) {
        this.lastName = lastName;
    }

    public string getUsername() {
        return username;
    }

    public void setUsername(string username) {
        this.username = username;
    }

    public string getPassword() {
        return password;
    }

    public void setPassword(string password) {
        this.password = password;
    }

    public string getRole() {
        return role;
    }

    public void setRole(string role) {
        this.role = role;
    }

    //to-string
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName=" + firstName +
                ", lastName=" + lastName +
                ", username=" + username +
                ", password=" + password +
                ", role=" + role +
                '}';
    }
}
