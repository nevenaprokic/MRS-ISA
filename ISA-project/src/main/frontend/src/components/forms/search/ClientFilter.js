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
import InputAdornment from "@mui/material/InputAdornment";
import EastIcon from '@mui/icons-material/East';
import BoyIcon from '@mui/icons-material/Boy';
import StarIcon from '@mui/icons-material/Star';

export default function ClientFilter({ params, setParams }) {

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
                      <Grid item>
                          <TextField
                            label="Min price"
                            id="priceMin"
                            type="number"
                            onChange={(event) => {
                              setParams({ ...params, minPrice: event.target.value });
                            }}
                            InputProps={{
                              startAdornment: <InputAdornment position="end">€</InputAdornment>,
                            }}
                          />
                          <EastIcon></EastIcon>
                          <TextField
                            label="Max price"
                            id="priceMax"
                            type="number"
                            onChange={(event) => {
                              setParams({ ...params, maxPrice: event.target.value });
                            }}
                            InputProps={{
                              startAdornment: <InputAdornment position="end">€</InputAdornment>,
                            }}
                          />
                      </Grid>
                          <br/>
                      <Grid item>
                          <TextField
                            style = {{maxWidth: 240}}
                            label="Min number of people"
                            id="minPeople"
                            type="number"
                            onChange={(event) => {
                              setParams({ ...params, minPeople: event.target.value });
                            }}
                            InputProps={{
                              startAdornment: <InputAdornment position="end"><BoyIcon/></InputAdornment>,
                            }}
                          />
                           <EastIcon></EastIcon>
                          <TextField
                            style = {{maxWidth: 240}}
                            label="Max number of people"
                            id="maxPeople"
                            type="number"
                            onChange={(event) => {
                              setParams({ ...params, maxPeople: event.target.value });
                            }}
                            InputProps={{
                              startAdornment: <InputAdornment position="end"><BoyIcon/></InputAdornment>,
                            }}
                          />
                      </Grid>
                      <br/>
                      <Grid item>
                          <TextField
                            style = {{maxWidth: 240}}
                            label="Min rating"
                            id="minRating"
                            type="number"
                            onChange={(event) => {
                              setParams({ ...params, minRating: event.target.value });
                            }}
                            InputProps={{
                              startAdornment: <InputAdornment position="end"><StarIcon/></InputAdornment>,
                            }}
                          />
                           <EastIcon></EastIcon>
                          <TextField
                            style = {{maxWidth: 240}}
                            label="Max rating"
                            id="maxRating"
                            type="number"
                            onChange={(event) => {
                              setParams({ ...params, maxRating: event.target.value });
                            }}
                            InputProps={{
                              startAdornment: <InputAdornment position="end"><StarIcon/></InputAdornment>,
                            }}
                          />
                      </Grid>

                  </Typography>
                  </AccordionDetails>
                </Accordion>
            </Grid>
  )
}
