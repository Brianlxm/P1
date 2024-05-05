package com.revature.models.DTOs;

//DTO for incoming reimbursements
//user created new reimbursement
//description, amount, status, userId, status (automatically pending from frontend)
public class IncomingReimbursementDTO {

    private String description;

    private double amount;

    private String status;

    private int userId;

    private String username;

    //boilerplate --------

    public IncomingReimbursementDTO() {
    }

    public IncomingReimbursementDTO(String description, double amount, String status, int userId, String username) {
        this.description = description;
        this.amount = amount;
        this.status = status;
        this.userId = userId;
        this.username = username;
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
//toString

    @Override
    public String toString() {
        return "IncomingReimbursementDTO{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}
