import * as React from "react";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import FormControlLabel from "@mui/material/FormControlLabel";
import Checkbox from "@mui/material/Checkbox";
import Autocomplete from "@mui/material/Autocomplete";
import NumbersIcon from "@mui/icons-material/Numbers";
import { DesktopDatePicker } from "@mui/x-date-pickers/DesktopDatePicker";
import InputAdornment from "@mui/material/InputAdornment";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";

export default function AddressForm() {
  const [value, setValue] = React.useState(new Date());

  const handlerOfferChange = (event, selectedOffer) => {
    // async function set(){
    //   console.log("naziv",selectedOffer.label);
    //   const eventsData = await getCalendarEvents(selectedOffer.id, setEvents);
    //   let calendarItems = generateCalendarEvents(eventsData.data);
    //   setEvents(calendarItems);
    //   setOfferId(selectedOffer.id);
    //   setOfferName(selectedOffer.label);
    //   return eventsData;
    // }
    // set();
  };

  const handleChange = (newValue) => {
    setValue(newValue);
  };

  return (
    <React.Fragment>
      <LocalizationProvider dateAdapter={AdapterDateFns}>
      <Grid item xs={12}>
                <label style={{ marginLeft: "35%", color:"#CC7351" }}>Special offer</label>
            </Grid>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <Autocomplete
              disablePortal
              id="combo-box-demo"
              options={["neka", "naka"]}
              required
              sx={{ width: 300, marginLeft: "22%", marginTop:"4%" }}
              onChange={(event, newValue) => {
                handlerOfferChange(event, newValue);
              }}
              renderInput={(params) => <TextField {...params} label="Offer*" />}
            />
          </Grid>
          <Grid item xs={12}>
                <label style={{ marginLeft: "35%" , color:"#CC7351"}}> Period of action</label>
            </Grid>
          <Grid item xs={12} sm={6}>
            <DesktopDatePicker
              label="Starting date*"
              inputFormat="yyyy-MM-dd"
              value={value}
              required
              onChange={handleChange}
              renderInput={(params) => <TextField {...params} />}
            />
          </Grid>

          <Grid item xs={12} sm={6}>
            <TextField
              id="days"
              name="second"
              label="Number of days"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              fullWidth
              required
              InputProps={{
                startAdornment: (
                  <InputAdornment position="start">
                    <NumbersIcon />
                  </InputAdornment>
                ),
              }}
            />
          </Grid>
          <Grid item xs={12}>
                <label style={{ marginLeft: "35%", color:"#CC7351" }}>  Reservation period</label>
            </Grid>
          
        <Grid item xs={12} sm={6}>
          <DesktopDatePicker
            label="Starting date*"
            inputFormat="yyyy-MM-dd"
            value={value}
            required
            onChange={handleChange}
            renderInput={(params) => <TextField {...params} />}
          />
        </Grid>

        <Grid item xs={12} sm={6}>
          <TextField
            id="days"
            name="second"
            label="Number of days"
            type="number"
            InputLabelProps={{
              shrink: true,
            }}
            fullWidth
            required
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <NumbersIcon />
                </InputAdornment>
              ),
            }}
          />
        </Grid>
        </Grid>
      </LocalizationProvider>
    </React.Fragment>
  );
}
