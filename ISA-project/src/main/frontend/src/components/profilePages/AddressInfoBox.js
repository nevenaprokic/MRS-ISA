
import "./OwnerProfile.scss" 
import * as React from 'react';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';

import HomeIcon from '@mui/icons-material/Home';


function AddressInfoBox(){
    return(
        <Grid item xs={12} sm={7} component={Paper} elevation={10} square height={"30%"} sx={{borderRadius: "5%"}}>
          <Box className="infoBoxContainer">
           
           <div className="infoBox">
           <label className="boxTitle">Address</label><br/><br/>
       
        <div>
        
            <div className="boxItem">
                <HomeIcon color="action"/>
            </div>
            <label className="boxItemTitle">Street: </label>
            <label className="boxItemText">{"Neka ulica 10"}</label>
        </div>
        <div>
            <div className="boxItem">
            <HomeIcon color="action"/>
            </div>
            <label className="boxItemTitle">City: </label>
            <label className="boxItemText">{"Neki grad"}</label>
        </div>
        <div>
            <div className="boxItem">
            <HomeIcon color="action"/>
            </div>
            <label className="boxItemTitle">State: </label>
            <label className="boxItemText">{"Neka drzava"}</label>
        </div>
        </div>
          </Box>
         
        </Grid>
        
    );
}

export default AddressInfoBox;