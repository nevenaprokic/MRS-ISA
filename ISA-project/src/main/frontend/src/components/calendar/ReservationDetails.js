import * as React from 'react';
import PropTypes from 'prop-types';
import Typography from '@mui/material/Typography';
import Grid from '@mui/material/Grid';
import Card from '@mui/material/Card';
import CardActionArea from '@mui/material/CardActionArea';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import { ThemeProvider } from '@emotion/react';
import { Button } from '@mui/material';
import { createTheme } from '@mui/material';
import "./WorkingCalendar.scss"
import { useEffect, useState } from 'react';
import { getReservationDetails } from '../../services/CalendarService';
import PersonOutlineIcon from '@mui/icons-material/PersonOutline';

function ReservationDetails({reservationId, setReservationId, close}){
    
    console.log("data", reservationId);
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

    const [reservationData, setReservationData] = useState();

    useEffect(() => {
        async function set(){
            if(!!reservationId){
                const reservation = await getReservationDetails(reservationId);
                console.log("detalji", reservation.data);
                setReservationData(reservation.data ? reservation.data : {});
                return reservation;
            }
       
        }
      set();
    }, [])
    
    function writeAdditionalServices(){
        let services = ""
        reservationData.additionalServices.forEach(element => {
            services += element.serviceName + ", "
        });
        return services.substring(0, services.length - 2);
    }
      
    return(
        !!reservationData &&
        <div className="reservationDetailsContainer">
        <ThemeProvider theme={theme}>
            <Grid item xs={12} md={6}>
            <CardActionArea component="a" href="#">
                <Card sx={{ display: 'flex' }}>
                <CardContent sx={{ flex: 1 }}>
                    <div>
                        <div className="headerItem">
                            <Typography component="h2" variant="h5">
                            {reservationData.offerName}
                            </Typography>
                        </div>
                        <div className="closeBtn">
                            <Button size="large" onClick={() => close()} sx={{}}>
                            x
                            </Button>
                        </div>
                    </div>
                    
                    <Typography variant="subtitle1" color="text.secondary">
                         {reservationData.startDate} - {reservationData.endDate}
                    </Typography>
                    <Typography variant="subtitle1" paragraph>
                        client: {reservationData.clienName}
                    </Typography>
                    <div>
                        <div className="personNumLabel">
                            <PersonOutlineIcon style={theme.secondary}></PersonOutlineIcon>
                        </div>
                        <div className="personNumLabel">
                            <Typography variant="subtitle1" paragraph>
                                {reservationData.numberOfPerson}
                            </Typography>
                        </div>
                    </div>
                   
                    <Typography variant="subtitle1" paragraph>
                      additional services: {writeAdditionalServices()}
                    </Typography>
                    
                    <Typography variant="subtitle1" color="secondary" sx={{fontWeight: "bold"}}>
                        Price: {reservationData.price} €
                    </Typography>
                </CardContent>
                <CardMedia
                    component="img"
                    sx={{ width: 160, display: { xs: 'none', sm: 'block' } }}
                    image= { "data:image/jpg;base64," +   reservationData.offerPhoto}
                    //alt={post.imageLabel}
                />
                </Card>
            </CardActionArea>
            </Grid>
        </ThemeProvider>
        </div>
    );
}

export default ReservationDetails;