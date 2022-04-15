import "./OwnerProfile.scss" 
import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import profileIcon from '../images/user-profile.png'
import EmailIcon from '@mui/icons-material/Email';
import LockIcon from '@mui/icons-material/Lock';
import BasicInfoBox from "./BasicInfoBox";
import AddressInfoBox from "./AddressInfoBox";
import AdditionalInfoClientBox from "./AdditionalInfoClientBox";
import SettingsIcon from '@mui/icons-material/Settings';
import { useState, useEffect } from 'react';
import { getUsernameFromToken } from '../../app/jwtTokenUtils';
import api from "../../app/api";

function ClientProfile(){

    const [clientData, setClientData] = useState();

    useEffect(() => {
        async function fetchPenalties() {
            const request = await api.get(
              "clientProfileInfo?email=" + getUsernameFromToken()
            ).catch(() => { console.log("Doslo je do neke greske kod dobavljanja podataka o klijentu."); });
            setClientData(request ? request.data : null);
          }
          fetchPenalties();
       
    }, [])

    if(clientData){
        return(
            <div className="ownerprofileContainer">

            <Grid container component="main" sx={{ height: '80vh' }}>
                <CssBaseline />
                <Grid item xs={12} sm={5}>
                    <img src={profileIcon} width="70%"></img>
                </Grid>

                <BasicInfoBox basicData={clientData}></BasicInfoBox>
       
                <Grid item xs={12} sm={1}>
                    <EmailIcon/>
                    <br/><br/>
                    <LockIcon/>
                    <br/><br/>
                    <SettingsIcon/>
                </Grid>
                      
                <Grid item xs={12} sm={4}>
                    <Typography>
                            <label className="email">{clientData.email}</label>
                            <br/><br/>
                            <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}}> Change password</Button>
                            <br/><br/>
                            <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}}> Change private data</Button>
                            
                    </Typography>         
                </Grid>
        
                <AddressInfoBox addressData={clientData}/>
                
                <Grid xs={12} sm={5}/>
                <AdditionalInfoClientBox additionalData={clientData}/>
                

            </Grid>
        </div>
       
    );
    }
    
}

export default ClientProfile;