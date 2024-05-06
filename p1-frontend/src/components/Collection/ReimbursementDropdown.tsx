import React, { useState } from 'react';
import axios from 'axios';
import { ReimbursementInterface } from '../../interfaces/ReimbursementInterface';

interface ReimbursementDropdownProps {
    onReimbursementsChange: (reimbursements: ReimbursementInterface[]) => void;
}

const ReimbursementDropdown: React.FC<ReimbursementDropdownProps> = ({ onReimbursementsChange }) => {
    const [selectedStatus, setSelectedStatus] = useState('');

    const handleStatusChange = (e:any) => {
        const status = e.target.value;
        setSelectedStatus(status);
        fetchReimbursementsByStatus(status);
    };

    const fetchReimbursementsByStatus = async (status:any) => {
        try {
            const response = await axios.get(`http://localhost:8080/reimbursements/${status}`, {withCredentials:true});
            const reimbursements = response.data;
            onReimbursementsChange(reimbursements);
        } catch (error) {
            console.error('Error fetching reimbursements:', error);
        }
    };

    return (
        <div>
            <select value={selectedStatus} onChange={handleStatusChange}>
                <option value="all">All</option>
                <option value="pending">Pending</option>
                <option value="approved">Approved</option>
                <option value="denied">Denied</option>
            </select>
        </div>
    );
};

export default ReimbursementDropdown;
