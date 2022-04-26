import "./CottageProfilePage.scss";
import { Grid, Box, Button } from "@mui/material";
import { ThemeProvider } from "@emotion/react";
import { createTheme } from "@mui/material/styles";
import * as React from "react";
import { getCottageById } from "../../../services/CottageService";
import { useState, useEffect } from "react";
import QuickActionBox from "./QuickActionBox";
import BasicCottageInfoBox from "../cottageProfile/BasicCottageInfoBox";
import AdditionalDescriptionBox from "./AdditionalDescriptionBox";
import PriceList from "./Pricelist";
import ImagesBox from "./ImagesBox";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import '../../../style/OfferData.scss';
import {getMarkByOfferId} from '../../../services/MarkService';
import Rating from '@mui/material/Rating';
import Divider from '@mui/material/Divider';

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

function CottageProfilePage({ id, close }) {

  const [cottageData, setCottageData] = useState();

  useEffect(() => {
    async function setcottageData() {
      let cottage = await getCottageById(id);
      setCottageData(!!cottage ? cottage.data : {});

      return cottage;
    }
    setcottageData();
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

  let images = [];
  if (cottageData && markData) {
    cottageData.photos.forEach((photo) => {
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
                  <h2 className="adventureTittle">{cottageData.name}</h2>
                  <div className="changeBtn">
                    <Button variant="contained">Change info</Button>
                  </div>
                  <Divider/>
                  <div className="mark">
                  <Rating name="read-only" value={markData} readOnly />
                  </div>
                </div>

                <ImagesBox images={images} />
                <QuickActionBox id={cottageData.id} />
                <Grid container xs={12}>
                  <Grid item xs={12} sm={6}>
                    <BasicCottageInfoBox basicInfo={cottageData} />
                  </Grid>
                  <Grid item xs={12} sm={6}>
                    <AdditionalDescriptionBox additionData={cottageData} />
                  </Grid>
                </Grid>
                <PriceList offer={cottageData} />
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

export default CottageProfilePage;
