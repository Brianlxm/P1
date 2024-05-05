package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.DTOs.OutgoingReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReimbursementService {

    private UserDAO userDAO;
    private ReimbursementDAO reimbursementDAO;

    @Autowired
    public ReimbursementService(UserDAO userDAO, ReimbursementDAO reimbursementDAO) {
        this.userDAO = userDAO;
        this.reimbursementDAO = reimbursementDAO;
    }

    //add reimbursement
    public Reimbursement addReimbursement(IncomingReimbursementDTO reimbursementDTO){

        //checks
        Reimbursement r = new Reimbursement(reimbursementDTO.getDescription(), reimbursementDTO.getAmount(), reimbursementDTO.getStatus(), null);

        //instantiate appropriate user
        User u = userDAO.findById(reimbursementDTO.getUserId()).get();

        //set user
        r.setUser(u);

        //save new reimbursement
        return reimbursementDAO.save(r);
    }

    //get all reimbursements
    public List<OutgoingReimbursementDTO> getAllReimbursements(int userId){

        //list to hold return
        List<OutgoingReimbursementDTO> outReimbursement = new ArrayList<>();

        //list to hold all from db
        List<Reimbursement> allReimbursements;

        //check if manager or employee
        User u = userDAO.findById(userId).get();
        if(u.getRole().equals("manager")){
            //manager gets all
            //get all reimbursements from db
            allReimbursements = reimbursementDAO.findAll();
        } else {
            allReimbursements = reimbursementDAO.findByUserUserId(userId);
        }
        for(Reimbursement r : allReimbursements){
            OutgoingReimbursementDTO outR = new OutgoingReimbursementDTO(
                    r.getReimbID(),
                    r.getDescription(),
                    r.getAmount(),
                    r.getStatus(),
                    r.getUser().getUserId());

            outReimbursement.add(outR);
        }
        return outReimbursement;
    }
}
