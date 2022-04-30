import * as React from 'react';
import Grid from '@mui/material/Grid';
import { styled } from '@mui/material/styles';
import Paper from '@mui/material/Paper';
import { TextField } from '@mui/material';
import InputAdornment from '@mui/material/InputAdornment';
import Button from '@mui/material/Button';
import { searchCottages } from '../../../services/CottageService';
import { searchShips } from '../../../services/ShipService';
import { offerType, searchInstructors } from '../../../services/userService';
import { useState } from 'react';


export default function Search({params, setParams, type, setOffers}){

    const [error, setError] = useState('');

    let searchOffer = {
            [offerType.COTTAGE] :  searchCottages,
            [offerType.SHIP]: searchShips,
            [offerType.ADVENTURE] :  searchInstructors,
    }

    const handleChange = (event) => {
        let {target: {value}} = event;
        console.log(value);
        let reg = new RegExp(/^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/).test(value);
        console.log(reg);
        if(!reg){
            console.log("set err")
            setError('Wrong format for phone number');
        }else{
            setError('');
            setParams({...params, "price": event.target.value});
        }
    }

    return(
        <Grid container spacing={5}>
           
           <Grid item xs>
            {
                (type == offerType.ADVENTURE) ? (
                    <TextField
                        id="firstName"
                        label="First Name"
                        onChange = { event => { setParams({...params, "firstName": event.target.value}); }}
                        InputLabelProps={{
                        shrink: true,
                        }}
                        fullWidth
                    />
                ):(
                    <TextField
                        id="name"
                        label="Name"
                        defaultValue=""
                        InputLabelProps={{
                            shrink: true,
                            }}
                        onChange = { event => { setParams({...params, "name": event.target.value}); }}
                    />
                )
            }
            </Grid>

            <Grid item xs>
            {
                (type == offerType.ADVENTURE) ? (
                    <TextField
                        id="lastName"
                        label="Last Name"
                        defaultValue=""
                        InputLabelProps={{
                            shrink: true,
                            }}
                        onChange = { event => { setParams({...params, "lastName": event.target.value}); }}
                    />
                ):(
                    <TextField 
                        fullWidth
                        label="Price"
                        id="price"
                        type="number"
                        onChange = { event => {  }}
                        InputProps={{
                        startAdornment: <InputAdornment position="end">€</InputAdornment>,
                        }}
                    /> 
                )
            }

            </Grid>

            <Grid item xs>
            {
                (type == offerType.ADVENTURE) ? (
                    <>
                        <TextField
                        id="phone"
                        label="Phone number"
                        onChange = { event => handleChange(event) }
                        InputLabelProps={{
                        shrink: true,
                        }}
                        fullWidth
                        />
                        {error != '' && <p style={{color:'#ED6663'}}>Please check the Phone Number</p>}
                    </>
                ):(
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
                )
            }
            </Grid>

            <Grid item xs>
                <TextField
                id="address"
                label="Address"
                defaultValue=""
                InputLabelProps={{
                    shrink: true,
                    }}
                onChange = { event => { setParams({...params, "address": event.target.value}); }}
                />
            </Grid>

            <Grid item xs>
                <Button size="large" sx={{}} onClick={ () => searchOffer[type](params, setOffers) } >Search</Button>
            </Grid>
        </Grid>
    );
}