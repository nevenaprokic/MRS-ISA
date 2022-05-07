import * as React from "react";
import Grid from "@mui/material/Grid";
import { TextField } from "@mui/material";
import InputAdornment from "@mui/material/InputAdornment";
import Button from "@mui/material/Button";
import { useState } from "react";
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

    let searchOffer = {
        [offerType.COTTAGE]: searchCottagesClient,
        [offerType.SHIP]: searchShips,
        [offerType.ADVENTURE]: searchInstructors
      };

  return (
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
      {console.log(type)}

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
  );
}
