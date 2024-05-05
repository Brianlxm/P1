import { useEffect, useState } from "react"
import { ReimbursementInterface } from "../../interfaces/ReimbursementInterface"
import axios from "axios"
import { Reimbursement } from "../Reimbursements/Reimbursement"
import ReimbursementDropdown from "./ReimbursementDropdown"
import "./Collection.css"

export const Collection: React.FC = () => {

    const [reimbursement, setReimbursements] = useState<ReimbursementInterface[]>([])

    useEffect(() => {
        getAllReimbursements()
    }, [])

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



    return(
        <div>
            <ReimbursementDropdown onReimbursementsChange={handleReimbursementChange}/>
            <div className="collection-container">
            {reimbursement.map((reimb, index) =>
                <div className="reimb" key={index}>
                    <Reimbursement {...reimb}></Reimbursement>
                    <button className="" onClick={() => deleteReimbursement(reimb.reimbID)}>Delete</button>
                </div>
            )}
            </div>
        </div>
    )
}