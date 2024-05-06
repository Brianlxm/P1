import { UserInterface } from "../../interfaces/UserInterface";
import "./User.css"

export const User: React.FC<UserInterface> = (user:UserInterface) => {

    return(
        <div className="user">
            <div className="field">
                <label>User Id: </label>
                <span>{user.userId}</span>
            </div>
            <div className="field">
                <label>Username: </label>
                <span>{user.username}</span>
            </div>
            <div className="field">
                <label>First Name: </label>
                <span>{user.firstName}</span>
            </div>
            <div className="field">
                <label>Last Name: </label>
                <span>{user.lastName}</span>
            </div>
            <div className="field">
                <label>Role: </label>
                <span>{user.role}</span>
            </div>
        </div>
    )
}