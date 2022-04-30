import "../../../style/AddAdventurePage.scss";
import * as React from "react";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import InputAdornment from "@mui/material/InputAdornment";
import { Button } from "@mui/material";
import AdditionalServices from "../addtitionaServices/AdditionalServices";
import { useState } from "react";
import UploadPictureForm from "../imageUpload/UploadPictureForm";
import { useForm } from "react-hook-form";

function AddShipPage() {
  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm({});
  const [additionalServicesInputList, setInputList] = useState([
    { serviceName: "", servicePrice: "" },
  ]);
  const [pictureInputList, pictureSetInputList] = useState([]);

  const onSubmit = (data) => {};

  return (
    <div className="formContainer">
      <Typography variant="h6" gutterBottom>
        Adding new ship
      </Typography>
      <Typography gutterBottom>
        <label className="headerText">
          Fields marked with an asterisk (*) are required.
        </label>
        <br />
        <label className="headerText">
          Prices are entered in euros with a maximum of two decimal places.
        </label>
      </Typography>
      <Grid
        container
        spacing={3}
        component="form"
        noValidate
        onSubmit={handleSubmit(onSubmit)}
        sx={{ marginTop: "2%" }}
      >
        <Grid item xs={12}>
          <TextField
            required
            id="offerName"
            label="Offer name"
            fullWidth
            defaultValue=""
          />
        </Grid>

        <Grid item xs={12} sm={6}>
          <TextField
            id="peopleNum"
            label="Maximum number of people"
            type="number"
            InputLabelProps={{
              shrink: true,
            }}
            fullWidth
            required
          />
        </Grid>

        <Grid item xs={12} sm={6}>
          <TextField
            fullWidth
            label="Price"
            id="price"
            type="number"
            required
            InputProps={{
              startAdornment: <InputAdornment position="end">€</InputAdornment>,
            }}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            id="type"
            label="Ship type"
            defaultValue=""
            InputLabelProps={{
              shrink: true,
            }}
            fullWidth
            required
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            id="size"
            label="Size"
            type="number"
            InputLabelProps={{
              shrink: true,
            }}
            fullWidth
            required
          />
        </Grid>

        <Grid item xs={12} sm={4}>
          <TextField
            fullWidth
            label="Numer of motors"
            id="motorNumber"
            type="number"
            required
            InputLabelProps={{
              shrink: true,
            }}
          />
        </Grid>
        <Grid item xs={12} sm={4}>
          <TextField
            id="motorPower"
            label="Motor power"
            type="number"
            InputLabelProps={{
              shrink: true,
            }}
            fullWidth
            required
          />
        </Grid>

        <Grid item xs={12} sm={4}>
          <TextField
            fullWidth
            label="Max speed"
            id="maxSpeed"
            type="number"
            required
            InputLabelProps={{
              shrink: true,
            }}
          />
        </Grid>

        <Grid item xs={12} sm={4}>
          <TextField
            required
            id="street"
            name="street"
            label="Street"
            fullWidth
          />
        </Grid>
        <Grid item xs={12} sm={4}>
          <TextField required id="city" name="city" label="City" fullWidth />
        </Grid>
        <Grid item xs={12} sm={4}>
          <TextField required id="state" name="state" label="State" fullWidth />
        </Grid>
        <Grid item xs={12}>
          <TextField
            required
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
          <TextField
            id="navigationEqupment"
            label="Navigation Equipment"
            multiline
            rows={4}
            defaultValue=""
            fullWidth
          />
        </Grid>
        <Grid item xs={12}>
          <UploadPictureForm
            pictureSetInputList={pictureSetInputList}
            pictureInputList={pictureInputList}
          />
        </Grid>
        <Grid item xs={12}>
          <AdditionalServices
            errors={errors}
            registerForm={register}
            inputList={additionalServicesInputList}
            setInputList={setInputList}
          />
        </Grid>
        <Grid item xs={12}>
          <TextField
            id="cancelationConditions"
            label="Cancellation conditions"
            multiline
            rows={3}
            defaultValue=""
            fullWidth
            required
          />
        </Grid>
        <Grid item xs={12} sm={4} sx={{ marginLeft: "35%" }}>
          <Button
            type="submit"
            variant="contained"
            sx={{ float: "right" }}
            color="success"
            fullWidth
          >
            Confirm
          </Button>
        </Grid>
      </Grid>
    </div>
  );
}

export default AddShipPage;
