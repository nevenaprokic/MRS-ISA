import * as React from "react";
import Button from "@mui/material/Button";
import CssBaseline from "@mui/material/CssBaseline";
import Grid from "@mui/material/Grid";
import Box from "@mui/material/Box";
import Typography from "@mui/material/Typography";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import { useForm } from "react-hook-form";
import TextField from "@mui/material/TextField";
import "../../../style/DeleteOrder.scss";
import "react-toastify/dist/ReactToastify.css";
import {
  deleteAccountOnRequest,
  rejectDeleteAccountOnRequest,
} from "../../../services/AdminService";

function DeleteAccountResponse({
  close,
  deleteRequest,
  allDeleteRequest,
  setDeleteRequests,
  isDelete,
}) {
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

  const {
    register,
    handleSubmit,
    formState: { errors },
    watch,
  } = useForm({});

  const onSubmit = (data) => {
    if (isDelete) {
      deleteAccountOnRequest(
        data.response,
        deleteRequest,
        setDeleteRequests,
        allDeleteRequest
      );
    } else {
      rejectDeleteAccountOnRequest(
        data.response,
        deleteRequest,
        setDeleteRequests,
        allDeleteRequest
      );
    }
    close();
  };
  return (
    <div className="deleteDataContainer" id="deleteDataContainer">
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
              width: "120%",
            }}
          >
            <div className="header">
              <div className="tittleDelete">
                <Typography
                  component="h1"
                  variant="h5"
                  sx={{ color: "#CC7351" }}
                >
                  Response for delete request
                </Typography>
              </div>
              <div className="closeBtn">
                <Button size="large" sx={{}} onClick={() => close()}>
                  x
                </Button>
              </div>
            </div>

            <Box
              component="form"
              noValidate
              onSubmit={handleSubmit(onSubmit)}
              sx={{ mt: 3 }}
            >
              <Grid container spacing={2}>
                <TextField
                  required
                  id="reason"
                  label="Enter your response"
                  multiline
                  rows={4}
                  defaultValue=""
                  fullWidth
                  {...register("response", { required: true })}
                />
                {errors.reason && <p style={{ color: "#ED6663" }}>Required!</p>}
              </Grid>
              <div className="buttonDelete">
                <Button
                  type="submit"
                  fullWidth
                  variant="contained"
                  sx={{ mt: 3, mb: 2 }}
                >
                  Send
                </Button>
              </div>
              <div>
                <br></br>
              </div>
            </Box>
          </Box>
        </Container>
      </ThemeProvider>
    </div>
  );
}

export default DeleteAccountResponse;
