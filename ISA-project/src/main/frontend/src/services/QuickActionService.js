import axios from "axios";
import api from "../app/api";

export function getQuickActionByOfferId(id){
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
export function alreadyExistQuickReservationForOffer(data){
    return api
        .get("quick-reservation/already-exist", {
            params:{
                offerId:data.offerId,
                startDate:data.startDateAction,
                dateNumber: data.daysAction
            }
        })
        .then((data) => {console.log("DA LI JE DOZVOLJENO"); console.log(data.data); return data.data;})
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}
export function isAvailablePeriod(data){
    return api
        .get("quick-reservation/available-period", {
            params:{
                offerId:data.offerId,
                startDate:data.startDateReservation,
                dateNumber: data.daysReservation
            }
        })
        .then((data) => {console.log("DA LI JE DOZVOLJENO"); console.log(data.data); return data.data;})
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}
