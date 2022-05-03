import axios from "axios";
import api from "../app/api";
import {addAddtionalServices} from "./AdditionalServicesService";
import { getUsernameFromToken } from "../app/jwtTokenUtils";

export function getAdventureByInstructorEmail(username){
    return api
        .get("/adventure/instructor-adventures", {
            params:{
                email:username
            }
        })
        .then((response) => response)
        .catch((err) => {
            alert(err.data)
        });
}

export function getAdventureById(id){
    return api
        .get("/adventure/details", {
            params:{
                id:id
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}

export function updateAdventure(adventureData, additionalServices){
    api
    .post("/adventure/update-adventure", adventureData)
    .then((responseData) => {
        updateAdditionalServices(adventureData.id, additionalServices);
    })
    .catch((err) => {
        console.log(err);
        alert(err.data)});
}

function updateAdditionalServices(offerId, additionalServiceDTOS){
    console.log(additionalServiceDTOS);
    api
    .post("/adventure/update-adventure-services",  {
        params:{
            offerId : offerId,
            additionalServiceDTOS : additionalServiceDTOS
        }
    })
    .then((responseData) => alert(responseData.data))
    .catch((errMessage) => alert(errMessage.data));
}

export function addAdventure(adventureData, additionalServices){
    let email = getUsernameFromToken();
    adventureData.append('email', email);
    console.log(adventureData.get("offerName"));
    api
    .post("/adventure/addAdventure", adventureData)
    .then((responseData) => {
        let adventureId = responseData.data;
        addAddtionalServices(adventureId, additionalServices);
    })
    .catch((err) => {
        console.log(err);
        alert(err.data)});
}

export function searchAdventureByInstructor(params, setOffers){
    params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople; 
    params.price = params.price == "" ? -1 : params.price; 
    params.cottageOwnerUsername = getUsernameFromToken();
    // return api
    //     .get("/adventure/search-adventure",  {params})
    //     .then((data) => setOffers(data.data))
    //     .catch((err) => {
    //         console.log("Nije uspesno dobavljeno");
    //         return err.message;
    //     });
    
}


