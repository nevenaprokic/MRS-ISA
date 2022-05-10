import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import { toast } from "react-toastify";

export function changeAdminData(newAdminData){
    let email = getUsernameFromToken();
    newAdminData["email"] = email;
    api
    .post("/admin/change-data", newAdminData)
    .then((responseData) => {toast.success(responseData.data, {
                                position: toast.POSITION.BOTTOM_RIGHT,
                                autoClose: 1500,
                            });
})
    .catch((err) => alert(err.data));
}

export function getAdminByEmail(){
    let email = getUsernameFromToken();
    return api
    .get("/admin/profile-info", 
        {
            params:{
            email: email
        }
    })
    .then((responseData) => responseData)
    .catch((err) => alert(err.data));

}