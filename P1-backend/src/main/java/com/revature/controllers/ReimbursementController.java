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

        //TODO: try/catch error handling
        Reimbursement r = reimbursementService.addReimbursement(reimbursementDTO);

        return ResponseEntity.status(201).body(r.getUser().getUsername() + " created a new reimbursement");
    }
}
