// ManagerComponent.tsx
import React from "react";
import { Collection } from "../Collection/Collection";
import { useNavigate } from "react-router-dom";
import "./Manager.css"

export const Manager: React.FC = () => {

    const navigate = useNavigate()



    return (
        <div className="manager-container">
            <h1 className="manager-header">Manager Dashboard</h1>
            <div>
                <button className="manager-button" onClick={() => {navigate("/collection")}}>See All Reimbursements</button>
            </div>
            <div>
                <button className="manager-button" onClick={() => {navigate("/usercollection")}}>See All Users</button>
            </div>
            <div>
                <button className="manager-button" onClick={() => {navigate("/")}}>Back to Login</button>
            </div>
            {/* Additional manager-specific functionality can go here */}
        </div>
    );
};
