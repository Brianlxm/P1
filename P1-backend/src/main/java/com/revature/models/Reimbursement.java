package com.revature.models;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import javax.annotation.processing.Generated;

@@Entity
@Table(name = "reimbursement")
@Component
public class Reimbursement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reimbID;

    private string description;

    private double amount;

    private string status;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private User user;

    //boilerplate code------------
    //constructors

    public Reimbursement() {
    }

    public Reimbursement(int reimbID, string description, double amount, string status, User user) {
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

    public string getDescription() {
        return description;
    }

    public void setDescription(string description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public string getStatus() {
        return status;
    }

    public void setStatus(string status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //to-string
    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimbID=" + reimbID +
                ", description=" + description +
                ", amount=" + amount +
                ", status=" + status +
                ", user=" + user +
                '}';
    }
}
