import { Grid, Box, Button} from "@mui/material";
import PriorityHighIcon from '@mui/icons-material/PriorityHigh';
import { createTheme } from '@mui/material/styles';
import { ThemeProvider } from "@emotion/react";
import "./CottageProfilePage.scss";


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

function QuickActionBox({inputList}){

    return(

        <div className="specialOffersContainer">
          
          <div className="specialOffersTitle">
              <label className="tittle">! Special offers:</label>
              <hr className="tittleLine"></hr>
              
          </div>

        
        <div className="specialOfferSrollBox">
       
                {inputList?.map((x, i) => {
                    return(
                        <div className="scolledItemsContainer">
                            <h3 className="actionTittle">Date of stay :{"15.10.2022"} {" - "} {"15.10.2022."}</h3>                           
                            <div>
                               
                                <label className="stayDate">Maximum number of people: {"4"}</label>
                                
                                <div className="availableDate">

                                    <label >Action available: </label>
                                    <label >{"15.10.2022"} {" - "} {"15.10.2022."}</label>
                                </div>
                            
                            </div>
                            
                            <div >
                                <label >Additional services: {"Lunch, Dinner, Boating"}</label>
                                
                            </div>
                            <br></br>
                            <label className="priceItem">Price: {"120"} €</label>
                                
                            <div className="actionButton">
                                <ThemeProvider theme={theme}>
                                    <Button variant="contained" size="small" color="primary" sx={{fontWeight:"bold"}}>get</Button>
                                </ThemeProvider>
                                
                            </div>
                            
                            <hr className="line"></hr>
                        </div>
                    );
                })}
        </div>
        
        </div>
    );

}

export default QuickActionBox;