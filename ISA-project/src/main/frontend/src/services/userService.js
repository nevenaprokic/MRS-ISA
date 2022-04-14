import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";

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

export function addAdventure(adventureData){
    let email = getUsernameFromToken();
    adventureData["ownerEmail"] = email
    api
    .post("/adventure/addAdventure", adventureData)
    .then((responseData) => alert(responseData))
    .catch((err) => alert(err));
}