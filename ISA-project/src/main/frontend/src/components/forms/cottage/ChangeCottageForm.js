import "../../../style/AddAdventurePage.scss" 
import * as React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import InputAdornment from '@mui/material/InputAdornment';
import { Button } from "@mui/material";
import AdditionalServices from '../addtitionaServices/AdditionalServices';
import { useState } from "react";
import ChangeImageForm from "../imageUpload/ChangeImageForm";
import { useForm } from "react-hook-form";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useEffect } from "react";



function ChangeCottageForm({currentCottageData, closeForm, childToParent}){
    const { register, handleSubmit, formState: { errors }, watch } = useForm({});
    const [additionalServicesInputList, setInputList] = useState([{ serviceName: "", servicePrice: "" }]);
    const [images, setImages] = useState([]);
    console.log("IME");
    console.log(currentCottageData.name);
    useEffect(() => {
        async function setCurrentData(){
            let images = [];
            currentCottageData.photos.forEach(photo => {
                let path = photo;
                images.push(path);
            });
            setImages(images);
            setCurrentAdditionalServices();
        }
        setCurrentData();

    }, []);
  

    const theme = createTheme({
        palette: {
          primary: { main: "#ADC2A9" },
          secondary: { main: "#ffffff" },
        },
      });

    const onSubmit = (data) => {
        
        closeForm();

    }

    function setCurrentAdditionalServices(){
        if(currentCottageData.additionalServices.length === 0){
            setInputList([{ serviceName: "", servicePrice: "" }])
        }
        else{
            setInputList(currentCottageData.additionalServices);
        }
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
                Change cottage
            </Typography>
        </div>
       
        <Grid container spacing={3} component="form" noValidate onSubmit={handleSubmit(onSubmit)} sx={{marginTop:"2%"}}>
                <Grid item xs={12}>            
                    <TextField
                        id="offerName"
                        label="Offer name" 
                        fullWidth 
                        defaultValue={currentCottageData.name}
                    
                    />
                   
                </Grid>

                <Grid item xs={12} sm={6} >
                <TextField
                    id="peopleNum"
                    label="Maximum number of people"
                    type="number"
                    InputLabelProps={{
                    shrink: true,
                    }}
                    fullWidth
                    defaultValue={currentCottageData.numberOfPerson} 
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
                    defaultValue={currentCottageData.price}
                />
                </Grid>
                <Grid item xs={12} sm={6} >
                <TextField
                    id="bedNumber"
                    label="Number of beds"
                    type="number"
                    InputLabelProps={{
                    shrink: true,
                    }}
                    fullWidth
                    defaultValue={currentCottageData.bedNumber} 
                />
                </Grid>
                <Grid item xs={12} sm={6} >
                <TextField
                    id="roomNumber"
                    label="Number of rooms"
                    type="number"
                    InputLabelProps={{
                    shrink: true,
                    }}
                    fullWidth
                    defaultValue={currentCottageData.roomNumber} 
                />
                </Grid>

                <Grid item xs={12} sm={4}>
                    <TextField
                    id="street"
                    name="street"
                    label="Street"
                    fullWidth  
                    defaultValue={currentCottageData.street}
                    />
                </Grid>
                <Grid item xs={12} sm={4}>
                    <TextField
                    id="city"
                    name="city"
                    label="City"
                    fullWidth
                    defaultValue={currentCottageData.city}
                    />
                </Grid>
                <Grid item xs={12} sm={4}>
                    <TextField
                    id="state"
                    name="state"
                    label="State"
                    fullWidth
                    defaultValue={currentCottageData.state}
                    />
                </Grid>    
                <Grid item xs={12}>
                    <TextField
                    id="description"
                    label="Description"
                    multiline
                    rows={4}
                    fullWidth
                    defaultValue={currentCottageData.description}
                    />
                    </Grid>
                <Grid item xs={12}>
                    <TextField
                    id="rulesOfConduct"
                    label="Rules of conduct"
                    multiline
                    rows={4}
                    fullWidth
                    defaultValue={currentCottageData.rulesOfConduct}
                    />
                </Grid>
                <Grid item xs={12}>
                    <ChangeImageForm images={images} setImages={setImages} childToParent={childToParent}/>
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
                    fullWidth
                    defaultValue={currentCottageData.cancellationConditions}
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

export default ChangeCottageForm;