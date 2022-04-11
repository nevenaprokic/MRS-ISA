
import "./OwnerProfile.scss" 
import * as React from 'react';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import PersonIcon from '@mui/icons-material/Person';
import LocalPhoneIcon from '@mui/icons-material/LocalPhone';

function BasicInfoBox(){
    return (
        <Grid item xs={12} sm={7} component={Paper} elevation={10} square height={"30%"} sx={{borderRadius: "5%"}}>
        <Box className="infoBoxContainer">
      
      <div className="infoBox">

          <label className="boxTitle">Basic information</label><br/><br/>
          <div>
              <div className="boxItem">
                  <PersonIcon color="action"/>
              </div>
              <label className="boxItemTitle">First name: </label>
              <label className="boxItemText">{"Mika"}</label>
          </div>
          <div>
              <div className="boxItem">
                  <PersonIcon color="action"/>
              </div>
              <label className="boxItemTitle">Last Name: </label>
              <label className="boxItemText">{"Mikic"}</label>
          </div>
          <div>
              <div className="boxItem">
                  <LocalPhoneIcon color="action"/>
              </div>
              <label className="boxItemTitle">Phone: </label>
              <label className="boxItemText">{"0631332312"}</label>
          </div>
          </div>
                
        </Box>
      </Grid>   
    );
}

export default BasicInfoBox;
