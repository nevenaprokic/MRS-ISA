import MediaCard from "../layout/MediaCard";
import * as React from 'react';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import {getCottageByCottageOwnerEmail} from '../../services/CottageService';
import { useState, useEffect } from 'react';
import { getUsernameFromToken } from "../../app/jwtTokenUtils";

export default function Album(){
  const [cottageData, setCottageData] = useState();
  
    useEffect(() => {
        async function setcottageData() {
        let username = getUsernameFromToken();
        const cottages = await getCottageByCottageOwnerEmail(username);
        setCottageData(!!cottages ? cottages.data : {});     

        return cottages;    
    }
      setcottageData();
       
    }, [])
    if(cottageData){
      return(<Container sx={{ py: 8 }} maxWidth="md">
      <Grid container spacing={4}>
        {cottageData.map((cottage) => (
          console.log(cottage),
          <Grid item key={cottage} xs={12} sm={6} md={4}>
            <MediaCard cottage={cottage}></MediaCard>
          </Grid>
        ))}
      </Grid>
    </Container>);
    }
    
}