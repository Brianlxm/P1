import { useState } from "react"
import { ReimbursementInterface } from "../../interfaces/ReimbursementInterface"
import { useNavigate } from "react-router-dom"
import axios from "axios"
import "./CreateReimbursement.css"

export const CreateReimbursement: React.FC = () => {

    //variable to store user input for creating reimbursement
    const [reimbursement, setReimbursement] = useState<Partial<ReimbursementInterface>>({
        description:'',
        amount:0,
        status:'pending'

    })

    const navigate = useNavigate()

    const storeValues = (input:any) => {
        if(input.target.name === "description"){
            setReimbursement((reimbursement) => ({...reimbursement, description:input.target.value}))
        } else if (input.target.name === "amount"){
            setReimbursement((reimbursement) => ({...reimbursement, amount:input.target.value}))
        } 
    }

    //function to send post to backend
    const createReimbursement = async () => {
        const response = await axios.post("http://localhost:8080/reimbursements", reimbursement, { withCredentials: true })
        .then((response) => {
            alert(response.data)
            navigate("/employee")
        })
        .catch((error) => {alert(error.message)})
    }



    return(
        <div className="create-reimbursement-container">
            <h1>Request a new Reimbursement</h1>

            <div className="input-container">
                <input type="text" placeholder="description" name="description" onChange={storeValues}/>
            </div>

            <div className="input-container">
                <input type="number" placeholder="amount" name="amount" onChange={storeValues}/>
            </div>

            <button className="submit-button" onClick={createReimbursement}>Submit</button>
            <button className="back-button" onClick={() => navigate("/employee")}>Back</button>

        </div>
    )

}