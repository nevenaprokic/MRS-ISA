
import { useEffect, useState } from "react";
import "./Graph.scss";
import {  userType } from "../../app/Enum";
import { getAllClientCategories, getAllOwnerCategories } from "../../services/LoyaltyService";
import Grid from "@mui/material/Grid";
import * as React from "react";
import Box from "@mui/material/Box";
import Divider from '@mui/material/Divider';
import { Button, Typography, IconButton } from "@mui/material";
import TimelineIcon from '@mui/icons-material/Timeline';
import App from './AttendanceSchedule';
import AccessTimeIcon from '@mui/icons-material/AccessTime';



function HomePageGraph(){

    const [loyaltyCategories, setLoyaltyCategories] = useState();
    const [categoryType, setCategorType] = useState();


    function handleClientCategory(){
        // return (<AttendanceSchedule/>);
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
        <div>
        <div className="graphContainer">
            <div>
            <p
              style={{
                marginTop: "0px",
                marginBottom: "0px",
                fontSize: "30px",
                color: "#CC7351",
              }}
            >
              Graph <TimelineIcon/>
            </p>
            <Divider />
            <br />
            <Box sx={{ flexGrow: 1 }}>
              <Grid item xs={12}>
              <div className="graphBtn">
                <Button
                    size="midle"
                    variant="outlined"
                    bgcolor="secondary"
                    color="primary"
                    onClick={handleClientCategory}
                >
                    Attendance report
                </Button>
            </div>
            <div className="graphBtn">
                    <Button
                        size="midle"
                        variant="outlined"
                        bgcolor="secondary"
                        color="primary"
                        onClick={handleOwnerCategory}
                    >
                            Income statement
                        </Button>
                    
            </div>
            <div className="graphBtn">
                    <Button
                        size="midle"
                        variant="outlined"
                        bgcolor="secondary"
                        color="primary"
                        onClick={handleOwnerCategory}
                    >
                            Evaluation report
                        </Button>
                    
            </div>
              </Grid>
            </Box>
            
            
            
            </div>
        </div>
        <div>
        <br />
        <p
              style={{
                marginTop: "0px",
                marginBottom: "0px",
                fontSize: "30px",
                color: "#CC7351",
              }}
            >
              Period <AccessTimeIcon/>
            </p>
            <Divider />
            <br />
            <Box sx={{ flexGrow: 1 }}>
              <Grid item xs={12}>
              <div className="graphBtn">
                <Button
                    size="midle"
                    variant="outlined"
                    bgcolor="secondary"
                    color="primary"
                    onClick={handleClientCategory}
                >
                     Yearly report
                </Button>
            </div>
            <div className="graphBtn">
                    <Button
                        size="midle"
                        variant="outlined"
                        bgcolor="secondary"
                        color="primary"
                        onClick={handleOwnerCategory}
                    >
                            
                            Monthly report
                        </Button>
                    
            </div>
            <div className="graphBtn">
                    <Button
                        size="midle"
                        variant="outlined"
                        bgcolor="secondary"
                        color="primary"
                        onClick={handleOwnerCategory}
                    >
                            Weekly report
                        </Button>
                    
            </div>
              </Grid>
            </Box>
        
        <App/>
  </div>
        </div>
    );

}

export default HomePageGraph;