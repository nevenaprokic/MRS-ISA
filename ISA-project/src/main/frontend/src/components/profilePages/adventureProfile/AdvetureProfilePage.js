import "./AdventureProfilePage.scss" ;
import ImagesBox from "./ImagesBox";

import QuickActionBox from "./QuickActionsBox";
import BasicAdventureInfiBox from "./BasicAdventureInfoBox";
import AdditionalDescriptionBox from "./AdditionalDescriptionBox";
import PriceList from "./Pricelist";
import { Grid, Box, Button} from "@mui/material";
import { ThemeProvider } from "@emotion/react";
import { createTheme } from '@mui/material/styles';
import { getAdventureById } from "../../../services/AdventureService";
import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";


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

function AdventureProfilePage(){
    const { id } = useParams();

    const [adventureData, setAdventureData] = useState();

    useEffect(() => {
        getAdventureById(id).then((offer) => {
            console.log(offer);
            setAdventureData(offer.data);    
        })
    }, []);
 
    return( 
        !!adventureData && 
        <ThemeProvider theme={theme}>
            <div className="profileContainer">
                <div className="headerContainer">
                    <h2 className="adventureTittle">{adventureData.offerName}</h2>
                    <div className="changeBtn" >
                        <Button variant="contained">Change info</Button>
                    </div>
                    
                </div>
                
                <ImagesBox images={adventureData.photos}/>
                <QuickActionBox inputList={adventureData.photos}/> {/*ovde proslediti listu akcija kad se dobavi*/}
                <Grid container xs={12}>
                    <Grid item xs={12} sm={6} >
                        <BasicAdventureInfiBox basicInfo={adventureData}/>
                    </Grid>
                    <Grid item xs={12} sm={6} >
                        <AdditionalDescriptionBox additionalDate={adventureData} />
                    </Grid>
                
                    
                </Grid>
                <PriceList priceData={adventureData}/>
            </div>
        </ThemeProvider>
    );
}
export default AdventureProfilePage;