
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
    window.location = "/user-profile/instructor";
  }

  const addNewAdventureHandler = function(){
    window.location = "/instructor/add-adventure";
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
              <Button>Home page</Button>
              <Button onClick={openProfilePage}>My profile</Button>
              <Divider />
              <Button>My adventures</Button>
              <Divider />
              <Button onClick={addNewAdventureHandler}>Add new adventure</Button>
              <Button>Reservation history</Button>
              <Button>Reservation reports</Button>
              <Button>New reservation</Button>
              <Button>Add new action</Button>
              <Divider />
              <Button>My calendar</Button>
              <Divider />
              <Button>Business reports</Button>
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