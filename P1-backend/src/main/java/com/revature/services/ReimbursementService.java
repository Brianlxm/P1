package com.revature.services;

import com.revature.daos.ReimbursementDAO;
import com.revature.daos.UserDAO;
import com.revature.models.DTOs.IncomingReimbursementDTO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
