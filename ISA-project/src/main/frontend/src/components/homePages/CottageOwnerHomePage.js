import * as React from "react";
import Box from "@mui/material/Box";
import CssBaseline from "@mui/material/CssBaseline";
import Typography from "@mui/material/Typography";
import Divider from "@mui/material/Divider";
import PropTypes from "prop-types";
import Tabs from "@mui/material/Tabs";
import Tab from "@mui/material/Tab";
import Container from "@mui/material/Container";
import { createTheme, ThemeProvider } from "@mui/material/styles";
import MainNavigationHome from "../layout/MainNavigationHome";
import Grid from "@mui/material/Grid";
import Search from "../forms/search/Search";
import SearchIcon from "@mui/icons-material/Search";
import Album from "../collections/Album";
import OwnerProfile from "../profilePages/userProfile/OwnerProfile";
import { offerType } from "../../app/Enum";
import { useState } from "react";
import AddCottagePage from "../forms/cottage/AddCottagePage";
import WorkingCalendar from "../calendar/WorkingCalendar";
import ReservationProfile from "../profilePages/reservationProfile/ReservationProfile";
import Checkout from "../forms/reservations/newQuickReservation/NewQuickReservationForm";
import NewReservationForm from '../forms/reservations/reservationOwner/NewReservationForm';
import HomePageGraph from '../graphs/HomePageGraph';

function TabPanel(props) {
  const { children, value, index, ...other } = props;

  return (
    <div
      role="tabpanel"
      hidden={value !== index}
      id={`vertical-tabpanel-${index}`}
      aria-labelledby={`vertical-tab-${index}`}
      {...other}
    >
      {value === index && (
        <Box sx={{ p: 3 }}>
          <Typography>{children}</Typography>
        </Box>
      )}
    </div>
  );
}

TabPanel.propTypes = {
  children: PropTypes.node,
  index: PropTypes.number.isRequired,
  value: PropTypes.number.isRequired,
};

function a11yProps(index) {
  return {
    id: `vertical-tab-${index}`,
    "aria-controls": `vertical-tabpanel-${index}`,
  };
}

export default function CottageOwnerHomePage() {
  const [params, setParams] = useState({
    firstName: "",
    lastName: "",
    phoneNumber: "",
    name: "",
    address: "",
    maxPeople: -1,
    price: -1,
  });

  const resetParams = () => {
    setParams({
      firstName: "",
      lastName: "",
      phoneNumber: "",
      name: "",
      address: "",
      maxPeople: -1,
      price: -1,
    });
  };
  const [value, setValue] = React.useState(0);
  const [offers, setOffers] = useState();

  const handleChange = (event, newValue) => {
    setValue(newValue);
    resetParams();
  };

  const outerTheme = createTheme({
    palette: {
      primary: { main: "#CC7351" },
      secondary: { main: "#9DAB86" },
    },
  });
  const secondaryTheme = createTheme({
    palette: {
      primary: { main: "#9DAB86" },
      secondary: { main: "#ffffff" },
    },
  });
  return (
    <ThemeProvider theme={outerTheme}>
      <MainNavigationHome />
      <Container maxWidth="100%">
        <Box
          sx={{
            flexGrow: 1,
            bgcolor: "background.paper",
            display: "flex",
            maxWidth: "100%",
          }}
        >
          <CssBaseline />
          <Tabs
            orientation="vertical"
            variant="scrollable"
            value={value}
            onChange={handleChange}
            aria-label="Vertical tabs example"
            textColor="primary"
            indicatorColor="primary"
            sx={{ borderRight: 1, borderColor: "divider", minWidth: "20%" }}
          >
            <Tab label="Home page" {...a11yProps(0)} />
            <Divider />
            <Tab label="Profile page" {...a11yProps(1)} />
            <Divider />
            <Tab label="Add new cottage" {...a11yProps(3)} />
            <Divider />
            <Tab label="Reservation history" {...a11yProps(4)} />
            <Tab label="New reservation" {...a11yProps(6)} />
            <Tab label="New action" {...a11yProps(7)} />
            <Divider />
            <Tab label="Calendar" {...a11yProps(8)} />
            <Divider />
            <Tab label="Business report" {...a11yProps(9)} />
          </Tabs>
          <TabPanel value={value} index={0}>
            <p
              style={{
                marginTop: "0px",
                marginBottom: "0px",
                fontSize: "30px",
                color: "#CC7351",
              }}
            >
              Search
              <SearchIcon />
            </p>
            <Divider />
            <br />
            <br />
            <Box sx={{ flexGrow: 1 }}>
              <Grid item xs={12}>
                <Search
                  params={params}
                  setParams={setParams}
                  type={offerType.COTTAGE_OWNER}
                  setOffers={setOffers}
                />
              </Grid>
            </Box>
            <Album albumData={offers} setAlbumeData={setOffers}/>
          </TabPanel>
          <TabPanel value={value} index={2}>
            <OwnerProfile />
          </TabPanel>
          <TabPanel value={value} index={4}>
            <AddCottagePage />
          </TabPanel>
          <TabPanel value={value} index={6}>
            <ReservationProfile />
          </TabPanel>
          <TabPanel value={value} index={7}>
            <NewReservationForm offers={offers} setOffers={setOffers}/>
          </TabPanel>
          <TabPanel value={value} index={8}>
            <Checkout offers={offers} setOffers={setOffers}/>
          </TabPanel>
          <TabPanel value={value} index={10}>
            <WorkingCalendar offers={offers} setOffers={setOffers}/>
          </TabPanel>
          <TabPanel value={value} index={12}>
            <HomePageGraph/>
          </TabPanel>
        </Box>
      </Container>
    </ThemeProvider>
  );
}
