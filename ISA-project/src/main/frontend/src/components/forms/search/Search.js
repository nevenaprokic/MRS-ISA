import * as React from 'react';
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import { TextField } from '@mui/material';
import InputAdornment from '@mui/material/InputAdornment';


export default function Search(){
    return(
        <Grid container spacing={5}>
           
            <Grid item xs>
                <TextField
                id="cottage name"
                label="Cottage name"
                defaultValue=" "/>
                
            </Grid>
            <Grid item xs>
            <TextField
                
                id="peopleNum"
                label="Maximum nuber of people"
                type="number"
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
                defaultValue=" "/>
                
            </Grid>
            <Grid item xs>
            <TextField 
                fullWidth
                label="Price"
                id="price"
                type="number"
                InputProps={{
                  startAdornment: <InputAdornment position="end">€</InputAdornment>,
                }}
                />
            </Grid>
        </Grid>
    );
}