
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import { toast } from "react-toastify";

export function getCalendarEvents(offerId){
    let username = getUsernameFromToken();
    api
    .get("/calendar/info", {
        params:{
            ownerEmail: username,
            offerId: offerId
        }
    })
    .then((responseData) => console.log(responseData.data))
    .catch((err) => {toast.error("Something went wrong.", {
                    position: toast.POSITION.BOTTOM_RIGHT,
                    autoClose: 1500,
                });
            });
}
