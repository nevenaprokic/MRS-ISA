import "./OwnerProfile.scss" 
import * as React from 'react';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import AssessmentIcon from '@mui/icons-material/Assessment';
import { getRoleFromToken } from '../../../app/jwtTokenUtils';
import { userType } from "../../../app/Enum";
import Biography from "./Biography";


function AdditionalInfoBox({additionalDate}){
    console.log(additionalDate);
    return(
        <Grid item xs={12} sm={7} component={Paper} elevation={10} square height={"30%"} sx={{borderRadius: "5%", minHeight: "200px"}}>
        <Box className="infoBoxContainer">
        <div className="infoBox">
            <label className="boxTitle">Additional information</label><br/><br/>
        
            <div>
                <div className="boxItem">
                    <AssessmentIcon color="action"/>
                </div>
                <label className="boxItemTitle">User category: </label>
                <label className="boxItemText">{additionalDate.userCategory}</label>
            </div>

            {getRoleFromToken() != userType.COTTAGE_OWNER && getRoleFromToken() != userType.SHIP_OWNER && 
                
                <Biography bigraphy={additionalDate.biography} />
                   
            }
        
          </div>
        </Box>
      </Grid>
    );
}

export default AdditionalInfoBox;