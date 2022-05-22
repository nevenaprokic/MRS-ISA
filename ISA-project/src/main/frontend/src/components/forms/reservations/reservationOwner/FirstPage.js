import * as React from "react";
import Grid from "@mui/material/Grid";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";


export default function FirstPage() {

  const handlerOfferChange = (event, selectedOffer) => {
    console.log(selectedOffer);
  };

  
  return (
    <React.Fragment>
      <Grid item xs={12}>
                <label style={{ marginLeft:"15%", color:"#CC7351", fontSize:"25px" }}>Clients who currently have a reservation with you.</label>
                <br/>
                <label style={{  marginLeft:"40%", color:"#CC7351", fontSize:"20px" }}>Please select!</label>
            </Grid>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <Autocomplete
              disablePortal
              id="combo-box-demo"
              options={["korisnik1", "korisnik2"]}
              required
              sx={{ width: 300, marginLeft: "30%", marginTop:"4%" }}
              onChange={(event, newValue) => {
                handlerOfferChange(event, newValue);
              }}
              renderInput={(params) => <TextField {...params} label="Client*" />}
            />
          </Grid>
          </Grid>
    </React.Fragment>
  );
}
