import * as React from 'react';
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import { TextField } from '@mui/material';
import InputAdornment from '@mui/material/InputAdornment';


export default function Search({params, setParams}){
    return(
        <Grid container spacing={5}>
           
            <Grid item xs>
                <TextField
                id="cottage name"
                label="Cottage name"
                defaultValue=""
                onChange = { event => { setParams({...params, "name": event.target.value}); }}
                />
                
            </Grid>
            <Grid item xs>
            <TextField
                
                id="peopleNum"
                label="Maximum number of people"
                type="number"
                onChange = { event => { setParams({...params, "maxPeople": event.target.value}); }}
                InputLabelProps={{
                shrink: true,
                }}
                fullWidth
                
              />
            </Grid>
            <Grid item xs>
                <TextField
                id="address"
                label="Address"
                defaultValue=""
                onChange = { event => { setParams({...params, "address": event.target.value}); }}
                />
                
            </Grid>
            <Grid item xs>
            <TextField 
                fullWidth
                label="Price"
                id="price"
                type="number"
                onChange = { event => { setParams({...params, "price": event.target.value}); }}
                InputProps={{
                  startAdornment: <InputAdornment position="end">€</InputAdornment>,
                }}
                />
            </Grid>
        </Grid>
    );
}