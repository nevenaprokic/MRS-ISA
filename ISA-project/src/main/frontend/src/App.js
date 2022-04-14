import './App.css';
import {Route, Routes} from 'react-router-dom';
import Layout from './components/layout/Layout';
import Registration from './components/forms/Registration';
import LogIn from './components/forms/Login';
import RegistrationOwner from './components/forms/RegistrationOwner';
import OwnerProfile from './components/profilePages/OwnerProfile';
import InstructorHomePage from './components/homePages/InstructorHomePage';
import AddAdventurePage from './components/forms/adventure/AddAdventurePage';
import RegistrationClient from './components/forms/RegistrationClient';

function App() {
  return (
    <div>
      <Layout>
       
        <Routes>
          <Route path="/log-in" element={<LogIn />} />
          <Route path="/registration" element={<Registration />} exact/>
          <Route path='/registration/registration-owner' element={<RegistrationOwner/>}/>
          <Route path='/user-home-page/instructor' element={<InstructorHomePage/>}/>
          <Route path='/user-profile/instructor' element={<OwnerProfile/>}/>
          <Route path='/instructor/add-adventure' element={<AddAdventurePage/>}/>
          <Route path='/registration/registration-client' element={<RegistrationClient/>}/>
        </Routes>
     
      </Layout>
      
    </div>
    

  );
}

export default App;