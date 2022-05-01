import * as React from 'react';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import profileIcon from '../images/profile.png'
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import Paper from '@mui/material/Paper';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';

export default function InstructorsAdventures({adventures}) {

    let imag = require("../images/no-image.png");

    if(adventures){
        return (
            <Grid item xs={12} sm={12} component={Paper} elevation={10} square sx={{borderRadius: "5%"}}>
            <Box className="infoBoxContainer">
            <div className="infoBox">
            <label className="boxTitle">Adventures</label><br/><br/>
                {adventures.map((adventure) => (
                  <Accordion>
                  <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                  >
                    <Typography>{adventure.offerName}</Typography>
                  </AccordionSummary>
                  <AccordionDetails>
                    {(adventure.photos.length != 0) ? (<img src={ require("/src/components/images/" + adventure.photos[0])} width="60%"></img>) : (<img src={imag} width="60%"></img>)}                    
                    <Typography>Description: {adventure.description}</Typography>
                    <Typography>Price: {adventure.price}</Typography>
                    <Typography>Number of people: {adventure.peopleNum}</Typography>
                    <Typography>State: {adventure.state}</Typography>
                    <Typography>City: {adventure.city}</Typography>
                    <Typography>Street: {adventure.street}</Typography>
                  </AccordionDetails>
                </Accordion>
                ))}
                 </div>
            </Box>
            </Grid>
        )
    }
}
