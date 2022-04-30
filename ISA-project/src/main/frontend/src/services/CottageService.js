import axios from "axios";
import api from "../app/api";
import {getUsernameFromToken} from "../app/jwtTokenUtils";

export function getCottageByCottageOwnerEmail(username){
    return api
        .get("/cottage/getCottages", {
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
        .get("/cottage/getCottageInfo", {
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
        .get("/cottage/getAllCottages")
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
        .get("/cottage/searchCottages",  {params})
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
        .get("/cottage/searchCottagesByCottageOwner",  {params})
        .then((data) => setOffers(data.data))
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}
export function addCottage(cottageData, additionalServiceData){
    let email = getUsernameFromToken();
    cottageData.append('email', email);
    api
    .post("/cottage/addCottage", cottageData)
    .then((responseData) => {
        let cottageId = responseData.data;
        addAddtionalServices(cottageId, additionalServiceData);
        alert(responseData.data);})
    .catch((err) => alert(err.data));
}

function addAddtionalServices(offerId, additionalServiceDTO){
    console.log(additionalServiceDTO);
    api
    .post("/cottage/add-additional-services",  {
        params:{
            offerId : offerId,
            additionalServiceDTO : additionalServiceDTO
        }
    })
    .then((responseData) => alert(responseData.data))
    .catch((errMessage) => alert(errMessage.data));
}

