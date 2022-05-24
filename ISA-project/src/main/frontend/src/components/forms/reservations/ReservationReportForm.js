import * as React from "react";
import Grid from "@mui/material/Grid";
import Typography from "@mui/material/Typography";
import TextField from "@mui/material/TextField";
import { Button } from "@mui/material";
import { useEffect } from "react";
import { useForm, useWatch } from "react-hook-form";
import "../../../style/ReportForm.scss";
import { AdapterDateFns } from "@mui/x-date-pickers/AdapterDateFns";
import { LocalizationProvider } from "@mui/x-date-pickers/LocalizationProvider";
import Radio from "@mui/material/Radio";
import RadioGroup from "@mui/material/RadioGroup";
import FormControlLabel from "@mui/material/FormControlLabel";
import FormControl from "@mui/material/FormControl";
import FormLabel from "@mui/material/FormLabel";

export default function ReservationReportForm() {
  const [value, setValue] = React.useState(true);
  const [valueOpinion, setOpinionValue] = React.useState("positive");

  const handleChangeYes = (event) => {
    setValue(event.target.value);
  };

  const handleChange = (event) => {
    setOpinionValue(event.target.value);
  };

  const handleSubmit = (e) => {};

  

  return (
    <LocalizationProvider dateAdapter={AdapterDateFns}>
      <div className="formContainer">
        <Typography variant="h6" gutterBottom>
          Report on the reservation made
        </Typography>
        <Typography gutterBottom>
          <label className="headerText">
            Fields marked with an asterisk (*) are required.
          </label>
        </Typography>
        <br></br>
        <Typography gutterBottom>
          <label className="question">Did the client show up? *</label>
        </Typography>
        <FormControl>
          <FormLabel id="demo-controlled-radio-buttons-group"></FormLabel>
          <RadioGroup
            aria-labelledby="demo-controlled-radio-buttons-group"
            name="controlled-radio-buttons-group"
            value={value}
            onChange={handleChangeYes}
          >
            <FormControlLabel value={true} control={<Radio />} label="Yes" />
            <FormControlLabel value={false} control={<Radio />} label="No" />
          </RadioGroup>
        </FormControl>

        <Typography gutterBottom>
          <label className="question">Impression of the client: *</label>
        </Typography>
        <FormControl>
          <FormLabel id="demo-controlled-radio-buttons-group"></FormLabel>
          <RadioGroup
            aria-labelledby="demo-controlled-radio-buttons-group"
            name="controlled-radio-buttons-group"
            value={valueOpinion}
            onChange={handleChange}
          >
            <FormControlLabel
              value={"positive"}
              control={<Radio />}
              label="Positive"
            />
            <FormControlLabel
              value={"negative"}
              control={<Radio />}
              label="Negative"
            />
            <FormControlLabel
              value={"no impression"}
              control={<Radio />}
              label="No impression"
            />
          </RadioGroup>
        </FormControl>
        <br />
        <br />
        <br />
        <Grid item xs={12}>
          <TextField
            id="comment"
            label="Comment"
            multiline
            rows={4}
            fullWidth
            defaultValue={""}
            required
          />
        </Grid>
        <Grid item xs={12} sm={4} sx={{ marginLeft: "65%" }}>
          <br />
          <Button
            type="submit"
            variant="contained"
            sx={{ float: "right" }}
            color="success"
            fullWidth
            onClick={handleSubmit}
          >
            Confirm
          </Button>
        </Grid>
      </div>
    </LocalizationProvider>
  );
}
