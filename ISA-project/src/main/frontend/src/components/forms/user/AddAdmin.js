import * as React from 'react';
import Button from '@mui/material/Button';
import CssBaseline from '@mui/material/CssBaseline';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import { FormControl } from '@mui/material';
import { useForm } from "react-hook-form";
import Input from '@mui/material/Input';
import FormHelperText from '@mui/material/FormHelperText';
import { getUsernameFromToken } from '../../../app/jwtTokenUtils';
import api from '../../../app/api';
import { toast } from "react-toastify";

function  AddAdmin(){

    const theme = createTheme({
        palette: {
          primary: {
            main: "#5f6d5f",
          },
          secondary: {
            main: "#CC7351",
          },
        },
      });
    
    const { register, handleSubmit, formState: { errors }, watch } = useForm({});

    const onSubmit = (data) => {
        console.log(data);
    }

    return (
        <ThemeProvider theme={theme}>
        <div className="AddAdminFormContainer" id="changeDataContainer">
            <Box
              sx={{
                marginTop: 0,
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                width: "120%",
                paddingLeft: '20',
                paddingRight: '20',
        
              }}
            >
                <div><br/></div>
                <div className="header">
                    <div className='tittle'>
                        <Typography component="h1" variant="h5" sx={{color:"#CC7351"}}>
                          Add new admin
                        </Typography>
                    </div>
                   
                  </div>
               
              <Box component="form" noValidate onSubmit={handleSubmit(onSubmit)} sx={{ mt: 3 }}>
                <Grid container spacing={2}>
                <FormControl variant="standard" sx={{ m: 1, mt: 3, width: '25ch' }}>
                    <Input 
                    name="email"
                    id="email"
                     {...register("email", {pattern: /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/, required:true })}
                     />
                  <FormHelperText id="standard-weight-helper-text">Email address</FormHelperText>
                 
                </FormControl>
                <FormControl variant="standard" sx={{ m: 1, mt: 3, width: '25ch' }}>
                    <Input 
                    name="firstName"
                    id="firstName"
                     {...register("firstName")}
                     />
                  <FormHelperText id="standard-weight-helper-text">First Name</FormHelperText>
                  
                </FormControl>
                <FormControl variant="standard" sx={{ m: 1, mt: 3, width: '25ch' }}>
                    <Input 
                    name="lastName"
                    id="lastName"
                     {...register("lastName")}/>
                  <FormHelperText id="standard-weight-helper-text">Last Name</FormHelperText>
                
                </FormControl>
                <FormControl variant="standard" sx={{ m: 1, mt: 3, width: '25ch' }}>
                    <Input 
                    name="phoneNumber"
                    id="phoneNumber"
                     {...register("phoneNumber")}/>
                  <FormHelperText id="standard-weight-helper-text">Phone number</FormHelperText>
                  
                </FormControl>   
                <FormControl variant="standard" sx={{ m: 1, mt: 3, width: '25ch' }}>
                    <Input 
                    name="street"
                    id="street"
                     {...register("street")}/>
                  <FormHelperText id="standard-weight-helper-text">Street</FormHelperText>
                 
                </FormControl>   
                <FormControl variant="standard" sx={{ m: 1, mt: 3, width: '25ch' }}>
                    <Input 
                    name="city"
                    id="city"
                     {...register("city")}/>
                  <FormHelperText id="standard-weight-helper-text">City</FormHelperText>
                  
                </FormControl>   
                <FormControl variant="standard" sx={{ m: 1, mt: 3, width: '25ch' }}>
                    <Input 
                    name="state"
                    id="state"
                     {...register("state")}/>
                  <FormHelperText id="standard-weight-helper-text">State</FormHelperText>
                  
                </FormControl>                 
                
                </Grid>
                <Button
                  type="submit"
                  
                  variant="contained"
                  sx={{ mt: 3, mb: 2, ml:'35%'}}
                >
                  Confirm
                </Button>
                
                <div><br/></div>
              </Box>
            </Box>
         
          </div>
        </ThemeProvider>
    );
}

export default AddAdmin;