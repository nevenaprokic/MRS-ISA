import React from 'react'
import {getCottages} from '../../services/CottageService';
import {getShips} from '../../services/ShipService';
import {getInstructors, offerType, userType} from '../../services/userService';
import { useState, useEffect } from "react";
import MediaCard from "../layout/MediaCard";
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';

export default function OfferList({type, offers, setOffers}) {

    let getOffers = {
      [offerType.COTTAGE] : getCottages,
      [offerType.SHIP] : getShips,
      [userType.INSTRUCTOR] : getInstructors
    };

    useEffect(() => {
        async function setData() {
          const offersData = await getOffers[type]();
          console.log("Offers:data")
          console.log(offersData);
          setOffers(offersData ? offersData.data : {});     

        return offersData;    
        }
        setData();
    }, [])

    if(offers){
      return(<Container sx={{ py: 8 }} maxWidth="md">
          <Grid container spacing={4}>
            {offers.map((offer) => (
              <Grid item key={offer.name} xs={12} sm={6} md={4}>
                <MediaCard offer={offer} offerT={type}></MediaCard>
              </Grid>
            ))}
          </Grid>
        </Container>);
      
    }
}
