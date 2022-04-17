import axios from "axios";
import api from "../app/api";

export function getCottageByCottageOwnerEmail(username){
    return api
        .get("/getCottages", {
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
export function getCottageById(id){
    console.log(id.id);
    return api
        .get("/getCottageInfo", {
            params:{
                idCottage:id.id
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}