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
//dodatne usluge

export default function PaymentForm() {
  const [value, setValue] = React.useState(new Date());
  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm({});
  const [additionalServicesInputList, setInputList] = useState([
    { serviceName: "", servicePrice: "" },
  ]);

  const handleChange = (newValue) => {
    setValue(newValue);
  };
  return (
    <React.Fragment>
      <Grid container spacing={3}>
        <Grid item xs={12} sm={6}>
          <TextField
            fullWidth
            label="Number of guests"
            id="guests"
            type="number"
            required
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <BoyIcon />
                </InputAdornment>
              ),
            }}
          />
        </Grid>
        <Grid item xs={12} sm={6}>
          <TextField
            fullWidth
            label="Price per day"
            id="price"
            type="number"
            required
            InputProps={{
              startAdornment: <InputAdornment position="end">€</InputAdornment>,
            }}
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
      </Grid>
    </React.Fragment>
  );
}
