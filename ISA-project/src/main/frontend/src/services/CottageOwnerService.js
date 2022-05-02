import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";

export function changeCottageOwnerData(newCottageOwnerData){
    let email = getUsernameFromToken();
    newCottageOwnerData["email"] = email;
    api
    .post("/changeCottageOwnerData", newCottageOwnerData)
    .then((responseData) => {
                alert(responseData.data); 
                })
    .catch((err) => alert(err.data));
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