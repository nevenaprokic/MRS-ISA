import "./AdventureProfilePage.scss" ;
import ImagesBox from "./ImagesBox";

import QuickActionBox from "../cottageProfile/QuickActionBox";
import BasicAdventureInfiBox from "./BasicAdventureInfoBox";
import AdditionalDescriptionBox from "./AdditionalDescriptionBox";
import PriceList from "../cottageProfile/Pricelist";
import { Grid, Box, Button} from "@mui/material";
import { ThemeProvider } from "@emotion/react";
import { createTheme } from '@mui/material/styles';
import { getAdventureById, checkUpdateAllowed } from "../../../services/AdventureService";
import { useState, useEffect } from "react";
import ChangeAdventureForm from "../../forms/adventure/ChangeAdventureForm";
import Modal from '@mui/material/Modal';
import { toast } from "react-toastify";
import { getMarkByOfferId } from "../../../services/MarkService";
import Rating from "@mui/material/Rating";
import MapBox from "../cottageProfile/MapBox";


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

 

function AdventureProfilePage({id, close, childToParentMediaCard}){

  
    const [adventureData, setAdventureData] = useState();

    const [openChangeForm, setOpenForm] = useState(false);

    const [markData, setMarkData] = useState();

    const handleOpenForm = () => {
        console.log("TU");
        async function checkAllowed(){
            let allowed = await checkUpdateAllowed(adventureData);
            console.log(allowed);
            if(allowed){
                setOpenForm(true);
            }   
            else{
                toast.error("Update is not allowed because this adventure has reservations.", {
                    position: toast.POSITION.BOTTOM_RIGHT,
                    autoClose: 1500,
                });
            }
        }
    checkAllowed();

    }
    
    const handleCloseForm = () => {
        setOpenForm(false);
    }

    
    const childToParent = (childData) => {
            setAdventureData(prevState => ({
                ...prevState,
                ["offerName"]: childData.offerName,
                ["price"]: childData.price,
                ["id"]: childData.id,
                ["city"]: childData.city,
                ["street"]: childData.street,
                ["state"]: childData.state,
                ["description"]: childData.description,
                ["rulesOfConduct"]: childData.rulesOfConduct,
                ["additionalEquipment"]: childData.additionalEquipment,
                ["cancelationConditions"]: childData.cancelationConditions,
                ["peopleNum"]: childData.peopleNum,
                ["additionalServices"]: childData.additionalServices,
                ["photos"]:childData.photos,

            })
            ); 

            childToParentMediaCard(childData);
      }
      

    useEffect(() => {
        async function getAdventureData(){
            let adventure = await getAdventureById(id);
            setAdventureData(!!adventure ? adventure.data : {});
            
            return adventure;
        } 
        getAdventureData();

        async function setData() {
            const markData = await getMarkByOfferId(id);
            setMarkData(markData.data ? markData.data : "0");
            return markData.data;
          }
        setData();
    }, []);

    function createServiceData() {
        let rows = [];
        adventureData.additionalServices.forEach((data) => {
            let name = data.serviceName;
            let price = data.servicePrice;
            rows.push({name, price});
          });
        return rows;
      }
    

    let images = [];

    function setImages(){
        adventureData.photos.forEach(photo_byte => {
            let img_src = "data:image/png;base64," + photo_byte
            images.push({image: img_src});
        });
    }
 
    return( 
        !!adventureData && 
        <div className="adventureProfile">
           { setImages()}
        <ThemeProvider theme={theme}>
            <div className="profileContainer">
                <div className="closeProfileBtn">
                    <Button size="large" sx={{}} onClick={() => close()}>
                    x
                    </Button>
                </div>
                <div className="headerContainer">
                    <h2 className="adventureTittle">{adventureData.offerName}</h2>
                    <div className="changeBtn" >
                        <Button variant="contained" onClick={handleOpenForm}>Change info</Button>
                    </div>    
                </div>
                {!!markData && 
                <div className="mark">
                    <Rating name="half-rating-read" precision={0.5} value={markData} readOnly />
                </div>}
                <Modal
                    open={openChangeForm}
                    onClose={handleCloseForm}
                    aria-labelledby="modal-modal-title"
                    aria-describedby="modal-modal-description"
                    sx={{backgroundColor:"rgb(218, 224, 210, 0.6)", overflow:"auto"}}
                >
                        <ChangeAdventureForm currentAdventureData={adventureData} closeForm={handleCloseForm} childToParent={childToParent}/>
                    
                </Modal>
                
                <ImagesBox images={images}/>
                <QuickActionBox id={adventureData.id}/>
                <MapBox street={adventureData.street} city={adventureData.city} state={adventureData.state}/>
                <Grid container xs={12}>
                    <Grid item xs={12} sm={6} >
                        <BasicAdventureInfiBox basicInfo={adventureData}/>
                    </Grid>
                    <Grid item xs={12} sm={6} >
                        <AdditionalDescriptionBox additionalDate={adventureData} />
                    </Grid>
                
                    
                </Grid>
                <PriceList offer={adventureData} additionalServices={createServiceData()}/>
            </div>
        </ThemeProvider>
        </div>
    );
}
export default AdventureProfilePage;