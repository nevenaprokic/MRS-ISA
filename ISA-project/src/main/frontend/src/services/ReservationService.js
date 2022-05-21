import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken, getRoleFromToken } from "../app/jwtTokenUtils";
import { toast } from "react-toastify";
import { arrayToDateString } from "./UtilService";

export function calculatePrice(days, price, additionalServices, guests){
    days = (days == "") ? 0 : days;
    let total = 0;
    for(let service of additionalServices){
        total += days * guests * service.servicePrice;
    }
    total += days * price;
    return total;
}

export function makeReservation(params, close){
    params = {...params, "email": getUsernameFromToken()};
    api
    .post("/reservation/make", params)
    .then((responseData) => {
        console.log(responseData.data);
        toast.success(responseData.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                            });
       
        close();
    }).catch((err) => 
        toast.error(err.response.data, {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 1500, }));
}

export function convertParams(action, offer){
    let o = {
        services: action.additionalServices,
        total: action.price,
        guests: action.numberOfPerson,
        date: arrayToDateString(action.startDate),
        endingDate: arrayToDateString(action.endDate),
        offerId: offer,
        actionId: action.id
    };
    return o;
}

export function getAllReservation(){
    let email = getUsernameFromToken();
    let role = getRoleFromToken();
    console.log(email);
    return api
    .get("/reservation/get-all-by-cottage-owner", {
        params: {
            ownerId: email ,
            role: role
        },
      })
    .then((responseData) => responseData)
    .catch((err) => {toast.error(err.response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    })}
        )

}
export function getAllReservationShipOwner(){
    let email = getUsernameFromToken();
    let role = getRoleFromToken();
    console.log(email);
    return api
    .get("/reservation/get-all-by-ship-owner", {
        params: {
            ownerId: email ,
            role: role
        },
      })
    .then((responseData) => responseData)
    .catch((err) => {toast.error(err.response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    })}
        )

}
export function isAvailableOffer(data){
    return api
        .get("reservation/available-offer", {
            params:{
                offerId:data.offerId,
                startDate:data.startDateReservation,
                dayNum: data.daysReservation
            }
        })
        .then((data) => {console.log("DA LI JE DOZVOLJENO"); console.log(data.data); return data.data;})
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}

