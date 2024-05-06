import { useState } from "react"
import { useNavigate } from "react-router-dom"
import { UserInterface } from "../../interfaces/UserInterface"
import axios from "axios"
import { state } from "../../globalData/store"
import { toast } from "react-toastify"
import "./Login.css"

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
        try {
            const response = await axios.post("http://localhost:8080/users/login", user, { withCredentials: true });
            const userData = response.data;
    
            // Assuming your user data contains a field called 'role'
            const userRole = userData.role;
    
            // Store user data in global state
            state.userSessionData = userData;

            //welcome message
            alert("Welcome, " + state.userSessionData.username)
    
            // Redirect based on user role
            if (userRole === 'employee') {
                navigate("/employee");
            } else if (userRole === 'manager') {
                navigate("/manager");
            } else {
                // Handle unexpected roles or errors
                toast.error("Invalid user role", {
                    position: 'top-right',
                    autoClose: 3000
                });
            }
        } catch (error:any) {
            // Display error message from response body
            if (error.response && error.response.data && error.response.data.error) {
                alert("Error: " + error.response.data.error);
            } else {
                // Display generic error message
                alert("Login Failed!");
            }
        }
    };
    

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