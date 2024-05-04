import { ReimbursementInterface } from "../../interfaces/ReimbursementInterface";

export const Reimbursement: React.FC<ReimbursementInterface> = (reimb:ReimbursementInterface) => {

    return(
        <div className="reimbursement">
            <div className="field">
                <label>Username: </label>
                <span>{reimb.username}</span>
            </div>
            <div className="field">
                <label>User Id: </label>
                <span>{reimb.userId}</span>
            </div>
            <div className="field">
                <label>Description: </label>
                <span>{reimb.description}</span>
            </div>
            <div className="field">
                <label>Amount: </label>
                <span>{reimb.amount}</span>
            </div>
            <div className="field">
                <label>Status: </label>
                <span>{reimb.status}</span>
            </div>
        </div>
    )
}