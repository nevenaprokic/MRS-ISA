import * as React from "react";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import Paper from "@mui/material/Paper";
import Stepper from "@mui/material/Stepper";
import Step from "@mui/material/Step";
import StepLabel from "@mui/material/StepLabel";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import FirstPage from "./FirstPage";
import SecondPage from "./SecondPage";
import Review from "./Review";
import { userType } from "../../../../app/Enum";
import { useEffect, useState } from "react";
import { getCottageByCottageOwnerEmail } from "../../../../services/CottageService";
import {
  getRoleFromToken,
  getUsernameFromToken,
} from "../../../../app/jwtTokenUtils";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";
import {getAdventureByInstructorEmail} from '../../../../services/AdventureService';
import {getShipByShipOwnerEmail} from '../../../../services/ShipService';

const steps = [
  "Selection of clients",
  "Selection of additional attributes",
  "Review your reservation",
];

const theme = createTheme({
  palette: {
    primary: { main: "#ADC2A9" },
    secondary: { main: "#ffffff" },
  },
});




export default function NewReservationForm({ offers, setOffers }) {
  const [activeStep, setActiveStep] = React.useState(0);
  const [reservation, setReservation] = React.useState({
    clientUserName: "",
    clientName:"",
    clientLastName:"",
    offerName: "",
    offerId: 0,
    daysReservation: 0,
    startDateReservation: "",
    peopleNum: 0,
    price: 0,

  });
  const [additionalServicesInputList, setInputList] = useState([
    { serviceName: "", servicePrice: "" },
  ]);
  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm({});

  

  const handleNext = () => {
    setActiveStep(activeStep + 1);
  };

  const handleBack = () => {
    setActiveStep(activeStep - 1);
  };

  let getOffer = {
    [userType.COTTAGE_OWNER]: getCottageByCottageOwnerEmail,
    [userType.SHIP_OWNER] : getShipByShipOwnerEmail,
    [userType.INSTRUCTOR] : getAdventureByInstructorEmail
  };

  useEffect(() => {
    async function setDataOffer() {
      let role = getRoleFromToken();
      let username = getUsernameFromToken();
      const offersData = await getOffer[role](username);
      setOffers(offersData ? offersData.data : {});

      return offersData;
    }
    setDataOffer();
  }, []);

  function getStepContent(step) {
    switch (step) {
      case 0:
        return <FirstPage />;
      case 1:
        return (
          <SecondPage />
        );
      case 2:
        return (
          <Review />
        );
      default:
        throw new Error("Unknown step");
    }
  }
  return (
    !!offers && (
      <div style={{ width: "70vw" }}>
        <ThemeProvider theme={theme}>
          <Container
            component="main"
            maxWidth="sm"
            sx={{ mb: 4, border: "solid medium #99A799", borderRadius: "20px" }}
          >
            <Paper
              variant="outlined"
              sx={{ my: { xs: 3, md: 6 }, p: { xs: 2, md: 3 } }}
            >
              <Typography component="h1" variant="h4" align="center">
                New reservation for the client
              </Typography>
              <Stepper activeStep={activeStep} sx={{ pt: 3, pb: 5 }}>
                {steps.map((label) => (
                  <Step key={label}>
                    <StepLabel>{label}</StepLabel>
                  </Step>
                ))}
              </Stepper>
              <React.Fragment>
                {activeStep === steps.length ? (
                  <React.Fragment>
                    <Typography variant="h5" gutterBottom>
                      Thank you for your new reservation.
                    </Typography>
                    <Typography variant="subtitle1">
                      {"The new reservation will be presented with the '" +
                        reservation.name +
                        "' offer. The client " + reservation.clientName + " "+ reservation.clientLastName +" will be notified of the new reservation."}
                    </Typography>
                  </React.Fragment>
                ) : (
                  <React.Fragment>
                    {getStepContent(activeStep)}
                    <Box sx={{ display: "flex", justifyContent: "flex-end" }}>
                      {activeStep !== 0 && (
                        <Button onClick={handleBack} sx={{ mt: 3, ml: 1 }}>
                          Back
                        </Button>
                      )}

                      <Button
                        variant="contained"
                        onClick={handleNext}
                        sx={{ mt: 3, ml: 1 }}
                      >
                        {activeStep === steps.length - 1 ? "Create" : "Next"}
                      </Button>
                    </Box>
                  </React.Fragment>
                )}
              </React.Fragment>
            </Paper>
          </Container>
        </ThemeProvider>
      </div>
    )
  );
}
