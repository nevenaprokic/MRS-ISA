import "../cottageProfile/CottageProfilePage.scss";
import { Grid, Button } from "@mui/material";
import { ThemeProvider } from "@emotion/react";
import { createTheme } from "@mui/material/styles";
import * as React from "react";
import { useState, useEffect } from "react";
import QuickActionBox from "../cottageProfile/QuickActionBox";
import BasicShipInfoBox from "./BasicShipInfoBox";
import AdditionalDescriptionBox from "./AdditionalDescriptionBox";
import PriceList from "../cottageProfile/Pricelist";
import ImagesBox from "../cottageProfile/ImagesBox";
import "../../../style/OfferData.scss";
import { getShipById, checkReservation } from "../../../services/ShipService";
import { getRoleFromToken } from "../../../app/jwtTokenUtils";
import Divider from "@mui/material/Divider";
import Rating from "@mui/material/Rating";
import { userType } from "../../../app/Enum";
import Modal from "@mui/material/Modal";
import DeleteShip from "../../forms/ship/DeleteShip";
import { toast } from "react-toastify";




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

function ShipProfilePage({ id, close }) {
  const [shipData, setShipData] = useState();
  const [openDialog, setOpenDialog] = React.useState(false);

  const handleOpenDeleteDialog = () => {
    checkAllowed(false);
  };
  const handleCloseDeleteDialog = () => {
    setOpenDialog(false);
  };

  async function checkAllowed({operation}) {
    let allowed = await checkReservation(shipData);
    let message = "Delete is not allowed because this ship has reservations.";
    if(operation)
      message = "Update is not allowed because this ship has reservations.";
    if (allowed) {
      setOpenDialog(true);
    } else {
      toast.error(
        message,
        {
          position: toast.POSITION.BOTTOM_RIGHT,
          autoClose: 1500,
        }
      );
    }
  }

  useEffect(() => {
    async function setData() {
      let ship = await getShipById(id);
      setShipData(!!ship ? ship.data : {});

      return ship;
    }
    setData();
  }, []);

  let photos = [];
  if (shipData) {
    shipData.photos.forEach((photo) => {
      let imag = { image: "data:image/jpg;base64," + photo };
      photos.push(imag);
    });
    return (
      <div className="changeDataContainer" id="changeDataContainer">
        <ThemeProvider theme={theme}>
          <div className="profileContainer">
            <div className="closeProfileBtn">
              <Button size="large" sx={{}} onClick={() => close()}>
                x
              </Button>
            </div>
            <div className="headerContainer">
              <h2 className="adventureTittle">{shipData.name}</h2>
              <Divider />
              <div className="mark">
                <Rating name="half-rating-read" precision={0.5} value={shipData.mark} readOnly />
              </div>
          
            {getRoleFromToken() != null &&
              getRoleFromToken() != userType.CLIENT ? (
                <div className="changeBtn">
                  <Button style={{ marginLeft: "35%" }} variant="contained">
                    Change info
                  </Button>
                  <Button
                    style={{ marginLeft: "5%" }}
                    variant="contained"
                    onClick={handleOpenDeleteDialog}
                  >
                    Delete
                  </Button>
                </div>
              ) : (
                <></>
              )}
            </div>
            <Modal
              open={openDialog}
              onClose={handleCloseDeleteDialog}
              aria-labelledby="modal-modal-title"
              aria-describedby="modal-modal-description"
              sx={{
                backgroundColor: "rgb(218, 224, 210, 0.6)",
                overflow: "auto",
              }}
            >
              <DeleteShip
                closeDialog={handleCloseDeleteDialog}
                open={openDialog}
                name={shipData.name}
                id={shipData.id}
                cloese={close}
              />
            </Modal>

            <ImagesBox images={photos} />
            <QuickActionBox id={shipData.id} />
            <Grid container xs={12}>
              <Grid item xs={12} sm={6}>
                <BasicShipInfoBox basicInfo={shipData} />
              </Grid>
              <Grid item xs={12} sm={6}>
                <AdditionalDescriptionBox additionData={shipData} />
              </Grid>
            </Grid>
            <PriceList offer={shipData} />
          </div>
        </ThemeProvider>
      </div>
    );
  }
}

export default ShipProfilePage;
