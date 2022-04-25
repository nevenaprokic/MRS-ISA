import "../../../style/AddAdventurePage.scss" 
import * as React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';
import { Button } from "@mui/material";
import AdditionalServices from '../addtitionaServices/AdditionalServices';
import { useState } from "react";
import UploadPictureForm from "../imageUpload/UploadPictureForm";
import { useForm } from "react-hook-form";
import { addAdventure } from "../../../services/userService";
import { CssBaseline } from "@mui/material";
import MainNavigationHome from "../../layout/MainNavigationHome";
import { createTheme, ThemeProvider } from "@mui/material/styles";

//NAPRAVITI PRAZNE FORME ZA IZMENU SLIKA I ADITIONAL SERVICA ILI PROBATI POVEZATI SA POSTOJECIM

function ChangeAdventureForm({currrentAdventureData, closeForm}){

    const { register, handleSubmit, formState: { errors }, watch } = useForm({});
    const [additionalServicesInputList, setInputList] = useState([{ serviceName: "", servicePrice: "" }]);
    const [pictureInputList, pictureSetInputList] = useState([]);
    const [submitForm, setSubmitForm] = useState(false);

    const theme = createTheme({
        palette: {
          primary: { main: "#ADC2A9" },
          secondary: { main: "#ffffff" },
        },
      });

    const onSubmit = (data) => {
        data["photos"] = pictureInputList;
        data["additionalServices"] = additionalServicesInputList;
        console.log(data);
        // addAdventure(data);
        // setSubmitForm(true);
        //alert("Successfully added new adventure!"); //ovde kasnije zameniti sa lepsim popup-om
      }

    
  
    return (
        <ThemeProvider theme={theme}>
        <div className="changeAdventureForm">
        <div className="closeFormBtn">
            <Button size="large" sx={{}} onClick={() => closeForm()}>
            x
            </Button>
        </div>
        <div>
            <Typography variant="h6" gutterBottom>
                Change adventure
            </Typography>
        </div>
       
        <Grid container spacing={3} component="form" noValidate onSubmit={handleSubmit(onSubmit)} sx={{marginTop:"2%"}}>
                <Grid item xs={12}>            
                    <TextField
                        id="offerName"
                        label="Offer name" 
                        fullWidth 
                        defaultValue=""
                                    
                    />
                </Grid>

                <Grid item xs={12} sm={6} >
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
                <Grid item xs={12} sm={6}>
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
                

                <Grid item xs={12} sm={4}>
                    <TextField
                    id="street"
                    name="street"
                    label="Street"
                    fullWidth  
                    />
                </Grid>
                <Grid item xs={12} sm={4}>
                    <TextField
                    id="city"
                    name="city"
                    label="City"
                    fullWidth
                    />
                </Grid>
                <Grid item xs={12} sm={4}>
                    <TextField
                    id="state"
                    name="state"
                    label="State"
                    fullWidth
                    />
                </Grid>    
                <Grid item xs={12}>
                    <TextField
                    id="description"
                    label="Description"
                    multiline
                    rows={4}
                    defaultValue=""
                    fullWidth
                    />
                    </Grid>
                <Grid item xs={12}>
                    <TextField
                    id="rulesOfConduct"
                    label="Rules of conduct"
                    multiline
                    rows={4}
                    defaultValue=""
                    fullWidth
                    />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                    id="additionalEquipment"
                    label="Additional Equipment"
                    multiline
                    rows={4}
                    defaultValue=""
                    fullWidth
                    />
                </Grid>
                <Grid item xs={12}>
                    <UploadPictureForm pictureSetInputList={pictureSetInputList} pictureInputList={pictureInputList}/>
                </Grid>
                <Grid  item xs={12}>
                    <AdditionalServices errors={errors} registerForm={register} inputList={additionalServicesInputList} setInputList={setInputList} />
                </Grid>
                <Grid item xs={12}>
                    <TextField
                    id="cancelationConditions"
                    label="Cancellation conditions"
                    multiline
                    rows={3}
                    defaultValue=""
                    fullWidth
                    />
                </Grid>
                <Grid item xs={12} sm={4} sx={{marginLeft:"35%"}}>
                    <Button 
                        type="submit"
                        variant="contained"
                        sx={{float:"right"}}
                        fullWidth
                        color="primary"
                    >
                    Confirm 
                    </Button>
                </Grid>

            </Grid>
            
        </div>
        </ThemeProvider>
    );

}

export default ChangeAdventureForm;