import { Button } from "@mui/material";
import { createTheme } from "@mui/material/styles";
import { getRoleFromToken } from "../../../app/jwtTokenUtils";
import "./CottageProfilePage.scss";
import { userType, offerType} from "../../../app/Enum";
import * as React from "react";
import { useState, useEffect } from "react";
import getQuickActionByOfferId from "../../../services/QuickActionService";
import CalendarMonthIcon from '@mui/icons-material/CalendarMonth';
import { makeReservation, convertParams } from '../../../services/ReservationService';

const theme = createTheme({
  palette: {
    primary: {
      main: "#99A799",
    },
    secondary: {
      main: "#ADC2A9",
    },
  },
});

function QuickActionBox({ id }) {
  const [quickActionData, setQuickActionsData] = useState();


  const handleReservation = (action) => {
    makeReservation(convertParams(action, id));
  };

  useEffect(() => {
    async function setData() {
      let quickActions = await getQuickActionByOfferId(id);
      setQuickActionsData(!!quickActions ? quickActions.data : {});
      return quickActions;
    }
    setData();
  }, []);

  if (quickActionData) {
    return (
      <div className="specialOffersContainer">
        <div className="specialOffersTitle">
          <label className="tittle">Special offers:</label>
          <hr className="tittleLine"></hr>
        </div>

        <div className="specialOfferSrollBox">
          {quickActionData?.map((action) => {
              console.log(action);
              let startDate = " " + action.startDate[2] + "." + action.startDate[1] + "." + action.startDate[0]+".";
              let endDate = " " + action.endDate[2] + "." + action.endDate[1] + "." + action.endDate[0]+".";
              let startDateAction = " " + action.startDateAction[2] + "." + action.startDateAction[1] + "." + action.startDateAction[0]+".";
              let endDateAction = " " + action.endDateAction[2] + "." + action.endDateAction[1] + "." + action.endDateAction[0]+".";
            return (
              <div className="scolledItemsContainer">
                <h3 className="actionTittle">
                  Date of stay :{startDate} {" - "} {endDate}
                </h3>
                <div>
                  <label className="stayDate">
                    Maximum number of people: {action.numberOfPerson} 
                  </label>

                <div>
                  { (action.additionalServices.length == 0) ? <label>No additional services for this offer</label> :
                   <label>Additional services: {action.additionalServices}</label>}
                </div>
                <br/>
                <div className="availableDate">
                    <label> <CalendarMonthIcon style={{ verticalAlign: '-6' }}/> Action available: </label>
                    <label>
                      {startDateAction} {" - "} {endDateAction}
                    </label>
                  </div>
                </div>
                <br></br>
                <label className="priceItem">Price: {action.price} €</label>
                { (getRoleFromToken() == userType.CLIENT) && 
                  <Button
                    className="bookingButton"
                    size="small"
                    variant="contained"
                    bgcolor="secondary"
                    color="primary"
                    onClick={() => handleReservation(action) }
                  >
                    Book now
                  </Button>
                }
                {/* <div className="actionButton">
                  <ThemeProvider theme={theme}>
                    <Button
                      variant="contained"
                      size="small"
                      color="primary"
                      sx={{ fontWeight: "bold" }}
                    >
                      get
                    </Button>
                  </ThemeProvider>
                </div> */}

                <hr className="line"></hr>
              </div>
            );
          })}
        </div>
      </div>
    );
  }
}

export default QuickActionBox;
