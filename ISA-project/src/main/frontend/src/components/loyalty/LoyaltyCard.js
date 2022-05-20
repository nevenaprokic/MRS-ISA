import { addYears } from "date-fns";
import { useEffect, useState } from "react";
import * as React from 'react';
import { styled } from '@mui/material/styles';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardMedia from '@mui/material/CardMedia';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { red } from '@mui/material/colors';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import CreateIcon from '@mui/icons-material/Create';
import DeleteIcon from '@mui/icons-material/Delete';
import categoryIcon from '../images/laurel.png';
import { loyaltyCategory } from "../../app/Enum";
import { userType } from "../../app/Enum";
import ChangeLoyaltyCategory from "../forms/loyaltyCategory/ChangeLoyaltyCategory";
import Modal from '@mui/material/Modal';

function LoyaltyCard({category, type}){

    let color = category.categoryColor;
    console.log(category);
    const [openChangeForm, setOpenChangeForm] = useState(false);
    
    function handleOpenForm(){ setOpenChangeForm(true)}
    function handleCloseForm() { setOpenChangeForm(false)}
    

    return(
        !!category && !!type &&
        
        <Card sx={{ border:"solid thin #ADC2A9"}} >
        <CardHeader
          avatar={
            <Avatar 
                src={categoryIcon}
                sx={{ width: 56, height: 56 }}>
            </Avatar>
          }
          title={category.name}
          sx={{fontSize:"35px", color:{color}}}
          subheader={type == userType.CLIENT ? "Client category" : "Owner category"}
        />
  
        <CardContent>
          <Typography variant="body2" color="text.secondary">
            Points for successful reservation: {category.reservationPoints}
          </Typography>
          {type == userType.CLIENT ? (
          <Typography variant="body2" color="text.secondary">
            Discount on reservation: {category.discount} %
          </Typography>
          ) : ( 
         <Typography variant="body2" color="text.secondary">
            Earning percent from reservation: {category.earningsPercent} %
          </Typography>
          )}
          <Typography variant="body2" color="text.secondary">
            Lower limit of points: {category.lowLimitPoints}
          </Typography>
          <Typography variant="body2" color="text.secondary">
            Upper limit of points: {category.heighLimitPoints}
          </Typography>
        </CardContent>
        <CardActions disableSpacing>
          <IconButton aria-label="change" sx={{color:"#CC7351"}} onClick={handleOpenForm}>
            <CreateIcon />
          </IconButton>
          <IconButton aria-label="delete" sx={{color:"#CC7351"}}>
            <DeleteIcon />
          </IconButton>
        </CardActions>

        <Modal
            open={openChangeForm}
            onClose={handleCloseForm}
            aria-labelledby="modal-modal-title"
            aria-describedby="modal-modal-description"
            sx={{backgroundColor:"rgb(218, 224, 210, 0.6)", overflow:"auto"}}
        >
                        <ChangeLoyaltyCategory close={handleCloseForm} loyaltyCategory={category} categoryType={type}/>
                    
        </Modal>
      </Card>
    );

}

export default LoyaltyCard;