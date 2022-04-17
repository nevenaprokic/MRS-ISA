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
import AdditionalinfoBox from "./AdditionalInfoBox";
import SettingsIcon from '@mui/icons-material/Settings';
import { getRoleFromToken, getUsernameFromToken } from '../../app/jwtTokenUtils';
import { getInstructorByUsername, getCottageOwnerByUsername } from "../../services/userService";
import { useState, useEffect } from 'react';
function OwnerProfile(){

    const [ownerData, setOwnerData] = useState();

    useEffect(() => {
        async function setownerData() {
        let username = getUsernameFromToken();
        let role = getRoleFromToken();
        let requestData = null;
        if(role == "COTTAGE_OWNER"){
            requestData = await getCottageOwnerByUsername(username);
        }else if(role == "INSTRUCTOR"){
            requestData = await getInstructorByUsername(username);
        }
        setOwnerData(!!requestData ? requestData.data : {});        //  requestData.data.email;
        console.log(ownerData);

        return requestData;    
    }
       setownerData();
       
    }, [])

    if(!! ownerData){
        return(
            <div className="ownerprofileContainer">

            <Grid container component="main" sx={{ height: '100vh' }}>
                <CssBaseline />
                <Grid item xs={12} sm={5}>
                    <img src={profileIcon} width="40%"></img>
                </Grid>

                <BasicInfoBox basicData={ownerData}></BasicInfoBox>
       
                <Grid item xs={12} sm={1}>
                    <EmailIcon/>
                    <br/><br/>
                    <LockIcon/>
                    <br/><br/>
                    <SettingsIcon/>
                </Grid>
                      
                <Grid item xs={12} sm={4}>
                    <Typography>
                            <label className="email">{ownerData.email}</label>
                            <br/><br/>
                            <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}}> Change password</Button>
                            <br/><br/>
                            <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}}> Change private data</Button>
                            
                    </Typography>         
                </Grid>
        
                <AddressInfoBox addressData={ownerData}/>
                
                <Grid xs={12} sm={5}/>
                <AdditionalinfoBox additionalDate={ownerData}/>
                

            </Grid>
        </div>
       
    );
    }
    
}

export default OwnerProfile;