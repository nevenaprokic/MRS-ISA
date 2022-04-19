import axios from "axios";
import api from "../app/api";

export function getShipByShipOwnerEmail(username){
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
export function getShipById(id){
    return api
        .get("/getShipInfo", {
            params:{
                idShip:id
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}