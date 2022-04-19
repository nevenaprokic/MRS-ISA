import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import OwnerProfile from "../components/profilePages/OwnerProfile";


export const userType = {
    CLIENT: "CLIENT",
    INSTRUCTOR: "INSTRUCTOR",
    COTTAGE_OWNER:"COTTAGE_OWNER" ,
    SHIP_OWNER: "SHIP_OWNER"
}

Object.freeze(userType);


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

export function addAdventure(adventureData){
    let email = getUsernameFromToken();
    adventureData["ownerEmail"] = email;
    api
    .post("/adventure/addAdventure", adventureData)
    .then((responseData) => alert(responseData.data))
    .catch((err) => alert(err.data));
}

export function changeOwnerData(newOwnerData){
    let email = getUsernameFromToken();
    newOwnerData["email"] = email;
    api
    .post("/changeOwnerData", newOwnerData)
    .then((responseData) => {
                alert(responseData.data); 
                window.location = "/user-profile/instructor"})
    .catch((err) => alert(err.data));
}

