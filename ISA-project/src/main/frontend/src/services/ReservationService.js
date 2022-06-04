import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken, getRoleFromToken } from "../app/jwtTokenUtils";
import { toast } from "react-toastify";
import { arrayToDateString, compareString, dateDiffInDays, stringToDate } from "./UtilService";

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
        date: arrayToDateString(action.startDate).toISOString().split('T')[0],
        endingDate: arrayToDateString(action.endDate).toISOString().split('T')[0],
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
        .then((data) => {return data.data;})
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });

}
export function makeReservationOwner(reservation){
    api
    .post("/reservation/make-by-owner", reservation)
    .then((responseData) => {
      toast.success("You successfully added a new quick resrevation.", {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 2000,
      });
    })
    .catch((err) => {
      toast.error("You made a mistake, try again.", {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 2000,
      });
    });
}
export function getAllReportCottageOwner(){
    let email = getUsernameFromToken();
    let role = getRoleFromToken();
    console.log(email);
    return api
    .get("/reservation-report/get-all-by-cottage-owner", {
        params: {
            ownerEmail: email ,
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
export function addReport(params){
    api
    .post("/reservation-report/add", params)
    .then((responseData) => {
        console.log(responseData.data);
        toast.success(responseData.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                            });
       
    }).catch((err) => 
        toast.error(err.response.data, {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 1500, }));
}

export function getUpcomingCottageReservationsClient(){
    let email = getUsernameFromToken();
  
    return api
    .get("/reservation/get-upcoming-cottage-reservations-by-client", {
        params: {
            email: email
        },
      })
    .then((responseData) => responseData)
    .catch((err) => {toast.error(err.response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    })}
        )
  }
  
  export function getUpcomingShipReservationsClient(){
    let email = getUsernameFromToken();
  
    return api
    .get("/reservation/get-upcoming-ship-reservations-by-client", {
        params: {
            email: email
        },
      })
    .then((responseData) => responseData)
    .catch((err) => {toast.error(err.response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    })}
        )
  }
  
  export function getUpcomingAdventureReservationsClient(){
    let email = getUsernameFromToken();
  
    return api
    .get("/reservation/get-upcoming-adventure-reservations-by-client", {
        params: {
            email: email
        },
      })
    .then((responseData) => responseData)
    .catch((err) => {toast.error(err.response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    })}
        )
  
  }

  export function cancelReservation(id, removeFromTable){
    return api
    .delete("/reservation/" + id)
    .then((response) => {
        toast.success(response.data, {
            position: toast.POSITION.BOTTOM_RIGHT,
            autoClose: 1500,
        });
         removeFromTable();
    })
    .catch((err) => {toast.error(err.response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    })}
        )
  }
  
export function sortReservations(criteria, sortAsc, reservations, setReservations){
   
    switch(criteria) {
        case 1:
            reservations.sort((a, b) => {
            let d1 = stringToDate(a.startDate);
            let d2 = stringToDate(b.startDate);

            return (sortAsc) ? d1 - d2 : d2 - d1;
        });
          break;
        case 2:
            reservations.sort((a, b) => {
            return (sortAsc) ?  a.price - b.price : b.price - a.price;
          });
          break;
        case 3:
            reservations.sort((a, b) => {
                console.log()
                let duration1 = dateDiffInDays(a.startDate, a.endDate);
                let duration2 = dateDiffInDays(b.endDate, b.startDate);
                return (sortAsc) ?  duration1 - duration2 : duration2 - duration1;
          });
          break;
        default:
            reservations.sort((a, b) => {
            return compareString(sortAsc, a.startDate, b.startDate);
        });
      }
      setReservations([...reservations]);
}


