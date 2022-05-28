import { useEffect, useState } from "react";
import * as React from "react";
import Box from "@mui/material/Box";
import Collapse from "@mui/material/Collapse";
import IconButton from "@mui/material/IconButton";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import Modal from "@mui/material/Modal";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Typography from "@mui/material/Typography";
import Paper from "@mui/material/Paper";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import { ThemeProvider } from "@emotion/react";
import { createTheme } from "@mui/material/styles";
import TablePagination from "@mui/material/TablePagination";
import LocationOnIcon from "@mui/icons-material/LocationOn";
import { userType } from "../../../app/Enum";
import {
  getRoleFromToken,
  getUsernameFromToken,
} from "../../../app/jwtTokenUtils";
import Rating from "@mui/material/Rating";
import { getAdventureByInstructorEmail } from "../../../services/AdventureService";
import { getShipByShipOwnerEmail } from "../../../services/ShipService";
import { getCottageByCottageOwnerEmail } from "../../../services/CottageService";
import Divider from "@mui/material/Divider";

function Row({ row, setRequests }) {
  const request = row;
  const [open, setOpen] = React.useState(false);

  const theme = createTheme({
    palette: {
      primary: { main: "#CC7351" },
      secondary: { main: "#99A799" },
    },
  });

  return (
    <ThemeProvider theme={theme}>
      <React.Fragment>
        <TableRow sx={{ "& > *": { borderBottom: "unset" } }}>
          <TableCell>
            <label className="deatilsItem">details</label>
            <IconButton
              aria-label="expand row"
              size="small"
              onClick={() => setOpen(!open)}
            >
              {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
            </IconButton>
          </TableCell>
          <TableCell sx={{ fontSize: 16 }} align="center">
            {"Sumska vila"}
          </TableCell>
          <TableCell sx={{ fontSize: 16 }} align="center">
            {" "}
            <Rating
              name="half-rating-read"
              precision={0.5}
              value={4}
              readOnly
            />
          </TableCell>
        </TableRow>

        <TableRow>
          <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={6}>
            <Collapse in={open} timeout="auto" unmountOnExit>
              <Box sx={{ margin: 1 }}>
                <Typography
                  variant="h6"
                  gutterBottom
                  component="div"
                  sx={{ color: "#5f6d5f" }}
                >
                  <LocationOnIcon />
                  {"\t\tOffer info"}
                </Typography>
                <Typography
                  variant="body1"
                  gutterBottom
                  component="div"
                  sx={{ color: "#5f6d5f" }}
                >
                  Price:
                  <label className="textItem"> {"100€"} </label>
                </Typography>
                <Typography
                  variant="body1"
                  gutterBottom
                  component="div"
                  sx={{ color: "#5f6d5f" }}
                >
                  Number of persone:
                  <label className="textItem"> {"2"} </label>
                </Typography>
                <Typography
                  variant="body1"
                  gutterBottom
                  component="div"
                  sx={{ color: "#5f6d5f" }}
                >
                  Additional services:
                  <label className="textItem">{" servisi"}</label>
                </Typography>
                <Typography
                  variant="body1"
                  gutterBottom
                  component="div"
                  sx={{ color: "#5f6d5f" }}
                >
                  Address:
                  <label className="textItem"> {"Neka adresa"} </label>
                </Typography>
              </Box>
            </Collapse>
          </TableCell>
        </TableRow>
      </React.Fragment>
    </ThemeProvider>
  );
}

function MarkForm() {
  const [offers, setOffers] = useState();
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  let role = getRoleFromToken();
  let getOfferByOwnerEmail = {
    [userType.COTTAGE_OWNER]: getCottageByCottageOwnerEmail,
    [userType.INSTRUCTOR]: getAdventureByInstructorEmail,
    [userType.SHIP_OWNER]: getShipByShipOwnerEmail,
  };

  useEffect(() => {
    async function getOfferData() {
      let username = getUsernameFromToken();
      const offersData = await getOfferByOwnerEmail[role](username);
      setOffers(!!offersData ? offersData.data : {});

      return offersData;
    }
    getOfferData();
  }, []);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(+event.target.value);
    setPage(0);
  };

  return (
    !!offers && (
      <div>
        <div>
          <p
            style={{
              marginTop: "0px",
              marginBottom: "0px",
              fontSize: "30px",
              color: "#CC7351",
            }}
          >
            Overall rating of yours offers:        
            <Rating
              name="half-rating-read"
              precision={0.5}
              value={4}
              readOnly
              style={{marginLeft:"2%"}}
              size="large"
            />
          </p>
          <Divider />
          <br />
        </div>
        <div className="requestsContainer">
          <TableContainer component={Paper} className="tableContainer">
            <Table aria-label="collapsible table">
              <TableHead>
                <TableRow sx={{ borderBottom: "solid #99A799" }}>
                  <TableCell />
                  <TableCell
                    sx={{ fontWeight: "bold", color: "#5f6d5f", fontSize: 18 }}
                    align="center"
                  >
                    <Typography variant="button">Offer name</Typography>
                  </TableCell>
                  <TableCell
                    sx={{ fontWeight: "bold", color: "#5f6d5f", fontSize: 18 }}
                    align="center"
                  >
                    <Typography variant="button">Rating</Typography>
                  </TableCell>
                  <TableCell />
                  <TableCell />
                </TableRow>
              </TableHead>
              <TableBody>
                {offers
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map((row) => (
                    <Row key={row.id} row={row} setRequests={setOffers} />
                  ))}
              </TableBody>
            </Table>
          </TableContainer>
          <TablePagination
            rowsPerPageOptions={[10, 25, 100]}
            component="div"
            count={offers.length}
            rowsPerPage={rowsPerPage}
            page={page}
            onPageChange={handleChangePage}
            onRowsPerPageChange={handleChangeRowsPerPage}
          />
          {offers.length == 0 && (
            <Typography variant="h6" sx={{ color: "#CC7351" }}>
              There are currently no reservations in past
            </Typography>
          )}
        </div>
      </div>
    )
  );
}

export default MarkForm;
