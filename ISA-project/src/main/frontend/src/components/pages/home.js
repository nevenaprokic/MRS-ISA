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
import MediaCardShip from '../layout/MediaCardShip';


export default function Album(){
  const [albumData, setCottageData] = useState();
  let role = getRoleFromToken();
  let getOfferByOwnerEmail = {
    [userType.COTTAGE_OWNER] :  getCottageByCottageOwnerEmail,
    [userType.INSTRUCTOR] :  getAdventureByInstructorEmail,
    [userType.SHIP_OWNER]: getShipByShipOwnerEmail
  }
  
  let getOfferProfileByRole = {
    [userType.COTTAGE_OWNER] :  getCottageProfile,
    [userType.INSTRUCTOR] :  getShipProfile,
    [userType.SHIP_OWNER]: getShipProfile
  }

  function getCottageProfile(offer){
      return (
        <MediaCard offer={offer}></MediaCard>
      );
  }

  function getShipProfile(offer){
    return (
      <MediaCardShip offer={offer}></MediaCardShip>
    );
}
    useEffect(() => {
        async function setcottageData() {
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
                {getOfferProfileByRole[getRoleFromToken()](offer)}
                
              </Grid>
            ))}
          </Grid>
        </Container>);
      // }else if(role === userType.SHIP_OWNER){
      //   return(<Container sx={{ py: 8 }} maxWidth="md">
      //     <Grid container spacing={4}>
      //       {albumData.map((offer) => (
      //         console.log(offer),
      //         <Grid item key={offer} xs={12} sm={6} md={4}>
      //           <MediaCardShip offer={offer}></MediaCardShip>
      //         </Grid>
      //       ))}
      //     </Grid>
      //   </Container>);
      // }
      
    }
    
}