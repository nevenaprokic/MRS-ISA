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
import Button from "@mui/material/Button";
import BoyIcon from '@mui/icons-material/Boy';
import StarIcon from '@mui/icons-material/Star';
import StraightenIcon from '@mui/icons-material/Straighten';
import { offerType } from "../../../app/Enum";
import {
  filterCottagesClient
} from "../../../services/CottageService";
import {filterShipsClient} from "../../../services/ShipService";

export default function ClientFilter({ params, setParams, setOffers, type, lastSearchedOffers }) {

  let filterOffer = {
    [offerType.COTTAGE]: filterCottagesClient,
    [offerType.SHIP]: filterShipsClient
  };

  const resetFields = () => {
    setParams({
    maxRating: "",
    maxPrice: "",
    maxPeople: "",
    minPeople: "",
    minPrice: "",
    minRating: "",
    minSize: "",
    maxSize: "" });
  }

  const handleReset = () => {
    setOffers(lastSearchedOffers);
    resetFields();
  }

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
                      <Grid item xs>
                          <TextField
                            label="Min price"
                            id="priceMin"
                            type="number"
                            value={params.minPrice}
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
                            value={params.maxPrice}
                            onChange={(event) => {
                              setParams({ ...params, maxPrice: event.target.value });
                            }}
                            InputProps={{
                              startAdornment: <InputAdornment position="end">€</InputAdornment>,
                            }}
                          />
                          <Button
                            size="large"
                            sx={{}}
                            onClick={() => filterOffer[type](params, setOffers, lastSearchedOffers)}
                          >
                            Filter
                          </Button>
                          <Button
                            size="large"
                            sx={{}}
                            onClick={() => handleReset()}
                          >
                            Reset
                          </Button>
                      </Grid>
                          <br/>
                      <Grid item>
                          <TextField
                            style = {{maxWidth: 240}}
                            label="Min number of people"
                            id="minPeople"
                            type="number"
                            value={params.minPeople}
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
                            value={params.maxPeople}
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
                            value={params.minRating}
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
                            value={params.maxRating}
                            type="number"
                            onChange={(event) => {
                              setParams({ ...params, maxRating: event.target.value });
                            }}
                            InputProps={{
                              startAdornment: <InputAdornment position="end"><StarIcon/></InputAdornment>,
                            }}
                          />
                      </Grid>
                      <br/>
                      {type == offerType.SHIP &&
                      <Grid item>
                      <TextField
                        style = {{maxWidth: 240}}
                        label="Min size"
                        id="minSize"
                        type="number"
                        value={params.minSize}
                        onChange={(event) => {
                          setParams({ ...params, minSize: event.target.value });
                        }}
                        InputProps={{
                          startAdornment: <InputAdornment position="end"><StraightenIcon/></InputAdornment>,
                        }}
                      />
                       <EastIcon></EastIcon>
                      <TextField
                        style = {{maxWidth: 240}}
                        label="Max size"
                        id="maxSize"
                        value={params.maxSize}
                        type="number"
                        onChange={(event) => {
                          setParams({ ...params, maxSize: event.target.value });
                        }}
                        InputProps={{
                          startAdornment: <InputAdornment position="end"><StraightenIcon/></InputAdornment>,
                        }}
                      />
                  </Grid>}

                  </Typography>
                  </AccordionDetails>
                </Accordion>
            </Grid>
  )
}
