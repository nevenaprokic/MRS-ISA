import "../cottageProfile/CottageProfilePage.scss";
import { Grid, Box, Button } from "@mui/material";
import { ThemeProvider } from "@emotion/react";
import { createTheme } from "@mui/material/styles";
import * as React from "react";
import { useState, useEffect } from "react";
import QuickActionBox from "../cottageProfile/QuickActionBox";
import BasicShipInfoBox from "./BasicShipInfoBox";
import AdditionalDescriptionBox from "./AdditionalDescriptionBox";
import PriceList from "../cottageProfile/Pricelist";
import ImagesBox from "../cottageProfile/ImagesBox";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import '../../../style/OfferData.scss';
import { getShipById } from "../../../services/ShipService";

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
  let images = [];
  if (shipData) {
    shipData.photos.forEach((photo) => {
      let imag = { image: require("/src/components/images/" + photo) };
      images.push(imag);
    });
    return (
      <div className="changeDataContainer" id="changeDataContainer">
      <ThemeProvider theme={theme}>
        <Container component="main" maxWidth="xs">
          <Box
            sx={{
              marginTop: 0,
              display: "inline",
              flexDirection: "column",
              alignItems: "center",
              marginLeft: -5,
              width: "120%",
            }}
          >
            <div className="header">
              <div className="tittle">
                <Typography
                  component="h1"
                  variant="h5"
                  sx={{ color: "#CC7351" }}
                >
                  
                </Typography>
              </div>
              <div className="closeBtn">
                <Button size="large" sx={{}} onClick={() => close()}>
                  x
                </Button>
              </div>
            </div>
            <Box component="form" noValidate sx={{ mt: 3 }}>
            <Grid container spacing={2}>
              <div className="profileContainer">
                <div className="headerContainer">
                  <h2 className="adventureTittle">{shipData.name}</h2>
                  <div className="changeBtn">
                    <Button variant="contained">Change info</Button>
                  </div>
                </div>

                <ImagesBox images={images} />
                <QuickActionBox id={shipData.id} />
                <Grid container xs={12}>
                  <Grid item xs={12} sm={6}>
                    <BasicShipInfoBox basicInfo={shipData} />
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <AdditionalDescriptionBox additionData={shipData} />
                  </Grid>
                </Grid>
                <PriceList basicPrice={shipData.price} />
              </div>
              </Grid>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
      </div>
    );
  }
}

export default ShipProfilePage;
