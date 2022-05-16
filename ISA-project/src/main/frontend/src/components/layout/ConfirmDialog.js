import * as React from "react";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import "../../style/ConfirmDialog.scss";
import "react-toastify/dist/ReactToastify.css";
import ButtonGroup from '@mui/material/ButtonGroup';

export default function ConfirmDialog({ close, cb }) {
  const theme = createTheme({
    palette: {
      primary: {
        main: "#5f6d5f",
      },
      secondary: {
        main: "#CC7351",
      },
    },
  });

  const onConfirm = (data) => {
    cb();
    close();
  };

  const onClose = (data) => {
    close();
  };

  return (
    <div className="confirmDialogContainer">
      <ThemeProvider theme={theme}>
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <Box
            sx={{
              marginTop: 0,
              display: "inline",
              flexDirection: "column",
              alignItems: "center",
              marginLeft: -5,
              width: "100%",
            }}
          >
            <div className="header">
              <div className="tittleConfirm">
                <Typography
                  component="h1"
                  variant="h5"
                  sx={{ color: "#CC7351" }}
                >
                  Are you sure?
                </Typography>
              </div>
            </div>

            <ButtonGroup className="buttons" disableElevation variant="contained">
                <Button onClick={onConfirm}>Confirm</Button>
                <Button  onClick={onClose} style={{ backgroundColor: "#CC7351"}}>Close</Button>
            </ButtonGroup>
        
              <div>
                <br></br>
              </div>
            </Box>
        </Container>
      </ThemeProvider>
    </div>
  );
}
