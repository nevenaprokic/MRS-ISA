import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import { toast } from "react-toastify";

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
    console.log(params);
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
    .catch((err) => alert(err.data));
}

