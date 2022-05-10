import React from 'react'
import Paper from '@mui/material/Paper';
import Accordion from '@mui/material/Accordion';
import AccordionSummary from '@mui/material/AccordionSummary';
import AccordionDetails from '@mui/material/AccordionDetails';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ArrowUpwardTwoToneIcon from '@mui/icons-material/ArrowUpwardTwoTone';
import ArrowDownwardTwoToneIcon from '@mui/icons-material/ArrowDownwardTwoTone';
import SortByAlphaTwoToneIcon from '@mui/icons-material/SortByAlphaTwoTone';
import Button from "@mui/material/Button";
import { useState } from "react";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import {sortCottages} from '../../../services/CottageService'
import {sortShips} from '../../../services/ShipService'
import { offerType } from '../../../app/Enum';


export default function ClientSort({offers, setOffers, type}) {

    const [sortAsc, setSortAsc] = useState(true);
    const [criteria, setCriteria] = useState(1);

    let sortOffers = {
      [offerType.COTTAGE]: sortCottages,
      [offerType.SHIP]: sortShips
    };


    const criteriaChanged = event => {
        let value = event.target.value;
        setCriteria(value);
        sortOffers[type](value, sortAsc, offers, setOffers);
    };

    const orderChanged = () => {
        sortOffers[type](criteria, !sortAsc, offers, setOffers);
        setSortAsc(!sortAsc);
    };

  return (
    <Grid item xs={12} sm={12} component={Paper} elevation={10} square >
    <Accordion>
          <AccordionSummary
            expandIcon={<ExpandMoreIcon />}
            aria-controls="panel1a-content"
            id="panel1a-header"
          >
            <Typography><SortByAlphaTwoToneIcon/></Typography>
          </AccordionSummary>
          <AccordionDetails>                 
            <Typography>
            
            <Box>
                <FormControl style={{minWidth: 120}}>
                    <InputLabel id="demo-simple-select-label">Criteria</InputLabel>
                    <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={criteria}
                    label="Criteria"
                    onChange={criteriaChanged}
                    >
                    <MenuItem value={1}>Name</MenuItem>
                    <MenuItem value={2}>Street</MenuItem>
                    <MenuItem value={3}>City</MenuItem>
                    <MenuItem value={4}>Rating</MenuItem>
                    <MenuItem value={5}>Price</MenuItem>
                    { type == offerType.SHIP && 
                        <MenuItem value={6}>Size</MenuItem>
                    }
                    { type == offerType.SHIP && 
                        <MenuItem value={7}>Max speed</MenuItem>
                    }
                    </Select>
                </FormControl>
                <Button onClick={ () => { orderChanged(); }}> { (sortAsc) ? (<ArrowUpwardTwoToneIcon fontSize="large"/>) : (<ArrowDownwardTwoToneIcon fontSize="large"/>) } </Button>
            </Box>
            
            </Typography>
          </AccordionDetails>
        </Accordion>
    </Grid>
  )
}
