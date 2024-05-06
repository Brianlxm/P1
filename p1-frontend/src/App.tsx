
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import { Login } from './components/Login/Login';
import { Register } from './components/Login/Register';
import { Employee } from './components/Employee/Employee';
import { Manager } from './components/Manager/Manager';
import { CreateReimbursement } from './components/Reimbursements/CreateReimbursement';
import { Collection } from './components/Collection/Collection';
import { EmployeeCollection } from './components/Collection/EmployeeCollection';
import { UserCollection } from './components/Manager/UserCollection';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path='' element={<Login/>}/>
          <Route path='/collection' element={<Collection/>}/>
          <Route path='/employee' element={<Employee/>}/>
          <Route path='/manager' element={<Manager/>}/>
          <Route path='/register' element={<Register/>}/>
          <Route path='/createreimbursement' element={<CreateReimbursement/>}/>
          <Route path='/employeecollection' element={<EmployeeCollection/>}/>
          <Route path='/usercollection' element={<UserCollection/>}/>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
