import * as React from "react";
import Typography from "@mui/material/Typography";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import InputAdornment from "@mui/material/InputAdornment";
import BoyIcon from "@mui/icons-material/Boy";
import AdditionalServices from "../../addtitionaServices/AdditionalServices";
import { useForm } from "react-hook-form";
import { useState } from "react";
import '../../../../style/ReservationForm.scss';
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import NumbersIcon from "@mui/icons-material/Numbers";
import { DesktopDatePicker } from "@mui/x-date-pickers/DesktopDatePicker";
import Checkbox from '@mui/material/Checkbox';
import IconButton from '@mui/material/IconButton';
import ListItemButton from '@mui/material/ListItemButton';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import ClearIcon from '@mui/icons-material/Clear';
import AddCircleIcon from '@mui/icons-material/AddCircle';


const additionalServices = [
  { serviceName: 'Klima ', servicePrice: '10' },
  { serviceName: 'Dorucak', servicePrice: '4' },
  { serviceName: 'Vecera', servicePrice: '5'},
  { serviceName: 'Rucak', servicePrice: '7'},
]

export default function SecondPage() {
  const [value, setValue] = React.useState(new Date());
  const [valueReservation, setValueReservation] = React.useState(new Date());
  const [checked, setChecked] = React.useState([]);
  const [total, setTotal] = React.useState(0);


  const handleChangePeopleNum = (e) => {
    console.log(e.target.value);
  };
  const handleChangePrice = (e) => {
    console.log(e.target.value);
  };

  const handleChange = (newValue) => {
    setValue(newValue);

  };

  const handleChangeReservationDate = (newValue) => {
   console.log(newValue);
   setValueReservation(newValue);
  };
 
  const handleChangeNumberReservation = (e) => {
   console.log(e.target.value);
    
  };


  const handleToggle = (value) => () => {
    const currentIndex = checked.indexOf(value);
    const newChecked = [...checked];

    if (currentIndex === -1) {
      newChecked.push(value);
    } else {
      newChecked.splice(currentIndex, 1);
    }
    setChecked(newChecked);
  };
  return (
    <React.Fragment>
      <LocalizationProvider dateAdapter={AdapterDateFns}>
      <Grid container spacing={3}>
      <Grid item xs={12}>
                <label style={{ marginLeft:"20%",marginBottom:"5%", color:"#CC7351", fontSize:"25px"  }}>{"The reservation offer is " + "Sumska vila" + "."}</label>
            </Grid>
      <Grid item xs={12} sm={4}>
          <DesktopDatePicker
            label="Starting date*"
            inputFormat="yyyy-MM-dd"
            value={valueReservation}
            required
            onChange={handleChangeReservationDate}
            renderInput={(params) => <TextField {...params} />}
          />
        </Grid>

        <Grid item xs={12} sm={4}>
          <TextField
            id="daysReservation"
            name="second"
            label="Number of days"
            type="number"
            InputLabelProps={{
              shrink: true,
            }}
            fullWidth
            required
            onChange={handleChangeNumberReservation}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <NumbersIcon />
                </InputAdornment>
              ),
            }}
          />
        </Grid>
        <Grid item xs={12} sm={4}>
          <TextField
            fullWidth
            label="Number of guests"
            id="guests"
            type="number"
            required
            onChange={handleChangePeopleNum}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <BoyIcon />
                </InputAdornment>
              ),
            }}
          />
        </Grid>
        <Grid item xs={12}>
                <label style={{marginBottom:"5%", color:"#CC7351", fontSize:"23px"  }}>{"Basic price:  " + "100" + "€"}</label>
                <br></br>
                <label style={{marginBottom:"5%", color:"#CC7351", fontSize:"15px"  }}>{"Choose additional services!"}</label>
            </Grid>
        <Grid item xs={12}>
                {(additionalServices.length == 0) ? (
                  <Typography variant="h6" gutterBottom color="#CC7351">
                    No additional services <ClearIcon style={{ verticalAlign: '-6',  color:"#CC7351" }}/>
                  </Typography>
                  ) : (
                  <>
                    <Typography variant="h7" gutterBottom color="#CC7351">
                    Additional services <AddCircleIcon style={{ verticalAlign: '-6',  color:"#CC7351" }}/>
                  </Typography>
                    
                <List sx={{ width: '60%', bgcolor: 'background.paper' }}>
                  {additionalServices.map((value) => {
                    const labelId = `checkbox-list-label-${value}`;
                    return (
                      <ListItem
                        key={"Sumska vila"}
                        secondaryAction={
                          <IconButton edge="end" aria-label="comments">
                          </IconButton>
                        }
                        disablePadding
                      >
                        <ListItemButton role={undefined} onClick={handleToggle(value)} dense>
                          <ListItemIcon>
                            <Checkbox
                              edge="start"
                              checked={checked.indexOf(value) !== -1}
                              tabIndex={-1}
                              disableRipple
                              inputProps={{ 'aria-labelledby': labelId }}
                              />
                      </ListItemIcon>
                    <ListItemText id={labelId} secondary={`${value.serviceName}`} /><ListItemText className="list-item" id={labelId} primary={` ${value.servicePrice}€`} />
                  </ListItemButton>
                    </ListItem>
                    );
                  })}
                </List>
                  </>
                )}
                </Grid>
      </Grid>
      </LocalizationProvider>
    </React.Fragment>
  );
}
//key={value.name + offer.id}
