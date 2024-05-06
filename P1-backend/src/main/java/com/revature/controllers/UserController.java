package com.revature.controllers;

import com.revature.daos.UserDAO;
import com.revature.models.DTOs.ListUserDTO;
import com.revature.models.DTOs.LoginUserDTO;
import com.revature.models.DTOs.OutgoingUserDTO;
import com.revature.models.DTOs.RegisterUserDTO;
import com.revature.models.User;
import com.revature.services.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    private UserService userService;
    private UserDAO userDAO;

    @Autowired
    public UserController(UserService userService, UserDAO userDAO) {
        this.userService = userService;
        this.userDAO = userDAO;
    }

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody RegisterUserDTO userDTO){
        try{
            userService.registerUser(userDTO);
            return ResponseEntity.status(201).body(userDTO.getUsername() + " was created!");
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginUserDTO userDTO, HttpSession session){
        //get user object from the service
        try {
            Optional<User> optionalUser = userService.loginUser(userDTO);

            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(401).body("Login Failed!");
            }

            //if login succeeds store the userinfo in our session
            User u = optionalUser.get();

            //store the user info in our session
            session.setAttribute("userId", u.getUserId());
            session.setAttribute("username", u.getUsername());
            session.setAttribute("role", u.getRole());

            return ResponseEntity.ok(new OutgoingUserDTO(u.getUserId(), u.getUsername(), u.getRole()));
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    //delete user by Id
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int userId){

        //TODO: take in HttpSession to do the necessary checks

        try{
            userService.deleteUser(userId);
            return ResponseEntity.ok("User was deleted");
        }catch (Exception e){
            return ResponseEntity.badRequest().body("Managers cannot be deleted");
        }
    }

    //get all users
    @GetMapping
    public ResponseEntity<?> getAllUsers(HttpSession session) {
        //login check
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("User not logged in!");
        }
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{userId}")
    public ResponseEntity<String> updateUserRole(@PathVariable int userId, HttpSession session){

        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("User not logged in!");
        }
        //TODO: get user info from userId not sessiondata
        User u = userDAO.findById(userId).get();

        String username = u.getUsername();
        String role = u.getRole();
        userService.updateUserRole(userId, role);
        if (role.equals("employee")){
            return ResponseEntity.status(200).body(username + " was promoted to manager");
        }else{
            return ResponseEntity.status(200).body(username + " was demoted to employee");
        }

    }
}
