// EmployeeComponent.tsx
import React from "react";
import { Collection } from "../Collection/Collection";
import { useNavigate } from "react-router-dom";
import "./Employee.css"


export const Employee: React.FC = () => {

    const navigate = useNavigate()

    return (
        <div className="manager-container">
            <h1 className="manager-header">Employee Dashboard</h1>

            {/*TODO: add new reimbursement*/}
            {/*TODO: change user information*/}
            <div className="">
                <div>
                    <button className="manager-button" onClick={() => {navigate("/employeecollection")}}>See All Reimbursements</button>
                </div>
                <div>
                    <button className="manager-button" onClick={() => {navigate("/createreimbursement")}}>Request New Reimbursement</button>
                </div>
                <div>
                    <button className="manager-button" onClick={() => {navigate("/")}}>Back to Login</button>
                </div>
            </div>

        </div>
    );
};
