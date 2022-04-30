import "./CottageProfilePage.scss";
import { Grid, Box, Button } from "@mui/material";
import { ThemeProvider } from "@emotion/react";
import { createTheme } from "@mui/material/styles";
import * as React from "react";
import { useParams } from "react-router-dom";
import { getCottageById } from "../../../services/CottageService";
import { useState, useEffect } from "react";
import QuickActionBox from "./QuickActionBox";
import BasicCottageInfoBox from "../cottageProfile/BasicCottageInfoBox";
import AdditionalDescriptionBox from "./AdditionalDescriptionBox";
import PriceList from "./Pricelist";
import image1 from "../../images/img1.jpg";
import image2 from "../../images/img2.jpg";
import ImagesBox from "./ImagesBox";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import '../../../style/OfferData.scss';
import { getRoleFromToken } from "../../../app/jwtTokenUtils";
import { userType } from "../../../services/userService";

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
  //let id = useParams();

  const [cottageData, setCottageData] = useState();

  useEffect(() => {
    async function setcottageData() {
      let cottage = await getCottageById(id);
      setCottageData(!!cottage ? cottage.data : {});

      return cottage;
    }
    setcottageData();
  }, []);
  let images = [];
  if (cottageData) {
    console.log("la");
    console.log(cottageData.price);
    cottageData.photos.forEach((photo) => {
      let imag = { image: require("/src/components/images/" + photo) };
      images.push(imag);
    });
    return (
      <div className="cottageProfile" id="changeDataContainer">
      <ThemeProvider theme={theme} minWidth="700px">
      <div className="profileContainer">
                <div className="closeProfileBtn">
                    <Button size="large" sx={{}} onClick={() => close()}>
                    x
                    </Button>
                </div>
                <div className="headerContainer">
                    <h2 className="adventureTittle">{cottageData.name}</h2>
                    {(getRoleFromToken() == null) ? 
                    (<div className="changeBtn">
                      <Button variant="contained">Change info</Button>
                      </div>) : 
                    (<></>)}
              
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
                <PriceList basicPrice={cottageData.price} />
            </div>
      </ThemeProvider>
      
      </div>
    );
  }
}

export default CottageProfilePage;