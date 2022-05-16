
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import { toast } from "react-toastify";

let eventId = 0;

export function getCalendarEvents(offerId, setEvents){
    let username = getUsernameFromToken();
    return api
    .get("/calendar/info", {
        params:{
            ownerEmail: username,
            offerId: offerId
        }
    })
    .then((responseData) => responseData)
    .catch((err) => {toast.error("Something went wrong.", {
                    position: toast.POSITION.BOTTOM_RIGHT,
                    autoClose: 1500,
                });
            });
}

export function generateCalendarEvents(evetns){
   
    const calendarEvents = []
    eventId = 0;
    evetns.forEach(event => {
        calendarEvents.push(
            {
                id: createEventId(),
                title: event.title,
                start: event.startDate,
                end: event.endDate + "T23:59:00",
                application_id: event.id,
                isReservation: event.reservation

            }
        )
    });
    return calendarEvents;
}


export function createEventId() {
  return String(eventId++)
}


export function getReservationDetails(reservationId){
    return api
    .get("calendar/reservation-info", {
        params:{
            reservationId: reservationId
        }
    })
    .then((responseData) => responseData)
    .catch((err) => {toast.error("Something went wrong.", {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    });
                });
}


