import { useEffect, useState } from "react"
import axios from "axios"
import "./UserCollection.css"
import { useNavigate } from "react-router-dom"
import { UserInterface } from "../../interfaces/UserInterface"
import { User } from "../Users/User"
import { error } from "console"

export const UserCollection: React.FC = () => {

    const [user, setUsers] = useState<UserInterface[]>([])

    useEffect(() => {
        getAllUsers()
    }, [])

    const navigate = useNavigate()

    //GET request to servre to get all reimbursements
    const getAllUsers = async () => {

        const response = await axios.get("http://localhost:8080/users", {withCredentials:true})

        setUsers(response.data)

        console.log(response.data)

    }


    //delete reimbursement by id
    const deleteUser = async(userId:number|undefined) => {

        const response = await axios.delete("http://localhost:8080/users/" + userId, {withCredentials:true})
        .then((response) => {
            alert(response.data)
            getAllUsers();
        })
        .catch((error) => {
            alert("Managers cannot be deleted")
        })

            //TODO: some catches for errors
    }

    //update user role
    const updateUserRole = async(userId:number|undefined) => {

        const response = await axios.put("http://localhost:8080/users/" + userId, null, {withCredentials:true})
        .then((response) => {
            alert(response.data)
            getAllUsers();
        })
        .catch((error) => {
            alert("Error occured")
        })
    }

    //update reimbursement by id
    // const updateReimbursement = async(reimbId:number|undefined, status:string) => {

    //     const response = await axios.put("http://localhost:8080/reimbursements/" + reimbId + "/" + status, null, {withCredentials:true})
    //     .then((response) => {
    //         alert(response.data); // Display the response message
    //         getAllReimbursements(); // Refresh view after successful update
    //     })
    //     .catch(
    //         //TODO: some catches for errors
    //     )
    // }



    return(
        <div className="user-collection-container">
            <h1 className="user-collection-header">Users</h1>
            <div className="collection-container">
            {user.map((user, index) =>
                <div className="user-container" key={index}>
                    <User {...user}></User>
                    <div className="buttons">
                    <button className="" onClick={() => updateUserRole(user.userId)}>Change Role</button>
                    <button className="" onClick={() => deleteUser(user.userId)}>Delete</button>
                    </div>
                </div>
            )}
            </div>
            <div className="buttons">
                <button className="" onClick={() => {navigate("/manager")}}>Back to Dashboard</button>
                {/*<button className="" onClick={() => {navigate("/")}}>Back to Login</button>*/}
            </div>
        </div>
    )
}