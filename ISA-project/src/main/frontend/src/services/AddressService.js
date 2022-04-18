import axios from "axios";
import api from "../app/api";

export default function getAddressByCottageId(id){
    return api
        .get("/getAddressInfo", {
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