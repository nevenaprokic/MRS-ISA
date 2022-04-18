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

const secondaryTheme = createTheme({
  palette: {
    primary: { main: "#9DAB86" },
    secondary: { main: "#ffffff" },
  },
});

export default function MediaCard({ cottage }) {
  let navigate = useNavigate();
  const routeChange = () => {
    let path = `/cottage-owner/cottage-profile/`+cottage.id;
    navigate(path, {
      params: { cottageObj: cottage }
    });
  };

  const imag = require("/src/components/images/" + cottage.photos[0]);
  return (
    <ThemeProvider theme={secondaryTheme}>
      <Card sx={{ maxWidth: 345, maxHeight: 330, minHeight:330}}>
        <CardMedia component="img" height="140" image={imag} alt="slike" />
        <CardContent>
          <Typography gutterBottom variant="h5" component="div" color="primary">
            {cottage.name}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            <p className="descriptionContainer"> {cottage.description} </p>     
          </Typography>
        </CardContent>
        <CardActions>
          <Button
            size="small"
            variant="contained"
            bgcolor="secondary"
            color="primary"
            onClick={routeChange}
          >
            View
          </Button>
        </CardActions>
      </Card>
    </ThemeProvider>
  );
}
