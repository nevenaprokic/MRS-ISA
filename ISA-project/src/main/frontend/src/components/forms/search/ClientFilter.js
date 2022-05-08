import React from 'react'
import Paper from '@mui/material/Paper';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import { TextField } from "@mui/material";
import FilterAltIcon from '@mui/icons-material/FilterAlt';

export default function ClientFilter() {

  return (
    <Grid item xs={12} sm={12} component={Paper} elevation={10} square >
            <Accordion>
                  <AccordionSummary
                    expandIcon={<ExpandMoreIcon />}
                    aria-controls="panel1a-content"
                    id="panel1a-header"
                  >
                    <Typography><FilterAltIcon/></Typography>
                  </AccordionSummary>
                  <AccordionDetails>                 
                    <Typography>
                    
                      <TextField
                        id="from"
                        label="From"
                        defaultValue=""
                        InputLabelProps={{
                          shrink: true,
                        }}
                    />
                    
                    <TextField
                        id="to"
                        label="To"
                        defaultValue=""
                        InputLabelProps={{
                          shrink: true,
                        }}
                    />
                    
          </Typography>
                    <Typography>Price: </Typography>
                    <Typography>Number of people: </Typography>
                  </AccordionDetails>
                </Accordion>
            </Grid>
  )
}
