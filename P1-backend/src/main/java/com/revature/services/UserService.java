package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.DTOs.ListUserDTO;
import com.revature.models.DTOs.LoginUserDTO;
import com.revature.models.DTOs.RegisterUserDTO;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        User existingUser = userDAO.findByUsername(userDTO.getUsername()).orElse(null);

        if(existingUser != null){
            throw new IllegalArgumentException("Username already exists!");
        }

        User newUser = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUsername(), userDTO.getPassword(), userDTO.getRole());
        return userDAO.save(newUser);

    }

    public Optional<User> loginUser(LoginUserDTO userDTO) throws IllegalArgumentException {
        //TODO: validity checks
        if (userDAO.findByUsername(userDTO.getUsername()).isEmpty()){
            throw new IllegalArgumentException("Username does not exist");
        }

        //check username exists, check if password matches the username
        User existingUser = userDAO.findByUsername(userDTO.getUsername()).get();
        if (existingUser.getPassword().equals(userDTO.getPassword())){
            return userDAO.findByUsername(userDTO.getUsername());
        } else {
            throw new IllegalArgumentException("Username and password don't match");
        }
        //if all checks pass, return a User OR null, and send it to the controller


    }

    //delete user
    public void deleteUser(int userId) throws IllegalArgumentException{

        //TODO: validity checks
        //make sure user to delete exists
        //make sure deleter is not themselves
        //TODO: make sure user is not a manager
        User u = userDAO.findById(userId).get();
        if(u.getRole().equals("manager")){
            throw new IllegalArgumentException("Managers cannot be deleted");
        }

        userDAO.deleteById(userId);
    }

    //get all users
    public List<ListUserDTO> getAllUsers(){

        //use DTO to not send password
        List<ListUserDTO> listUsers = new ArrayList<>();

        List<User> users = userDAO.findAll();

        for(User u : users) {
            ListUserDTO userDTO = new ListUserDTO(
                    u.getUserId(),
                    u.getUsername(),
                    u.getFirstName(),
                    u.getLastName(),
                    u.getRole());

            listUsers.add(userDTO);
        }
        return listUsers;
    }

    //updateUserRole
    public User updateUserRole(int userId, String role){

        User u = userDAO.findById(userId).get();

        u.setRole(role);

        return userDAO.save(u);
    }
}
