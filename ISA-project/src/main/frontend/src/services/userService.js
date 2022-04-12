import axios from "axios";
import api from "../app/api";

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