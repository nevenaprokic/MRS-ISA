
import * as React from 'react';
import Box from '@mui/material/Box';
import CssBaseline from '@mui/material/CssBaseline';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import PropTypes from 'prop-types';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Container from '@mui/material/Container';
import { createTheme, ThemeProvider } from '@mui/material/styles';
import MainNavigationHome from '../layout/MainNavigationHome';
import Grid from '@mui/material/Grid';
import Search from '../forms/search/Search';
import SearchIcon from '@mui/icons-material/Search';
import { useState } from "react";
import ClientProfile from '../profilePages/ClientProfile';


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
      'aria-controls': `vertical-tabpanel-${index}`,
    };
  }

export default function ClientHomePage() {
   

    const [value, setValue] = useState(0);

    const handleChange = (event, newValue) => {
      setValue(newValue);
    };
  
    const outerTheme = createTheme({
            palette: {
              primary: { main:'#CC7351'},
              secondary: { main:'#9DAB86'},
            },

      });
      const secondaryTheme = createTheme({
        palette: {
          primary: { main:'#9DAB86'},
          secondary: { main:'#ffffff'},
        },

  });
  return (
        <ThemeProvider theme={outerTheme}>
          <MainNavigationHome/>
            <Container maxWidth="100%">
              
                <Box
                sx={{ flexGrow: 1, bgcolor: 'background.paper', display: 'flex', maxWidth:"100%"}}
                >
                <CssBaseline />
                <Tabs
                    orientation="vertical"
                    variant="scrollable"
                    value={value}
                    onChange={handleChange}
                    aria-label="Vertical tabs example"
                    textColor='primary'  indicatorColor="primary"
                    sx={{ borderRight: 1, borderColor: 'divider', minWidth:"20%" }}
                >
                    <Tab label="Home page" {...a11yProps(0)} />
                    <Divider />
                    <Tab label="Profile page" {...a11yProps(1)} />
                    <Divider />
                    <Tab label="Availability of cottages" {...a11yProps(2)} />
                    <Divider />
                    <Tab label="Reservation history" {...a11yProps(3)} />
                    <Tab label="Reservation report" {...a11yProps(4)} />
                    <Tab label="New reservation" {...a11yProps(5)} />
                    <Tab label="New action" {...a11yProps(6)} />
                    <Divider />
                    <Tab label="Calendar" {...a11yProps(7)} />
                    <Divider />
                    <Tab label="Business report" {...a11yProps(8)} />
                </Tabs>
                <TabPanel value={value} index={0}>
                  <p style={{marginTop:'0px', marginBottom:'0px', fontSize:'30px', color:'#CC7351'}}>Search<SearchIcon/></p>
                    <Divider/>
                    <br/><br/>
                      <Box sx={{ flexGrow: 1 }}>
                          <Grid  item xs={12}>
                              <Search/>
                          </Grid>
                        </Box>
                </TabPanel>
                <TabPanel value={value} index={1}>
                    Item Two
                </TabPanel>
                <TabPanel value={value} index={2}>
                    <ClientProfile></ClientProfile>
                </TabPanel>
                <TabPanel value={value} index={3}>
                </TabPanel>
                <TabPanel value={value} index={4}>
                    Item Five
                </TabPanel>
                <TabPanel value={value} index={5}>
                    Item Six
                </TabPanel>
                <TabPanel value={value} index={6}>
                  {/* <ReservationHistory></ReservationHistory> */}
                </TabPanel>
                </Box>
            </Container>
            </ThemeProvider>

  );
}