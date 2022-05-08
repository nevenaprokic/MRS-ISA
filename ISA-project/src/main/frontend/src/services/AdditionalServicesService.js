import axios from "axios";
import api from "../app/api";
import { toast } from "react-toastify";

export function getAdditionalServiceByOffer(id){
    return api
        .get("/getAdditionalServiceInfo", {
            params:{
                id:id
            }
        })
        .then((data) => data)
        .catch((err) => {toast.error(err.response.data, {
            position: toast.POSITION.BOTTOM_RIGHT,
            autoClose: 1500,
        });
    })
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
    .then((responseData) => {
                            toast.success(responseData.data, {
                                position: toast.POSITION.BOTTOM_RIGHT,
                                autoClose: 1500,
                            });
                        })
    .catch((errMessage) => {toast.error(errMessage.response.data, {
                                position: toast.POSITION.BOTTOM_RIGHT,
                                autoClose: 1500,
                            });
                        });
}