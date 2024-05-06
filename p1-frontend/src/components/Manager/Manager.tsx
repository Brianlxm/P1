// ManagerComponent.tsx
import React from "react";
import { Collection } from "../Collection/Collection";
import { useNavigate } from "react-router-dom";

export const Manager: React.FC = () => {

    const navigate = useNavigate()



    return (
        <div>
            <h1>Manager Dashboard</h1>
            <div>
                <button className="" onClick={() => {navigate("/collection")}}>See All Reimbursements</button>
            </div>
            <div>
                <button className="" onClick={() => {navigate("/usercollection")}}>See All Users</button>
            </div>
            <div>
                <button className="" onClick={() => {navigate("/")}}>Back to Login</button>
            </div>
            {/* Additional manager-specific functionality can go here */}
        </div>
    );
};
