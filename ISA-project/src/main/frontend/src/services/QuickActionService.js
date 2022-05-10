import axios from "axios";
import api from "../app/api";

export default function getQuickActionByOfferId(id){
    return api
        .get("quick-reservation/get", {
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