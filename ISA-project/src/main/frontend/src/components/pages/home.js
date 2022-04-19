import MediaCard from "../layout/MediaCard";
import * as React from 'react';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import {getCottageByCottageOwnerEmail} from '../../services/CottageService';
import { useState, useEffect } from 'react';
import { getUsernameFromToken , getRoleFromToken} from "../../app/jwtTokenUtils";
import { userType } from "../../services/userService";
import { getAdventureByInstructorEmail } from "../../services/AdventureService";
import {getShipByShipOwnerEmail} from '../../services/ShipService';


export default function Album(){
  const [albumData, setCottageData] = useState();
  let getOfferByOwnerEmail = {
    [userType.COTTAGE_OWNER] :  getCottageByCottageOwnerEmail,
    [userType.INSTRUCTOR] :  getAdventureByInstructorEmail,
    [userType.SHIP_OWNER]: getShipByShipOwnerEmail
  }
  
    useEffect(() => {
        async function setcottageData() {
          let role = getRoleFromToken();
          let username = getUsernameFromToken();
          const offersData = await getOfferByOwnerEmail[role](username);
          console.log(offersData);
          setCottageData(!!offersData ? offersData.data : {});     

        return offersData;    
    }
      setcottageData();
       
    }, [])
    if(albumData){
      return(<Container sx={{ py: 8 }} maxWidth="md">
      <Grid container spacing={4}>
        {albumData.map((offer) => (
          console.log(offer),
          <Grid item key={offer} xs={12} sm={6} md={4}>
            <MediaCard offer={offer}></MediaCard>
          </Grid>
        ))}
      </Grid>
    </Container>);
    }
    
}