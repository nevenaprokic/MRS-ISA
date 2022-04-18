import { useState } from "react";
import { Button } from "@mui/material";
import Grid from '@mui/material/Grid';
import UploadImageGallery from "./UploadImageGallery";
import { styled } from '@mui/material/styles';

function UploadPictureForm({pictureInputList, pictureSetInputList}){

    const Input = styled('input')({
        display: 'none',
      });

    const handlePictureUpload = function(e) {
        let files = e.target.files;
        console.log(e.target.files[0]);
        const selectedPictures = [...pictureInputList];
        const targetPicture = e.target.files;
        const targetPicturesObj = [...targetPicture];
        var FR= new FileReader();
        FR.readAsDataURL(files[0]);
      
        FR.onload = (event) => {
            console.log(event.target.result);
            selectedPictures.push(event.target.result);
        }
        

        // targetPicturesObj.map((file) =>{
        //     return (URL.createObjectURL(file))
        // })
        pictureSetInputList(selectedPictures);
    }

    
    //UVEZIVANJE 2 KOMPNENTE
    return(

       <Grid continer spacing={10}>
           <Grid item xs={12} sm={5}>
                <label htmlFor="contained-button-file">
                <Input accept="image/*" id="contained-button-file" multiple type="file" onChange={(e) => handlePictureUpload(e)}/>
                <Button variant="contained" component="span" color="inherit" >
                    Upload photos
                    </Button>
                </label>
            
            </Grid>
            <Grid>
                <label className="uploadPhotoLabel">You can add photos from previous adventures</label>
            </Grid>
            <Grid item xs={12}>
                
                    <UploadImageGallery images={pictureInputList} pictureSetInputList={pictureSetInputList}/>
                
                
            </Grid>
       </Grid>
    );
}

export default UploadPictureForm;