package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.DTOs.RegisterUserDTO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //register user
    public User registerUser(RegisterUserDTO userDTO) throws IllegalArgumentException{
        // check the username, password, firstname, lastname, role not empty/null
        if(userDTO.getFirstName().isBlank() || userDTO.getFirstName() == null){
            throw new IllegalArgumentException("First name cannot be empty");
        }
        if(userDTO.getLastName().isBlank() || userDTO.getLastName() == null){
            throw new IllegalArgumentException("Last name cannot be empty");
        }
        if(userDTO.getUsername().isBlank() || userDTO.getUsername() == null){
            throw new IllegalArgumentException("Username cannot be empty");
        }
        if(userDTO.getPassword().isBlank() || userDTO.getPassword() == null){
            throw new IllegalArgumentException("Password cannot be empty");
        }
        try{
            User newUser = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole());
            return userDAO.save(newUser);
        } catch (DataIntegrityViolationException e){
            throw new IllegalArgumentException("Username already taken! Try a different one");
        }
    }
}
