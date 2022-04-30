import axios from "axios";
import api from "../app/api";
import {getUsernameFromToken} from "../app/jwtTokenUtils";

export function getShipByShipOwnerEmail(username){
    return api
    .get("/getShips", {
        params:{
            email:username
        }
    })
    .then((data) => data)
    .catch((err) => {
        console.log("Nije uspesno dobavljeno");
        return err.message;
    });
}
export function getShipById(id){
    return api
        .get("/getShipInfo", {
            params:{
                idShip:id
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}

export function getShips(){
    return api
        .get("/getAllShips")
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}

export function searchShips(params, setOffers){
    params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople; 
    params.price = params.price == "" ? -1 : params.price; 
    console.log(params);
    return api
        .get("/searchShips",  {params})

export function searchShipByShipOwner(params, setOffers){
    params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople; 
    params.price = params.price == "" ? -1 : params.price; 
    params.shipOwnerUsername = getUsernameFromToken();
    return api
        .get("/searchShipByShipOwner",  {params})
        .then((data) => setOffers(data.data))
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}

