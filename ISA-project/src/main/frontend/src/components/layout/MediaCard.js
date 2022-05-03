import * as React from "react";
import Card from "@mui/material/Card";
import CardActions from "@mui/material/CardActions";
import CardContent from "@mui/material/CardContent";
import CardMedia from "@mui/material/CardMedia";
import Button from "@mui/material/Button";
import Typography from "@mui/material/Typography";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import "../../style/MediaCard.scss";
import CottageProfilePage from "../profilePages/cottageProfile/CottageProfilePage";
import { useState } from "react";
import { useEffect } from 'react';
import {getMarkByOfferId} from '../../services/MarkService';
import Rating from '@mui/material/Rating';
import { getRoleFromToken} from "../../app/jwtTokenUtils";
import Modal from '@mui/material/Modal';
import { userType, offerTypeByUserType, offerType} from "../../app/Enum";
import AdventureProfilePage from "../profilePages/adventureProfile/AdvetureProfilePage";
import ShipProfilePage from "../profilePages/shipProfile/ShipProfilePage";
import OwnerProfile from "../profilePages/userProfile/OwnerProfile";

const secondaryTheme = createTheme({
  palette: {
    primary: { main: "#9DAB86" },
    secondary: { main: "#ffffff" },
  },
});

export default function MediaCard({ offer, offerT }) {

  const [offerData, setOfferData] = useState();

  const [open, setOpen] = useState(false);
  const [clientData, setClientData] = useState();

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleOpenPass = () => setOpen(true);
  const handleClosePass = () => setOpen(false);

 

  let imag = require("../images/no-image.png");
  if(offer.photos && offer.photos.length != 0){
    console.log(require("/src/components/images/" + offer.photos[0]));
    imag = require("/src/components/images/" + offer.photos[0]);
  }


  const childToParent = (childData) => {
    console.log("TUU");
    
    if(offerT === offerType.ADVENTURE){
      imag = require("../images/no-image.png");
      if(offer.photos.length != 0){
        imag = "data:image/jpg;base64," + offer.photos[0];
      }
      setOfferData(prevState => ({
        ...prevState,
        ["offerName"]: childData.offerName,
        ["description"]: childData.description,
        ["photos"]:childData.photos,

    }) ); 
    } 
    else{
      setOfferData(prevState => ({
        ...prevState,
        ["name"]: childData.offerName,
        ["description"]: childData.description,
        ["photos"]:childData.photos,

    }) ); 
    }
  }


const modalOfferComponent = (offerStr, offerId) =>{

    switch (offerStr){
      case offerType.ADVENTURE:
        return ( <AdventureProfilePage id={offerId} close={handleClose} childToParenMediaCard={childToParent}/>);
      case offerType.COTTAGE: 
        return ( <CottageProfilePage id={offerId} close={handleClose}/>);
      case offerType.SHIP:
        return ( <ShipProfilePage id={offerId} close={handleClose}/>);
      case userType.INSTRUCTOR:
        return (<OwnerProfile instructor={offer} close={handleClose} />);
      default:
        return (<div>Undefined offer type</div>);
    }
  }

  const [markData, setMarkData] = useState();
  useEffect(() => {
    setOfferData(offer);
    async function setData() {
      const markData = await getMarkByOfferId(offer.id);
      setMarkData(markData.data ? markData.data : "0");
      return markData.data;
    }
    setData();
  }, []);
  if(markData) {
    return (
      <ThemeProvider theme={secondaryTheme}>
        {console.log(offer)}
        <Card sx={{ maxWidth: 345, maxHeight: 375, minHeight:330}}>
          <CardMedia component="img" height="140" image={imag} alt="slike" />
          <CardContent>
            <Typography gutterBottom variant="h5" component="div" color="primary">
              {/* {offer.name && offerT != userType.INSTRUCTOR ? offer.name : offer.firstName + " " + offer.lastName} */}
              {offer.name && offerT != userType.INSTRUCTOR ? offer.name : offer.offerName}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              <p className="descriptionContainer"> {offerT != userType.INSTRUCTOR  ? offer.description : offer.biography} </p>     
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
}
