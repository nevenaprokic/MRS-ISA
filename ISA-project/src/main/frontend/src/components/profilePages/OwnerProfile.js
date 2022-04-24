import "./OwnerProfile.scss" 
import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import profileIcon from '../images/profile.png'
import EmailIcon from '@mui/icons-material/Email';
import LockIcon from '@mui/icons-material/Lock';
import BasicInfoBox from "./BasicInfoBox";
import AddressInfoBox from "./AddressInfoBox";
import AdditionalinfoBox from "./AdditionalInfoBox";
import SettingsIcon from '@mui/icons-material/Settings';
import { getUsernameFromToken, getRoleFromToken } from '../../app/jwtTokenUtils';
import { getInstructorByUsername, getCottageOwnerByUsername, getShipOwnerByUsername } from "../../services/userService";
import { useState, useEffect } from 'react';
import ChangeOwnerData from "../forms/ChangeOwnerData";
import Modal from '@mui/material/Modal';
import DeleteIcon from '@mui/icons-material/Delete';
import { userType } from "../../services/userService";
import Biography from "./Biography";


function OwnerProfile(){

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

    const [ownerData, setOwnerData] = useState();
    const [open, setOpen] = useState(false);

    let getOfferByOwnerEmail = {
        [userType.COTTAGE_OWNER] :  getCottageOwnerByUsername,
        [userType.INSTRUCTOR] :  getInstructorByUsername,
        [userType.SHIP_OWNER]: getShipOwnerByUsername
      }

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const childToParent = (childData) => {
        setOwnerData(prevState => ({
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
            let username = getUsernameFromToken();
            let role = getRoleFromToken();
            let requestData = await getOfferByOwnerEmail[role](username);
            setOwnerData(!!requestData ? requestData.data : {});        //  requestData.data.email;

        return requestData;    
    }
       setData();
       
    }, [])



    if(ownerData){
        return(
            <div className="ownerprofileContainer">

            <Grid container component="main" sx={{ height: '100vh', width: '40vw', marginLeft:'10%' }}> 
                <CssBaseline />
                <Grid item xs={12} sm={5}>
                    <img src={profileIcon} width="60%"></img>
                </Grid>

                <BasicInfoBox basicData={ownerData}></BasicInfoBox>
       
                <Grid item xs={12} sm={1}>
                    <EmailIcon/>
                    <br/><br/>
                    <LockIcon/>
                    <br/><br/>
                    <SettingsIcon/>
                    <br/><br/>
                    <DeleteIcon/>
                </Grid>
                      
                <Grid item xs={12} sm={4}>
                    <Typography>
                            <label className="email">{ownerData.email}</label>
                            <br/><br/>
                            <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}}> Change password</Button>
                            <br/><br/>
                            <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}} onClick={handleOpen}> Change private data</Button>
                            <br/><br/>
                            <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}} > Delete profile</Button>
                            
                    </Typography>         
                </Grid>
                <Modal
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                    sx={{backgroundColor:"rgb(218, 224, 210, 0.6)"}}
                >
                    
                        <ChangeOwnerData currentOwnerData={ownerData} close={handleClose} childToParent={childToParent}/>
                    
                </Modal>
        
                <AddressInfoBox addressData={ownerData}/>
                
                <Grid xs={12} sm={5}/>

                 <AdditionalinfoBox additionalDate={ownerData}/>

            </Grid>
        </div>
       
    );
    }
    
}

export default OwnerProfile;