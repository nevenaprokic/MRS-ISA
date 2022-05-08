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
import { getShipById } from "../../../services/ShipService";
import { getRoleFromToken } from "../../../app/jwtTokenUtils";
import { getMarkByOfferId } from "../../../services/MarkService";
import Divider from "@mui/material/Divider";
import Rating from "@mui/material/Rating";
import { userType } from "../../../app/Enum";


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

  useEffect(() => {
    async function setData() {
      let ship = await getShipById(id);
      setShipData(!!ship ? ship.data : {});

      return ship;
    }
    setData();
  }, []);

  const [markData, setMarkData] = useState();
  useEffect(() => {
    async function setData() {
      const markData = await getMarkByOfferId(id);
      setMarkData(markData.data ? markData.data : "0");
      return markData.data;
    }
    setData();
  }, []);

  // function createServiceData() {
  //   let rows = [];
  //   shipData.additionalServices.forEach((data) => {
  //       let name = data.serviceName;
  //       let price = data.servicePrice;
  //       rows.push({name, price});
  //     });
  //   return rows;
  // }

  let photos = [];
  if (shipData && markData) {
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
              {(getRoleFromToken() != null && getRoleFromToken() != userType.CLIENT) ? (
                <div className="changeBtn">
                  <Button variant="contained">Change info</Button>
                </div>
              ) : (
                <></>
              )}

              <Divider />
              <div className="mark">
                <Rating name="read-only" value={markData} readOnly />
              </div>
            </div>

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
