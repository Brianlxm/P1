import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { UserInterface } from "../../interfaces/UserInterface"
import axios from "axios"
import { state } from "../../globalData/store"
import { toast } from "react-toastify"

export const Login: React.FC = () => {

    //defining state object for user data
    const[user, setUser] = useState<UserInterface>({
        username:"",
        password:""
    })

    //need useNavigate hook to let us navigate between components
    const navigate = useNavigate()

    //function to store input box values
    const storeValues = (input:any) => {
        if(input.target.name === "username"){
            setUser((user) => ({...user, username:input.target.value}))
        } else {
            setUser((user) => ({...user, password:input.target.value}))
        }
    }

    //TODO: Login function
    const login = async () => {
    
        //TODO: make this send actual POST request with user inputs
        //with credentials to let us save/send user session info
        const response = await axios.post("http://localhost:8080/users/login", user,
        {withCredentials:true})
        .then((response) => {

            //store user into in global state
            state.userSessionData = response.data

            console.log(state.userSessionData)

            toast.success("Welcome, " + state.userSessionData.username, {
                position: 'top-right',
                autoClose: 3000
            })

            navigate("/collection")

        })
        .catch((error) => {alert(error.message)})

    }





    return(
        <div className="login">
            <div className="text-container">
                <h1>Employee Reimbursement System</h1>

                <div className="input-container">
                    <input type="text" placeholder="username" name="username" onChange={storeValues}/>
                </div>
                <div className="input-container">
                    <input type="password" placeholder="password" name="password" onChange={storeValues}/>
                </div>

                <button className="login-button" onClick={login}>Login</button>
                <button className="login-button" onClick={() => navigate("/register")}>Create Account</button>
            </div>

        </div>
    )
}