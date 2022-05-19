import * as React from 'react';
import Typography from '@mui/material/Typography';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemText from '@mui/material/ListItemText';
import Grid from '@mui/material/Grid';

const products = [
  {
    name: 'Cena ponude',
    price: '$110',
  },
  {
    name: 'Klima',
    price: '$10',
  },
  {
    name: 'Dorucak',
    price: '$4',
  }
];

const payments = [
  { name: 'Pocetak akcije', detail: '20/05/2022' },
  { name: 'Kraj akcije', detail: '25/05/2022' },
  { name: 'Pocetak rezervacije', detail: '04/06/2022' },
  { name: 'Kraj rezervacije', detail: '06/06/2022' },
];

export default function Review() {
  return (
    <React.Fragment>
      <Typography variant="h6" gutterBottom color={"#CC7351"}>
        Quick reservation summary
      </Typography>
      <List disablePadding>
        {products.map((product) => (
          <ListItem key={product.name} sx={{ py: 1, px: 0 }}>
            <ListItemText primary={product.name} secondary={product.desc} />
            <Typography variant="body2">{product.price}</Typography>
          </ListItem>
        ))}

        <ListItem sx={{ py: 1, px: 0 }}>
          <ListItemText primary="Total" />
          <Typography variant="subtitle1" sx={{ fontWeight: 700 }}>
            $124
          </Typography>
        </ListItem>
      </List>
      <Grid container spacing={2}>
        <Grid item xs={12} sm={6}>
          <Typography variant="h6" gutterBottom sx={{ mt: 2 }} color={"#CC7351"}>
            Offer
          </Typography>
          <Typography gutterBottom>Ime: Sumska vila</Typography>
          <Typography gutterBottom>Broj osoba: 4</Typography>
        </Grid>
        <Grid item container direction="column" xs={12} sm={6}>
          <Typography variant="h6" gutterBottom sx={{ mt: 2 }} color={"#CC7351"}>
            Date
          </Typography>
          <Grid container>
            {payments.map((payment) => (
              <React.Fragment key={payment.name}>
                <Grid item xs={6}>
                  <Typography gutterBottom>{payment.name}</Typography>
                </Grid>
                <Grid item xs={6}>
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