import * as React from "react";
import Grid from "@mui/material/Grid";
import { TextField } from "@mui/material";
import { MobileDatePicker } from '@mui/x-date-pickers/MobileDatePicker';
import Button from "@mui/material/Button";
import { useState, useEffect } from "react";
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import {
    searchCottagesClient
} from "../../../services/CottageService";
import {
  searchShipsClient
} from "../../../services/ShipService";
import { offerType } from "../../../app/Enum";
import { searchInstructors } from "../../../services/InstructorService";

export default function ClientSearch({ params, setParams, type, setOffers, setLastSearchedOffers }) {

  const [valueFrom, setValueFrom] = React.useState();
  const [valueTo, setValueTo] = React.useState();

  const handleChangeFrom = (newValue) => {
    // newValue = newValue.toLocaleDateString("en-US");
    setValueFrom(newValue);
    setParams({ ...params, dateFrom: newValue});
  };

  const handleChangeTo = (newValue) => {
    // newValue = newValue.toLocaleDateString("en-US");
    setValueTo(newValue);
    setParams({ ...params, dateTo: newValue});
  };

  const handleSubmit = () => {
    searchOffer[type](params, setOffers, setLastSearchedOffers);
  };

    let searchOffer = {
        [offerType.COTTAGE]: searchCottagesClient,
        [offerType.SHIP]: searchShipsClient,
        [offerType.ADVENTURE]: searchInstructors
      };

    useEffect(() => {
      let tommotowDate = new Date();
      tommotowDate.setDate(tommotowDate.getDate() + 1);
      setValueFrom(tommotowDate);
      setValueTo(tommotowDate);
        
      }, [])

  return (
    <LocalizationProvider dateAdapter={AdapterDateFns}>
    <Grid container spacing={5}>
      <Grid item xs>
        {type == offerType.ADVENTURE ? (
          <TextField
            id="firstName"
            label="First Name"
            onChange={(event) => {
              setParams({ ...params, firstName: event.target.value });
            }}
            InputLabelProps={{
              shrink: true,
            }}
            fullWidth
          />
        ) : (
          <TextField
            id="name"
            label="Name"
            defaultValue=""
            InputLabelProps={{
              shrink: true,
            }}
            onChange={(event) => {
              setParams({ ...params, name: event.target.value });
            }}
          />
        )}
      </Grid>

      <Grid item xs>
        {type == offerType.ADVENTURE ? (
          <TextField
            id="lastName"
            label="Last Name"
            defaultValue=""
            InputLabelProps={{
              shrink: true,
            }}
            onChange={(event) => {
              setParams({ ...params, lastName: event.target.value });
            }}
          />
        ) : (
            <TextField
            id="description"
            label="Description"
            defaultValue=""
            InputLabelProps={{
              shrink: true,
            }}
            onChange={(event) => {
              setParams({ ...params, description: event.target.value });
            }}
          />
        )}
      </Grid>

      <Grid item xs>
        <TextField
          id="address"
          label="Address"
          defaultValue=""
          InputLabelProps={{
            shrink: true,
          }}
          onChange={(event) => {
            setParams({ ...params, address: event.target.value });
          }}
        />
      </Grid>

      <Grid item xs>
        <Button
          size="large"
          sx={{}}
          onClick={() => handleSubmit()}
        >
          Search
        </Button>
      </Grid>
    </Grid>
    <br/>
    <Grid container spacing={5}>
      <Grid item>
        <MobileDatePicker
            label="From"
            inputFormat="dd/MM/yyyy"
            value={valueFrom}
            onChange={handleChangeFrom}
            renderInput={(params) => <TextField {...params} />}
          />
      </Grid>
      <Grid item>
        <MobileDatePicker
            label="To"
            inputFormat="dd/MM/yyyy"
            value={valueTo}
            onChange={handleChangeTo}
            renderInput={(params) => <TextField {...params} />}
          />
      </Grid>
    </Grid>
    </LocalizationProvider>
  );
}
