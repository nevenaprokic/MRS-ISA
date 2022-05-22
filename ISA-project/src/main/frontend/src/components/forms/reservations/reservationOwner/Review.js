import * as React from 'react';
import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Grid from '@mui/material/Grid';

const additionalServices = [
  { serviceName: 'Klima ', servicePrice: '10' },
  { serviceName: 'Dorucak', servicePrice: '4' },
  { serviceName: 'Vecera', servicePrice: '5'},
  { serviceName: 'Rucak', servicePrice: '7'},
]

export default function Review() {

  let price = "100" + '€';
  let totalPrice = parseInt(100);
  console.log(additionalServices);
  if(additionalServices.length != 1 && additionalServices[0].servicePrice !== ''){
    additionalServices.map((additional) => {totalPrice+= parseInt(additional.servicePrice)});
  }
  
  const payments = [
    { name: 'Start date reservation: ', detail: "25/05/2022"},
    { name: 'End date reservation: ', detail: "29/05/2022"},
  ];
  
  return (
    <React.Fragment>
      <Typography variant="h6" gutterBottom color={"#CC7351"}>
        Reservation summary
      </Typography>
      <List disablePadding>
          <ListItem key={"Offer price"} sx={{ py: 1, px: 0 }}>
            <ListItemText primary={"Offer price"} />
            <Typography variant="body2">{price}</Typography>
          </ListItem>
          {(additionalServices.length != 0) && (additionalServices[0].servicePrice !== '') ? (additionalServices.map((product) => (
          <ListItem key={product.serviceName} sx={{ py: 1, px: 0 }}>
            <ListItemText primary={product.serviceName} />
            <Typography variant="body2">{product.servicePrice + "€"}</Typography>
          </ListItem>
        ))) : (
          <></>
        ) }

        <ListItem sx={{ py: 1, px: 0 }}>
          <ListItemText primary="Total" />
          <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
            {totalPrice + "€"}
          </Typography>
        </ListItem>
      </List>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={3}>
          <Typography variant="h6" gutterBottom sx={{ mt: 2 }} color={"#CC7351"}>
            Offer
          </Typography>
          <Typography gutterBottom>{'Name: ' + 'Sumska vila'}</Typography>
          <Typography gutterBottom>{'Number of guests: ' + '2'}</Typography>
        </Grid>
        <Grid item xs={12} sm={4}>
          <Typography variant="h6" gutterBottom sx={{ mt: 2 }} color={"#CC7351"}>
            Client
          </Typography>
          <Typography gutterBottom>{'Name and surname: ' + 'Pera Peric'}</Typography>
          <Typography gutterBottom>{'User name: ' + 'pera@gmail.com'}</Typography>
        </Grid>
        <Grid item container direction="column" xs={12} sm={5}>
          <Typography variant="h6" gutterBottom sx={{ mt: 2 }} color={"#CC7351"}>
            Date
          </Typography>
          <Grid container>
            {payments.map((payment) => (
              <React.Fragment key={payment.name}>
                <Grid item xs={7}>
                  <Typography gutterBottom>{payment.name}</Typography>
                </Grid>
                <Grid item xs={5}>
                  <Typography gutterBottom>{payment.detail}</Typography>
                </Grid>
              </React.Fragment>
            ))}
          </Grid>
        </Grid>
      </Grid>
    </React.Fragment>
  );
}