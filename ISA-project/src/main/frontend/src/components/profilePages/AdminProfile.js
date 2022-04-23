import "../../style/AdminProfile.scss" 
import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import profileIcon from '../images/admin_icon.png'
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

function AdminProfile(){

    let adminData = {firstName: "pera",
                    lastName: "peric",
                    phoneNumber: "12121",
                    street: "neka ulica br",
                    city: "neki grad",
                    state: "neka drzava",
                    email: "pera@gmail.com"};
    //dobavljanje podataka o adminu

    return(
        <div className="adminProfileContainer">

        <Grid container component="main" sx={{ height: '100vh', width: '40vw', marginLeft:'10%'}}> 
            <CssBaseline />
            <Grid item xs={12} sm={5}>
                <img src={profileIcon} width="60%"></img>
            </Grid>

            <BasicInfoBox basicData={adminData}></BasicInfoBox>
   
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
                        <label className="email">{adminData.email}</label>
                        <br/><br/>
                        <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}}> Change password</Button>
                        <br/><br/>
                        <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}} > Change private data</Button>
                        <br/><br/>
                        <Button  size="small" sx={{backgroundColor:"#99A799", color:"black"}} > Delete profile</Button>
                        
                </Typography>         
            </Grid>    
            <AddressInfoBox addressData={adminData}/>
            
            <Grid xs={12} sm={5}/>
        

        </Grid>
    </div>
    );

}

export default AdminProfile;