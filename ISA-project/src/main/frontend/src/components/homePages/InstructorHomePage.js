
import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import CssBaseline from '@mui/material/CssBaseline';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import { Button } from '@mui/material';
import logo from '../../logo.png';


const drawerWidth = 240;

export default function InstructorHomePage() {

  const openProfilePage = function(){
    console.log("caoooo");
    window.location = "/user-profile/instructor";
  }

  return (
    <Box sx={{ display: 'flex' }}>
      <CssBaseline />
      <AppBar
        position="fixed"
        sx={{ width: `calc(100% - ${drawerWidth}px)`, ml: `${drawerWidth}px` }}
      >
        
      </AppBar>
      <Drawer
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          '& .MuiDrawer-paper': {
            width: drawerWidth,
            boxSizing: 'border-box',
          },
        }}
        variant="permanent"
        anchor="left"
      >
        <div><img src={logo} /></div>
        <Toolbar />
        
        <Divider />
        <List>
              <Button>Pocetna strana</Button>
              <Button onClick={openProfilePage}>Profilna strana</Button>
              <Divider />
              <Button>Dostupne avanture</Button>
              <Divider />
              <Button>Istorija rezervacija</Button>
              <Button>Izvestaji o rezervacijama</Button>
              <Button>Nova rezervacija</Button>
              <Button>Kreiranje akcija</Button>
              <Divider />
              <Button>Kalendar</Button>
              <Divider />
              <Button>Izvestaj o poslovanju</Button>
          </List>
      </Drawer>
      <Box
        component="main"
        sx={{ flexGrow: 1, bgcolor: 'background.default', p: 3 }}
      >
        <Toolbar />
        <Typography paragraph>
          
        </Typography>
        <Typography paragraph>
          
        </Typography>
      </Box>
    </Box>
  );
}