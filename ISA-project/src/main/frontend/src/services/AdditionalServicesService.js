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

export function addAddtionalServices(offerId, additionalServiceDTOS){
    console.log(additionalServiceDTOS);
    api
    .post("/adventure/add-additional-services",  {
        params:{
            offerId : offerId,
            additionalServiceDTOS : additionalServiceDTOS
        }
    })
    .then((responseData) => alert(responseData.data))
    .catch((errMessage) => alert(errMessage.data));
}