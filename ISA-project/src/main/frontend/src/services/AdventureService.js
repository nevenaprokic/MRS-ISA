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