import LoyaltyCard from "./LoyaltyCard";
import { useEffect, useState } from "react";
import "./LoyaltyProgram.scss";
import { loyaltyCategory, userType } from "../../app/Enum";
import { getAllClientCategories, getAllOwnerCategories } from "../../services/LoyaltyService";
import Autocomplete from '@mui/material/Autocomplete';
import Grid from '@mui/material/Grid';
import Container from '@mui/material/Container';
import TextField from '@mui/material/TextField';
import Tabs from '@mui/material/Tabs';
import Tab from '@mui/material/Tab';
import Divider from '@mui/material/Divider';
import { Button, Typography } from "@mui/material";

function LoyalyProgeramPage(){

    const [loyaltyCategories, setLoyaltyCategories] = useState();
    const [categoryType, setCategorType] = useState();
    console.log("aaadadassafasfafs", setLoyaltyCategories);


    function handleClientCategory(){
        async function getData(){      
            const responseData = await getAllClientCategories();
            setLoyaltyCategories(responseData.data ? responseData.data : {}); 
            setCategorType(userType.CLIENT);
        }
        getData();
    }
    function handleOwnerCategory(){
        async function getData(){
                const responseData = await getAllOwnerCategories();
                console.log(responseData.data);
                setLoyaltyCategories(responseData.data ? responseData.data : {});
                setCategorType(userType.OWNER);
                console.log("qqqq", categoryType);
            }
            
        getData();


    }

    return (
        <div className="categoriesContainer">
            <div>
            <div className="categoryBtn">
            <Typography variant="h6" sx={{color:"#5f6d5f"}}>
                Select categories
            </Typography>
            </div>
            
            <div className="categoryBtn">
                <Button
                    size="midle"
                    variant="outlined"
                    bgcolor="secondary"
                    color="primary"
                    onClick={handleClientCategory}
                >
                    CLient categories
                </Button>
            </div>
            <div className="categoryBtn">
                    <Button
                        size="midle"
                        variant="outlined"
                        bgcolor="secondary"
                        color="primary"
                        onClick={handleOwnerCategory}
                    >
                            Owner categories
                        </Button>
                    
            </div>
            </div>
            {!!loyaltyCategories && !!setLoyaltyCategories &&
            <Container sx={{ py: 8}} >
                <Grid container spacing={5}> 
                    {loyaltyCategories.map((category) => (
                    <Grid item key={category.name} xs={15} sm={4}>
                        <LoyaltyCard type={categoryType} category={category} setLoyaltyCategories={setLoyaltyCategories}/>           
                    </Grid>
                    ))}
                </Grid>
            </Container>
            }
            
        </div>
        
    );

}

export default LoyalyProgeramPage;