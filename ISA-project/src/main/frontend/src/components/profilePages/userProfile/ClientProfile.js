import "./OwnerProfile.scss" 
import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import profileIcon from '../../images/profile.png'
import EmailIcon from '@mui/icons-material/Email';
import LockIcon from '@mui/icons-material/Lock';
import BasicInfoBox from "./BasicInfoBox";
import AddressInfoBox from "./AddressInfoBox";
import AdditionalInfoClientBox from "./AdditionalInfoClientBox";
import SettingsIcon from '@mui/icons-material/Settings';
import { useState, useEffect } from 'react';
import { getUsernameFromToken } from '../../../app/jwtTokenUtils';
import api from "../../../app/api";
import Modal from '@mui/material/Modal';
import ChangeClientData from "../../forms/user/ChangeClientData";
import DeleteIcon from '@mui/icons-material/Delete';
import ChangePassword from "../../forms/user/ChangePassword";


function ClientProfile(){

    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        width: 400,
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 24,
        p: 4,
        paleGreen: "#dae0d2"
      };


    const [clientData, setClientData] = useState();
    const [open, setOpen] = useState(false);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const [openPasswordManager, setPasswordManager] = useState(false);

    const handleOpenPass = () => setPasswordManager(true);
    const handleClosePass = () => setPasswordManager(false);

    const childToParent = (childData) => {
        setClientData(prevState => ({
            ...prevState,
            ["firstName"]: childData.firstName,
            ["lastName"]: childData.lastName,
            ["phoneNumber"]: childData.phoneNumber,
            ["city"]: childData.city,
            ["street"]: childData.street,
            ["state"]: childData.state,

        }));
      }
    
    useEffect(() => {
        async function setData() {
            const request = await api.get(
              "clientProfileInfo?email=" + getUsernameFromToken()
            ).catch(() => { console.log("Doslo je do neke greske kod dobavljanja podataka o klijentu."); });
            setClientData(request ? request.data : null);
          }
          setData();
    }, [])

    const deleteAccount = () => {
        api
          .get("deleteAccount?email=" + getUsernameFromToken())
          .then((res) => {
              console.log(res);
          })
          .catch((err) => {
              console.log("Nije uspesna prijava");
          });
    }

    if(clientData){
        return(
            <div className="ownerprofileContainer">

            <Grid container component="main" sx={{ height: '100vh', width: '40vw', marginLeft:'10%'  }}>
                <CssBaseline />
                <Grid item xs={12} sm={5} lg={5}>
                    <img src={profileIcon} width="60%"></img>
                </Grid>

                <BasicInfoBox basicData={clientData}></BasicInfoBox>
       
                <Grid item xs={12} sm={1} lg={1}>
                    <EmailIcon/>
                    <br/><br/>
                    <LockIcon/>
                    <br/><br/>
                    <SettingsIcon/>
                    <br/><br/>
                    <DeleteIcon/>
                </Grid>
                      
                <Grid item xs={12} sm={4} lg={4}>
                    <Typography>
                            <label className="email">{clientData.email}</label>
                            <br/><br/>
                            <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}} onClick={handleOpenPass} > Change password</Button>
                            <br/><br/>
                            <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}} onClick={handleOpen}> Change private data</Button>
                            <br/><br/>
                            <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}} onClick={deleteAccount} > Delete profile</Button>
                            
                    </Typography>         
                </Grid>

                <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                    sx={{backgroundColor:"rgb(218, 224, 210, 0.6)"}}
                >
                    
                        <ChangeClientData currentClientData={clientData} close={handleClose} childToParent={childToParent} />
                    
                </Modal>

                <Modal
                    open={openPasswordManager}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                    sx={{backgroundColor:"rgb(218, 224, 210, 0.6)"}}
                >
                    
                        <ChangePassword close={handleClosePass} />
                    
                </Modal>
        
                <AddressInfoBox addressData={clientData}/>
                
                <Grid xs={12} sm={5} lg={5}/>
                <AdditionalInfoClientBox additionalData={clientData}/>
                

            </Grid>
        </div>
       
    );
    }
    
}

export default ClientProfile;