import { Grid, Box, Button} from "@mui/material";
import "./CottageProfilePage.scss";
import HomeIcon from '@mui/icons-material/Home';
import MeetingRoomIcon from '@mui/icons-material/MeetingRoom';
import HotelIcon from '@mui/icons-material/Hotel';
import MeetingRoom from "@mui/icons-material/MeetingRoom";
import ArticleIcon from '@mui/icons-material/Article';
import Typography from '@mui/material/Typography';
import { createTheme } from '@mui/material/styles';
import { ThemeProvider } from "@emotion/react";
import ReadMoreReact from 'read-more-react';
import ShowMoreText from "react-show-more-text";
import {getAddressByCottageId} from '../../../services/AddressService';
import * as React from 'react';
import { useState, useEffect } from 'react';

const theme = createTheme({
    palette: {
      primary: {
        main: "#99A799",
      },
      secondary: {
        main: "#ADC2A9",
      },
    },
  });

  let shownTextlength = 250; //maksimalna velicina stringa za prvi prikaz
  
  function executeOnClick(isExpanded) {
        console.log(isExpanded);
    }





function BasicAdventureInfiBox({basicInfo}){

    const [addressData, setAddressData] = useState();
  
    useEffect(() => {
        async function setData() {
        let address = await getAddressByCottageId(basicInfo.id);
        setAddressData(!!address ? address.data : {});   

        return address;    
    }
      setData();
       
    }, [])
    if(addressData){
        return (
        
            <div className="basicInfoContainer">
                 <div>
        
                    <div className="basicBoxItem">
                        <HomeIcon color="action"/>
                    </div>
                    <label className="basicBoxItemTitle">Address: </label>
                    <label className="basicBoxItemText">{addressData.street}, {addressData.city}, {addressData.state}</label>
                </div>
                <div>
        
                    <div className="basicBoxItem">
                        <MeetingRoom color="action"/>
                    </div>
                    <label className="basicBoxItemTitle">Rooms number: </label>
                    <label className="basicBoxItemText">{basicInfo.roomNumber}</label>
                </div>
                <div>
        
                    <div className="basicBoxItem">
                        <HotelIcon color="action"/>
                    </div>
                    <label className="basicBoxItemTitle">Beds number: </label>
                    <label className="basicBoxItemText">{basicInfo.bedNumber}</label>
                </div>
                <div>
        
                    <div className="basicBoxItem">
                        <ArticleIcon color="action"/>
                    </div>
                    <label className="basicBoxItemTitle">Description: </label>
                    {/* <ReadMoreReact text={description}
                        
                        ideal={200}
                        max={250}
                        readMoreText={"Click here to read more.."}
                    />    */}
                    <div className="descriptionText">
                        <ShowMoreText
                        
                            lines={7}
                            more="Show more"
                            less="Show less"
                            className="content-css"
                            anchorClass="my-anchor-css-class"
                            onClick={executeOnClick}
                            expanded={false}
                            width={280}
                            truncatedEndingComponent={"... "}
                        >
                            {basicInfo.description}
                        </ShowMoreText>
                    </div>
                   
                    
  
                    
                </div>

            </div>
             

    );

    }
    
}

export default BasicAdventureInfiBox;