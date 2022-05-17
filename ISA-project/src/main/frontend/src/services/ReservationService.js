import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import { toast } from "react-toastify";
import { arrayToDateString } from "./UtilService";

export function calculatePrice(days, price, additionalServices){
    days = (days == "") ? 0 : days;
    let total = 0;
    for(let service of additionalServices){
        total += service.servicePrice;
    }
    total += days * price;
    return total;
}

export function makeReservation(params, close){
    params = {...params, "email": getUsernameFromToken()};
    api
    .post("/reservation/make", params)
    .then((responseData) => {
        toast.success(responseData.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                            });
        close();
})
    .catch((err) => 
        toast.error(err.response.data, {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 1500, }));
}

export function convertParams(action, offer){
    return {
        services: action.additionalServices,
        total: action.price,
        guests: action.numberOfPerson,
        date: arrayToDateString(action.startDate),
        endingDate: arrayToDateString(action.endDate),
        offerId: offer,
        actionId: action.id
    };
}

export function getAllReservation(){
    let email = getUsernameFromToken();
    console.log(email);
    return api
    .get("/reservation/get-all-by-cottage-owner", {
        params: {
            ownerId: email ,
        },
      })
    .then((responseData) => responseData)
    .catch((err) => {toast.error(err.response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    })}
        )

}

