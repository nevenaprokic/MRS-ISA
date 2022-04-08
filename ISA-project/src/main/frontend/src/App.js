import './App.css';
import {Route, Routes} from 'react-router-dom';
import Layout from './components/layout/Layout';
import Registration from './components/forms/Registration';
import RegistrationOwner from './components/forms/RegistrationOwner';

function App() {
  return (
    <div>
      <Layout>
       
        <Routes>
          <Route path="/registration" element={<Registration />} exact/>
          <Route path='/registration/registration-owner' element={<RegistrationOwner/>}/>
        </Routes>
     
      </Layout>
      
    </div>
    

  );
}

export default App;