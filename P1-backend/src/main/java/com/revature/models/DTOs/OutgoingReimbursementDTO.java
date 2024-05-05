package com.revature.models.DTOs;

public class OutgoingReimbursementDTO {

    private int reimbID;

    private String description;

    private double amount;

    private String status;

    private int userId;

    private String username;

    //boilerplate ---------
    public OutgoingReimbursementDTO() {
    }

    public OutgoingReimbursementDTO(int reimbID, String description, double amount, String status, int userId, String username) {
        this.reimbID = reimbID;
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
        this.username = username;
    }

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "OutgoingReimbursementDTO{" +
                "reimbID=" + reimbID +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
