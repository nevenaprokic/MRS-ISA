import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";

export function changeShipOwnerData(newShipOwnerData){
    let email = getUsernameFromToken();
    newShipOwnerData["email"] = email;
    api
    .post("/changeShipOwnerData", newShipOwnerData)
    .then((responseData) => {
                alert(responseData.data); 
                })
    .catch((err) => alert(err.data));
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