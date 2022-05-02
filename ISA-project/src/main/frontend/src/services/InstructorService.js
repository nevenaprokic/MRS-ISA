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

export function getInstructors(){
    return api
        .get("/getAllInstructors")
        .then((data) => data )
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