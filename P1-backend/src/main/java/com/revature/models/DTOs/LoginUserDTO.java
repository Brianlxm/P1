package com.revature.models.DTOs;

//DTO for logins
//only need username and password

public class LoginUserDTO {

    private String username;

    private String password;

    //boilerplate code
    //constructors

    public LoginUserDTO() {
    }

    public LoginUserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //toString
    
    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
