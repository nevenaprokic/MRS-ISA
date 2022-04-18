import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useNavigate } from "react-router-dom";
import '../../style/MediaCard.scss';
import AdventureProfilePage from "../profilePages/adventureProfile/AdvetureProfilePage";
import { getAdventureById } from "../../services/AdventureService";
import { userType } from "../../services/userService";
import { getRoleFromToken } from "../../app/jwtTokenUtils";

const secondaryTheme = createTheme({
  palette: {
    primary: { main: "#9DAB86" },
    secondary: { main: "#ffffff" },
  },
});



export default function MediaCard({ offer }) {
  let navigate = useNavigate();
  
  const handleVIew = function(){
    openAdventurePage("aa");
 

  }


  function routeChange(nesto){
    let path = `/cottage-owner/cottage-profile/`+ offer.id;
    navigate(path, {
      params: { cottageObj: offer }
    });
  };

  function openAdventurePage(nesto){
   let path = `/instructor/adventure-profile/`+ offer.id;
    navigate(path, {
      params: { adventureObj: offer }
    });
  }

  let offersOptions = {
    [userType.CLIENT]:  "", //funkcija za klijenta
    [userType.INSTRUCTOR]: openAdventurePage,
    [userType.COTTAGE_OWNER]: routeChange
  }

  let imag = require("../images/no-image.png");
  console.log(offer.photos);
  if(offer.photos.length != 0){
    imag = require("/src/components/images/" + offer.photos[0]);
  }
  
  return (
    <ThemeProvider theme={secondaryTheme}>
      <Card sx={{ maxWidth: 345, maxHeight: 330, minHeight:330}}>
        <CardMedia component="img" height="140" image={imag} alt="slike" />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div" color="primary">
            {offer.name}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            <p className="descriptionContainer"> {offer.description} </p>     
          </Typography>
        </CardContent>
        <CardActions>
          <Button
            size="small"
            variant="contained"
            bgcolor="secondary"
            color="primary"
            onClick={handleVIew}
          >
            View
          </Button>
        </CardActions>
      </Card>
    </ThemeProvider>
  );
}
