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
import ChangeOwnerData from './components/forms/ChangeOwnerData';
import ClientProfile from './components/profilePages/ClientProfile';
import CottageOwnerHomePage from './components/homePages/CottageOwnerHomePage';
import MainNavigation from './components/layout/MainNavigation';
import CottageProfilePage from './components/profilePages/cottageProfile/CottageProfilePage';
import AdventureProfilePage from './components/profilePages/adventureProfile/AdvetureProfilePage';

function App() {
  return (
    <div>
        <Routes>
          <Route path="/" element={<MainNavigation />} />
          <Route path="/log-in" element={<LogIn />} />
          <Route path="/registration" element={<Registration />} exact/>
          <Route path='/registration/registration-owner' element={<RegistrationOwner/>}/>
          <Route path='/user-home-page/instructor' element={<InstructorHomePage/>}/>
          <Route path='/user-profile/instructor' element={<OwnerProfile/>}/>
          <Route path='/user-profile/client' element={<ClientProfile/>}/>
          <Route path='/user-profile/cottage-owner' element={<CottageOwnerHomePage/>}/>
          <Route path='/instructor/add-adventure' element={<AddAdventurePage/>}/>
          <Route path='/registration/registration-client' element={<RegistrationClient/>}/>
          <Route path='/owner/change-data' element={<ChangeOwnerData/>}/>
          <Route path="/cottage-owner/cottage-profile/:id" element={<CottageProfilePage />}/>
          <Route path="/instructor/adventure-profile/:id" element={<AdventureProfilePage />}/>

        </Routes>
    </div>
  );
}

export default App;