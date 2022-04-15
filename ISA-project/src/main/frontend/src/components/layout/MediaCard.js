import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import { createTheme, ThemeProvider } from '@mui/material/styles';
const secondaryTheme = createTheme({
    palette: {
      primary: { main:'#9DAB86'},
      secondary: { main:'#ffffff'},
    },

});

export default function MediaCard() {
    
  return (
    <ThemeProvider theme={secondaryTheme}>
        <Card sx={{ maxWidth: 345 }}>
        <CardMedia
            component="img"
            height="140"
            image="/static/images/cards/contemplative-reptile.jpg"
            alt="green iguana"
        />
        <CardContent>
            <Typography gutterBottom variant="h5" component="div" color="primary">
            Name : 
            </Typography>
            <Typography variant="body2" color="text.secondary">
            Description : 
            </Typography>
            <Typography variant="body2" color="text.secondary">
            Mark : 
            </Typography>
        </CardContent>
        <CardActions>
            <Button size="small" variant="contained" bgcolor="secondary" color='primary'>View</Button>
        </CardActions>
        </Card>
    </ThemeProvider>
  );
}
