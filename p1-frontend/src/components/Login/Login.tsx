import { useNavigate } from "react-router-dom"

export const Login: React.FC = () => {

    //need useNavigate hook to let us navigate between components
    const navigate = useNavigate()

    //TODO: Login function
    const login = async () => {
        
        //TODO: make this send actual POST request with user inputs
        navigate("/reimbursement")
    }





    return(
        <div className="login">
            <div className="text-container">
                <h1>Employee Reimbursement System</h1>

                <div className="input-container">
                    <input type="text" placeholder="username" name="username"/>
                </div>
                <div className="input-container">
                    <input type="password" placeholder="password" name="password"/>
                </div>

                <button className="login-button" onClick={login}>Login</button>
                <button className="login-button" onClick={() => navigate("/register")}>Create Account</button>
            </div>

        </div>
    )
}