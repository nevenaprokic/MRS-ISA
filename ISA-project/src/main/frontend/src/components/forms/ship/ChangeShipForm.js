import "../../../style/AddAdventurePage.scss";
import * as React from "react";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import InputAdornment from "@mui/material/InputAdornment";
import { Button } from "@mui/material";
import AdditionalServices from "../addtitionaServices/AdditionalServices";
import { useState } from "react";
import ChangeImageForm from "../imageUpload/ChangeImageForm";
import { useForm } from "react-hook-form";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useEffect } from "react";

function ChangeShipForm({ currentShipData, closeForm, childToParent }) {
  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm({});
  const [additionalServicesInputList, setInputList] = useState([
    { serviceName: "", servicePrice: "" },
  ]);
  const [images, setImages] = useState([]);

  useEffect(() => {
    async function setCurrentData() {
      let images = [];
      currentShipData.photos.forEach((photo) => {
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
  };

  function setCurrentAdditionalServices() {
    if (currentShipData.additionalServices.length === 0) {
      setInputList([{ serviceName: "", servicePrice: "" }]);
    } else {
      setInputList(currentShipData.additionalServices);
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
            Change ship
          </Typography>
        </div>

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
              id="offerName"
              label="Offer name"
              fullWidth
              defaultValue={currentShipData.name}
            />
          </Grid>

          <Grid item xs={12} sm={6}>
            <TextField
              id="numberOfPerson"
              label="Maximum nuber of people"
              type="number"
              InputLabelProps={{
                shrink: true,
              }}
              fullWidth
              defaultValue={currentShipData.numberOfPerson}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              fullWidth
              label="Price"
              id="price"
              type="number"
              InputProps={{
                startAdornment: (
                  <InputAdornment position="end">€</InputAdornment>
                ),
              }}
              defaultValue={currentShipData.price}
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="type"
              label="Ship type"
              InputLabelProps={{
                shrink: true,
              }}
              defaultValue={currentShipData.type}
              fullWidth
              required
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              id="size"
              label="Size"
              type="number"
              defaultValue={currentShipData.size}
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
              defaultValue={currentShipData.motorNumber}
            />
          </Grid>
          <Grid item xs={12} sm={4}>
            <TextField
              id="motorPower"
              label="Motor power"
              type="number"
              defaultValue={currentShipData.motorPower}
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
              defaultValue={currentShipData.maxSpeed}
            />
          </Grid>

          <Grid item xs={12} sm={4}>
            <TextField
              id="street"
              name="street"
              label="Street"
              fullWidth
              defaultValue={currentShipData.street}
            />
          </Grid>
          <Grid item xs={12} sm={4}>
            <TextField
              id="city"
              name="city"
              label="City"
              fullWidth
              defaultValue={currentShipData.city}
            />
          </Grid>
          <Grid item xs={12} sm={4}>
            <TextField
              id="state"
              name="state"
              label="State"
              fullWidth
              defaultValue={currentShipData.state}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              id="description"
              label="Description"
              multiline
              rows={4}
              fullWidth
              defaultValue={currentShipData.description}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              id="rulesOfConduct"
              label="Rules of conduct"
              multiline
              rows={4}
              fullWidth
              defaultValue={currentShipData.rulesOfConduct}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              id="additionalEquipment"
              label="Additional Equipment"
              multiline
              rows={4}
              fullWidth
              defaultValue={currentShipData.additionalEquipment}
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              id="navigationEquipment"
              label="Navigation Equipment"
              multiline
              rows={4}
              defaultValue={currentShipData.navigationEquipment}
              fullWidth
            />
          </Grid>
          <Grid item xs={12}>
            <ChangeImageForm
              images={images}
              setImages={setImages}
              childToParent={childToParent}
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
              fullWidth
              defaultValue={currentShipData.cancelationConditions}
            />
          </Grid>
          <Grid item xs={12} sm={4} sx={{ marginLeft: "35%" }}>
            <Button
              type="submit"
              variant="contained"
              sx={{ float: "right" }}
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

export default ChangeShipForm;
