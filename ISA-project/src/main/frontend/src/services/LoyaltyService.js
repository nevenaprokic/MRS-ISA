
import api from "../app/api";
import { toast } from "react-toastify";

export function getAllOwnerCategories(){
    return api
    .get("/loyalty/owner-categories")
    .then((responseData) => responseData)
    .catch((err)  => {toast.err("Something went wrong!", {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    });
                })
}

export function getAllClientCategories(){
    return api
    .get("/loyalty/client-categories")
    .then((responseData) => responseData)
    .catch((err)  => {toast.error("Something went wrong!", {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    });
                })
}