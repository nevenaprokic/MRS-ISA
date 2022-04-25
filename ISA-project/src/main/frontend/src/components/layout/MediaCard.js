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
import CottageProfilePage from "../profilePages/cottageProfile/CottageProfilePage";
import {getRoleFromToken} from '../../app/jwtTokenUtils';
import { useState } from "react";
import Modal from '@mui/material/Modal';
import { userType, offerTypeByUserType, offerType} from "../../services/userService";
import AdventureProfilePage from "../profilePages/adventureProfile/AdvetureProfilePage";
import ShipProfilePage from "../profilePages/shipProfile/ShipProfilePage";


const secondaryTheme = createTheme({
  palette: {
    primary: { main: "#9DAB86" },
    secondary: { main: "#ffffff" },
  },
});

export default function MediaCard({ offer, offerT }) {


  const [clientData, setClientData] = useState();

  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleOpenPass = () => setOpen(true);
  const handleClosePass = () => setOpen(false);



  let imag = require("../images/no-image.png");
  console.log("SSS", offer);
  if(offer.photos.length != 0){
    console.log(require("/src/components/images/" + offer.photos[0]));
    imag = require("/src/components/images/" + offer.photos[0]);
  }

  const modalOfferComponent = (offerStr, offerId) =>{
    switch (offerStr){
      case offerType.ADVENTURE:
        return ( <AdventureProfilePage id={offerId} close={handleClose}/>);
      case offerType.COTTAGE: 
        return ( <CottageProfilePage id={offerId} close={handleClose}/>);
      case offerType.SHIP:
        return ( <ShipProfilePage id={offerId} close={handleClose}/>);
      default:
        return (<div>Undefined offer type</div>);
    }
  }

  return (
    <ThemeProvider theme={secondaryTheme}>
      <Card sx={{ maxWidth: 345, maxHeight: 330, minHeight:330}}>
        <CardMedia component="img" height="140" image={imag} alt="slike" />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div" color="primary">
            {!!offer.name ? offer.name : offer.offerName}
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
            onClick={handleOpen}
          >
            View
          </Button>
          <Modal
              open={open}
              onClose={handleClose}
              aria-labelledby="modal-modal-title"
              aria-describedby="modal-modal-description"
              sx={{backgroundColor:"rgb(218, 224, 210, 0.6)", overflow:"auto"}}
          >
                  {modalOfferComponent(offerT, offer.id)}
              
          </Modal>
        </CardActions>
      </Card>
    </ThemeProvider>
  );
}
