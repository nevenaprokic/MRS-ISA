import axios from "axios";
import api from "../app/api";

export function getAdventureByInstructorEmail(username){
    return api
        .get("/adventure/instructor", {
            params:{
                email:username
            }
        })
        .then((response) => alert(response.data))
        .catch((err) => {
            alert(err.data)
        });
}