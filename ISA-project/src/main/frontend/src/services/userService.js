import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import OwnerProfile from "../components/profilePages/OwnerProfile";


export const userType = {
    CLIENT: "CLIENT",
    INSTRUCTOR: "INSTRUCTOR",
    COTTAGE_OWNER:"COTTAGE_OWNER" ,
    SHIP_OWNER: "SHIP_OWNER",
    ADMIN : "ADMIN"
}

Object.freeze(userType);

export const offerType = {
    ADVENTURE: 1,
    COTTAGE: 2 ,
    SHIP: 3,
    COTTAGE_OWNER: 4,
    SHIP_OWNER: 5
}

Object.freeze(offerType);

export const offerTypeByUserType = {
    INSTRUCTOR: offerType.ADVENTURE,
    COTTAGE_OWNER: offerType.COTTAGE ,
    SHIP_OWNER: offerType.SHIP
}

Object.freeze(offerTypeByUserType);


export function getInstructorByUsername(username){
    return api
       .get("/instructorProfileInfo",{
        params: {
            email: username
          }
       } 
     )
       .then((responseData) => responseData)
    .catch((err) => {
        console.log("Nije uspesna prijava");
        return err.message;
    });
}
export function getCottageOwnerByUsername(username){
    return api
       .get("/cottageOwnerProfileInfo",{
        params: {
            email: username
          }
       } 
     )
       .then((responseData) => responseData)
    .catch((err) => {
        console.log("Nije uspesna prijava");
        return err.message;
    });
}
export function getShipOwnerByUsername(username){
    return api
       .get("/shipOwnerProfileInfo",{
        params: {
            email: username
          }
       } 
     )
       .then((responseData) => responseData)
    .catch((err) => {
        console.log("Nije uspesna prijava");
        return err.message;
    });
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

function addAddtionalServices(offerId, additionalServiceDTOS){
    console.log(additionalServiceDTOS);
    api
    .post("/adventure/add-additional-services",  {
        params:{
            offerId : offerId,
            additionalServiceDTOS : additionalServiceDTOS
        }
    })
    .then((responseData) => alert(responseData.data))
    .catch((errMessage) => alert(errMessage.data));
}

export function changeOwnerData(newOwnerData){
    let email = getUsernameFromToken();
    newOwnerData["email"] = email;
    api
    .post("/changeOwnerData", newOwnerData)
    .then((responseData) => {
                alert(responseData.data); 
                })
    .catch((err) => alert(err.data));
}

export function changeInstructorData(newOwnerData){
    let email = getUsernameFromToken();
    newOwnerData["email"] = email;
    api
    .post("/change-instructor-data", newOwnerData)
    .then((responseData) => {
                alert(responseData.data); 
                })
    .catch((err) => alert(err.data));
}


export function getAdminByEmail(){
    let email = getUsernameFromToken();
    return api
    .get("/admin-profile", 
        {
            params:{
            email: email
        }
    })
    .then((responseData) => responseData)
    .catch((err) => alert(err.data));

}

export function getInstructors(){
    return api
        .get("/getAllInstructors")
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}

export function searchInstructors(params, setOffers){
    console.log(params);
    return api
        .get("/searchInstructors",  {params})
        .then((data) => setOffers(data.data))
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}

export function changeAdminData(newAdminData){
    let email = getUsernameFromToken();
    newAdminData["email"] = email;
    api
    .post("/change-admin-data", newAdminData)
    .then((responseData) => {
                alert(responseData.data); 
                })
    .catch((err) => alert(err.data));
}



