import axios from "axios";
import api from "../app/api";

export function getMarkByOfferId(id){
    return api
        .get("/mark/get", {
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
