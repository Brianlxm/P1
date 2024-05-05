// EmployeeComponent.tsx
import React from "react";
import { Collection } from "../Collection/Collection";
import { useNavigate } from "react-router-dom";


export const Employee: React.FC = () => {

    const navigate = useNavigate()

    return (
        <div>
            <h1>Employee Dashboard</h1>

            {/*TODO: add new reimbursement*/}
            {/*TODO: change user information*/}
            <div className="navbar">
                <div>
                    <button className="" onClick={() => {navigate("/collection")}}>See All Reimbursements</button>
                </div>
                <div>
                    <button className="" onClick={() => {navigate("/createreimbursement")}}>Request New Reimbursement</button>
                </div>
                <div>
                    <button className="" onClick={() => {navigate("/")}}>Back to Login</button>
                </div>
            </div>

        </div>
    );
};
