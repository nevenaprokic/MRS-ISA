import axios from "axios";
import api from "../app/api";

export function getAdditionalServiceByOffer(id){
    return api
        .get("/getAdditionalServiceInfo", {
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