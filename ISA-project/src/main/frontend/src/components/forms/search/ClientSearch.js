import * as React from "react";
import Grid from "@mui/material/Grid";
import { TextField } from "@mui/material";
import { MobileDatePicker } from '@mui/x-date-pickers/MobileDatePicker';
import Button from "@mui/material/Button";
import { useState } from "react";
import { AdapterDateFns } from '@mui/x-date-pickers/AdapterDateFns';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import {
    searchCottagesClient
} from "../../../services/CottageService";
import {
  searchShips
} from "../../../services/ShipService";
import { offerType } from "../../../app/Enum";
import { searchInstructors } from "../../../services/InstructorService";

export default function ClientSearch({ params, setParams, type, setOffers }) {
    const [error, setError] = useState("");

  const [valueFrom, setValueFrom] = React.useState();
  const [valueTo, setValueTo] = React.useState();

  const handleChangeFrom = (newValue) => {
    newValue = newValue.toLocaleDateString("en-US");
    setValueFrom(newValue);
    setParams({ ...params, dateFrom: newValue});
  };

  const handleChangeTo = (newValue) => {
    newValue = newValue.toLocaleDateString("en-US");
    setValueTo(newValue);
    setParams({ ...params, dateTo: newValue});
  };


    let searchOffer = {
        [offerType.COTTAGE]: searchCottagesClient,
        [offerType.SHIP]: searchShips,
        [offerType.ADVENTURE]: searchInstructors
      };

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
          onClick={() => searchOffer[type](params, setOffers)}
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
            // onChange={(event) => {
            //   handleChangeFrom(event);
            //   console.log("AAA");
            //   setParams({ ...params, dateFrom: event.target.value });
            // }}
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
