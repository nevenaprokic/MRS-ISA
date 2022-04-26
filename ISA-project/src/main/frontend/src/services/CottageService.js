import axios from "axios";
import api from "../app/api";
import {getUsernameFromToken} from "../app/jwtTokenUtils";

export function getCottageByCottageOwnerEmail(username){
    return api
        .get("/getCottages", {
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
export function getCottageById(id){
    return api
        .get("/getCottageInfo", {
            params:{
                idCottage:id
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}

export function getCottages(){
    return api
        .get("/getAllCottages")
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}

export function searchCottages(params, setOffers){
    params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople; 
    params.price = params.price == "" ? -1 : params.price; 
    console.log(params);
    return api
        .get("/searchCottages",  {params})
        .then((data) => setOffers(data.data))
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}
export function searchCottagesByCottageOwner(params, setOffers){
    params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople; 
    params.price = params.price == "" ? -1 : params.price; 
    params.cottageOwnerUsername = getUsernameFromToken();
    return api
        .get("/searchCottagesByCottageOwner",  {params})
        .then((data) => setOffers(data.data))
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}