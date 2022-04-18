import MediaCard from "../layout/MediaCard";
import * as React from 'react';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import {getCottageByCottageOwnerEmail} from '../../services/CottageService';
import { useState, useEffect } from 'react';
import { getUsernameFromToken , getRoleFromToken} from "../../app/jwtTokenUtils";
import { userType } from "../../services/userService";
import { getAdventureByInstructorEmail } from "../../services/AdventureService";


export default function Album(){
  const [albumData, setCottageData] = useState();
  let nesto = {
    [userType.COTTAGE_OWNER] :  getCottageByCottageOwnerEmail,
    [userType.INSTRUCTOR] :  getAdventureByInstructorEmail
  }
  
    useEffect(() => {
        async function setcottageData() {
          let role = getRoleFromToken();
          let username = getUsernameFromToken();
          const offersData = await nesto[role](username);
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
            <MediaCard cottage={offer}></MediaCard>
          </Grid>
        ))}
      </Grid>
    </Container>);
    }
    
}