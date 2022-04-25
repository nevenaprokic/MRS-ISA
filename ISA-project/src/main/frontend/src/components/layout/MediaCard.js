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
import Modal from "@mui/material/Modal";
import { useEffect } from 'react';
import {getMarkByOfferId} from '../../services/MarkService';
import Rating from '@mui/material/Rating';

const secondaryTheme = createTheme({
  palette: {
    primary: { main: "#9DAB86" },
    secondary: { main: "#ffffff" },
  },
});

export default function MediaCard({ offer }) {
  const [open, setOpen] = useState(false);

  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);
  const [openPasswordManager, setPasswordManager] = useState(false);

  const handleOpenPass = () => setPasswordManager(true);
  const handleClosePass = () => setPasswordManager(false);

 

  let imag = require("../images/no-image.png");
  console.log(offer.photos[0]);
  if (offer.photos.length != 0) {
    console.log(require("/src/components/images/" + offer.photos[0]));
    imag = require("/src/components/images/" + offer.photos[0]);
  }

  const [markData, setMarkData] = useState();
  useEffect(() => {
    async function setData() {
      const markData = await getMarkByOfferId(offer.id);
      setMarkData(markData.data ? markData.data : "0");
      return markData.data;
    }
    setData();
  }, []);
  if (markData) {
    return (
      <ThemeProvider theme={secondaryTheme}>
        <Card sx={{ maxWidth: 345, maxHeight: 375, minHeight: 330 }}>
          <CardMedia component="img" height="140" image={imag} alt="slike" />
          <CardContent>
            <Typography
              gutterBottom
              variant="h5"
              component="div"
              color="primary"
            >
              {offer.name}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              <p className="descriptionContainer"> {offer.description} </p>
              <p>
              <Rating name="read-only" value={markData} readOnly />
              </p>
            </Typography>
          </CardContent>
          <CardActions>
            <Button
              size="small"
              variant="contained"
              bgcolor="secondary"
              color="primary"
              onClick={handleOpenPass}
            >
              View
            </Button>
            <Modal
              open={openPasswordManager}
              onClose={handleClose}
              aria-labelledby="modal-modal-title"
              aria-describedby="modal-modal-description"
              sx={{ backgroundColor: "rgb(218, 224, 210, 0.6)" }}
            >
              <CottageProfilePage id={offer.id} close={handleClosePass} />
            </Modal>
          </CardActions>
        </Card>
      </ThemeProvider>
    );
  }
}
