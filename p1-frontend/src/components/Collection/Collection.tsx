import { useEffect, useState } from "react"
import { ReimbursementInterface } from "../../interfaces/ReimbursementInterface"
import axios from "axios"
import { Reimbursement } from "../Reimbursements/Reimbursement"
import ReimbursementDropdown from "./ReimbursementDropdown"
import "./Collection.css"
import { useNavigate } from "react-router-dom"

export const Collection: React.FC = () => {

    const [reimbursement, setReimbursements] = useState<ReimbursementInterface[]>([])

    useEffect(() => {
        getAllReimbursements()
    }, [])

    const navigate = useNavigate()

    //GET request to servre to get all reimbursements
    const getAllReimbursements = async () => {

        const response = await axios.get("http://localhost:8080/reimbursements", {withCredentials:true})

        setReimbursements(response.data)

        console.log(response.data)

    }

    //handle reimbursementChange
    const handleReimbursementChange = (newReimbursements: ReimbursementInterface[]) => {
        setReimbursements(newReimbursements);
    }

    //delete reimbursement by id
    const deleteReimbursement = async(reimbId:number|undefined) => {

        const response = await axios.delete("http://localhost:8080/reimbursements/" + reimbId, {withCredentials:true})
        .then((response) => alert(response.data))
        .then(() => getAllReimbursements)
        .catch(
            //TODO: some catches for errors
        )
    }

    //update reimbursement by id
    const updateReimbursement = async(reimbId:number|undefined, status:string) => {

        const response = await axios.put("http://localhost:8080/reimbursements/" + reimbId + "/" + status, null, {withCredentials:true})
        .then((response) => {
            alert(response.data); // Display the response message
            getAllReimbursements(); // Refresh view after successful update
        })
        .catch(
            //TODO: some catches for errors
        )
    }



    return(
        <div>
            <h1 className="collection-header">Reimbursements</h1>
            <ReimbursementDropdown onReimbursementsChange={handleReimbursementChange}/>
            <div className="collection-container">
            {reimbursement.map((reimb, index) =>
                <div className="reimb-container" key={index}>
                    <div className="reimb-details">
                    <Reimbursement {...reimb}></Reimbursement>
                    </div>
                    <div className="buttons">
                    <button className="approve-button" onClick={() => updateReimbursement(reimb.reimbID, "approved")}>Approve</button>
                    <button className="deny-button" onClick={() => updateReimbursement(reimb.reimbID, "denied")}>Deny</button>
                    {/*<button className="" onClick={() => deleteReimbursement(reimb.reimbID)}>Delete</button>*/}
                </div>
                </div>
            )}
            </div>
            <div className="buttons">
                <button className="back-button" onClick={() => {navigate("/manager")}}>Back to Dashboard</button>
                {/*<button className="" onClick={() => {navigate("/")}}>Back to Login</button>*/}
            </div>
        </div>
    )
}