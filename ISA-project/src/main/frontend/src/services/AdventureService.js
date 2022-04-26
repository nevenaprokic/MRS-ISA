import axios from "axios";
import api from "../app/api";

export function getAdventureByInstructorEmail(username){
    return api
        .get("/adventure/instructor-adventures", {
            params:{
                email:username
            }
        })
        .then((response) => response)
        .catch((err) => {
            alert(err.data)
        });
}

export function getAdventureById(id){
    return api
        .get("/adventure/details", {
            params:{
                id:id
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}

