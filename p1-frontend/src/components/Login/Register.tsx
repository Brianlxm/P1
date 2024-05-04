import { useState } from "react"
import { UserInterface } from "../../interfaces/UserInterface"
import { Navigate, useNavigate } from "react-router-dom"
import axios from "axios"
import { toast } from "react-toastify"

export const Register: React.FC = () => {

    //set state (UserInterface)
    const[user, setUser] = useState<UserInterface>({
        username:"",
        password:"",
        firstName:"",
        lastName:"",
        role:"employee"
    })

    const navigate = useNavigate()

    const storeValues = (input:any) => {
        if(input.target.name === "username"){
            setUser((user) => ({...user, username:input.target.value}))
        } else if(input.target.name === "password"){
            setUser((user) => ({...user, password:input.target.value})) 
        } else if(input.target.name === "firstName"){
            setUser((user) => ({...user, firstName:input.target.value})) 
        } else if(input.target.name === "lastName"){
            setUser((user) => ({...user, lastName:input.target.value})) 
        } else if(input.target.name === "role"){
            setUser((user) => ({...user, role:input.target.value})) 
        }
    }

    //function to send POST with user data to register user in the backend
    const register = async () => {
        const response = await axios.post("http://localhost:8080/users", user)
        .then((response) => {
        // alert
        alert(response.data)

        //toast
        toast.success(response.data, {
            position: 'top-right',
            autoClose: 3000
        }) //"{user} was created!"

        navigate("/")
    })
    .catch((error) => {alert(error.message)})
    }


    return (
        <div className="login">

            <div className="text-container">
                <h1>Create an account</h1>

                <div className="input-container">
                    <input type="text" placeholder="Username" name="username" onChange={storeValues}/>
                </div>

                <div className="input-container">
                    <input type="password" placeholder="Password" name="password" onChange={storeValues}/>
                </div>

                <div className="input-container">
                    <input type="text" placeholder="First Name" name="firstName" onChange={storeValues}/>
                </div>

                <div className="input-container">
                    <input type="text" placeholder="Last Name" name="lastName" onChange={storeValues}/>
                </div>

                <button className="login-button" onClick={register}>Submit</button>
                <button className="login-button" onClick={() => navigate("/")}>Back</button>
            </div>

        </div>
    )
}