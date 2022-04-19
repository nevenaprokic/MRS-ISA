import axios from "axios";
import api from "../app/api";

export default function getShipByShipOwnerEmail(username){
    return api
    .get("/getShips", {
        params:{
            email:username
        }
    })
    .then((data) => data)
    .catch((err) => {
        console.log("Nije uspesno dobavljeno");
        return err.message;
    });
}