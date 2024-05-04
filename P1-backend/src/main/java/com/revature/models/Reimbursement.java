package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@Entity
@Table(name = "reimbursement")
@Component
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbID;

    private String description;

    private double amount;

    private String status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    //boilerplate code------------
    //constructors

    public Reimbursement() {
    }

    public Reimbursement(String description, double amount, String status, User user) {
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }

    public Reimbursement(int reimbID, String description, double amount, String status, User user) {
        this.reimbID = reimbID;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.user = user;
    }
//getter/setters
    public int getReimbID() {
        return reimbID;
    }

    public void setReimbID(int reimbID) {
        this.reimbID = reimbID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbID=" + reimbID +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", user=" + user +
                '}';
    }
}
