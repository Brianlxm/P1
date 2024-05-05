package com.revature.controllers;


import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;
import jakarta.servlet.http.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reimbursements")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ReimbursementController {

    private ReimbursementService reimbursementService;

    @Autowired
    public ReimbursementController(ReimbursementService reimbursementService) {
        this.reimbursementService = reimbursementService;
    }

    //post mapping for inserting new reimbursement
    @PostMapping
    public ResponseEntity<String> addReimbursement(@RequestBody IncomingReimbursementDTO reimbursementDTO, HttpSession session){

        //if user is not logged in, send error
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("User not logged in!");
        }

        //attach stored userId to the reimbursementDTO
        reimbursementDTO.setUserId((int) session.getAttribute("userId"));

        //attach stored username
        reimbursementDTO.setUsername((String) session.getAttribute("username"));

        //TODO: try/catch error handling
        Reimbursement r = reimbursementService.addReimbursement(reimbursementDTO);

        return ResponseEntity.status(201).body(r.getUser().getUsername() + " submitted a new reimbursement");
    }

    //get all reimbursements
    @GetMapping
    public ResponseEntity<?> getAllReimbursements(HttpSession session){

        //login check
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("User not logged in!");
        }

        //get userId
        int userId = (int) session.getAttribute("userId");

        return ResponseEntity.ok(reimbursementService.getAllReimbursements(userId));

    }

    //get reimbursements by status
    @GetMapping("/{status}")
    public ResponseEntity<?> getReimbursementsByStatus(@PathVariable String status, HttpSession session){
        //login check
        if(session.getAttribute("userId") == null){
            return ResponseEntity.status(401).body("User not logged in!");
        }

        //get userId
        int userId = (int) session.getAttribute("userId");

        return ResponseEntity.ok(reimbursementService.getReimbursementsByStatus(userId, status));

    }

    //delete reimbursement
//    @DeleteMapping("/{reimbursementId")
//    public ResponseEntity<String> deleteReimbursement(@PathVariable int reimbursementId, HttpSession session){
//        //login chack
//        if(session.getAttribute("userId") == null){
//            return ResponseEntity.status(401).body("User not logged in");
//        }
//        //get userId from session
//        int userId = (int) session.getAttribute("userId");
//        try {
//            return ResponseEntity.ok(reimbursementService.deleteReimbursements(reimbursementId, userId));
//        } catch (Exception e){
//            return ResponseEntity.status(400).body(e.getMessage());
//        }
//    }
}
