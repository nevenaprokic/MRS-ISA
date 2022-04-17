import axios from "axios";
import api from "../app/api";

export default function getQuickActionByCottageId(id){
    return api
        .get("/getQuickActions", {
            params:{
                idCottage:id
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}