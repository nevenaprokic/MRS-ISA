import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import { toast } from "react-toastify";

export function getInstructorByUsername(username){
    return api
       .get("/instructorProfileInfo",{
        params: {
            email: username
          }
       } 
     )
       .then((responseData) => responseData)
    .catch((err) => {toast.success("Instructor does not found", {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    });
            });
}
export function changeInstructorData(newOwnerData){
    let email = getUsernameFromToken();
    newOwnerData["email"] = email;
    api
    .post("/change-instructor-data", newOwnerData)
    .then((responseData) => {toast.success(responseData.data, {
                                position: toast.POSITION.BOTTOM_RIGHT,
                                autoClose: 1500,
                            });
                        } )
    .catch((err) =>  {toast.error(err.response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    });
                });
}

export function getInstructors(){
    return api
        .get("/getAllInstructors")
        .then((data) => data )
        .catch((err) => {toast.error("Something went wrong.", {
            position: toast.POSITION.BOTTOM_RIGHT,
            autoClose: 1500,
        });
    });
}

export function searchInstructors(params, setOffers){
    console.log(params);
    return api
        .get("/searchInstructors",  {params})
        .then((data) => setOffers(data.data))
        .catch((err) => {
            {toast.error("Instructor doesn't exists", {
                position: toast.POSITION.BOTTOM_RIGHT,
                autoClose: 1500,
            });
        }
        });
}