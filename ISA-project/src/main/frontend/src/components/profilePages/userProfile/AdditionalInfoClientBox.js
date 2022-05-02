import "./OwnerProfile.scss" 
import * as React from 'react';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import AssessmentIcon from '@mui/icons-material/Assessment';
import ErrorIcon from '@mui/icons-material/Error';
import ArticleIcon from '@mui/icons-material/Article';


function AdditionalInfoClientBox({additionalData}){
    return(

        <Grid item xs={12} sm={7} component={Paper} elevation={10} square height={"30%"} sx={{borderRadius: "5%", minHeight: "200px"}}>
        <Box className="infoBoxContainer">
         
         <div className="infoBox">
         <label className="boxTitle">Additional information</label><br/><br/>
     
          <div>
              <div className="boxItem">
                  <AssessmentIcon color="action"/>
              </div>
              <label className="boxItemTitle">Client category: </label>
              <label className="boxItemText" >{additionalData.clientCategory.split("_")[0]}</label>
          </div>
          <div>
              <div className="boxItem">
              <ErrorIcon color="action"/>
              </div>
              <label className="boxItemTitle">Penalties: </label>
              <label className="boxItemText">{additionalData.penal}</label>
          </div>
          </div>
        </Box>
      </Grid>
    );
}

export default AdditionalInfoClientBox;