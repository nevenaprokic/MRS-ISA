import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import { toast } from "react-toastify";
import {compareString} from './UtilService'

export function getInstructorByUsername(username){
    return api
       .get("/instructor/profile-info",{
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
    .post("/instructor/change-data", newOwnerData)
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
        .get("/instructor/get-all")
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
        .get("/instructor/search",  {params})
        .then((data) =>{
          setOffers(data.data);
          if (data.data.length == 0) {
            toast.info(
              "You don't have any instructors that match the search parameters.",
              {
                position: toast.POSITION.BOTTOM_RIGHT,
                autoClose: 2000,
              }
            );
        }})
        .catch((err) => {
            {toast.error("Something went wrong.", {
                position: toast.POSITION.BOTTOM_RIGHT,
                autoClose: 1500,
            });
        }
        });
}

export function sendDeleteRequestInstructor(data){
    return api
    .post("/instructor/delete-profile-request?email=" + getUsernameFromToken(), data)
    .then((data) => {
      toast.success("You have successfully submitted a request to delete the order.", {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 2000,
      });
    })
    .catch((err) => {
      toast.error(err.response.data, {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 2000,
      });
    });
}

export function filterInstructorsClient(){

}

export function sortInstructors(value, sortAsc, offers, setOffers){
  switch(value) {
    case 8:
      offers.sort((a, b) => {
        return compareString(sortAsc, a.firstName, b.firstName);
    });
      break;
    case 2:
      offers.sort((a, b) => {
        return compareString(sortAsc, a.street, b.street);
      });
      break;
    case 3:
      offers.sort((a, b) => {
        return compareString(sortAsc, a.city, b.city);
      });
      break;
    case 9:
      offers.sort((a, b) => {
        return compareString(sortAsc, a.lastName, b.lastName);
      });
      break;
    default:
      offers.sort((a, b) => {
        return compareString(sortAsc, a.firstName, b.firstName);
    });
  }
  setOffers([...offers]);
}

export function searchInstructorsClient(params, setOffers, setLastSearchedOffers){
  console.log(params);
  if(params.dateFrom <= params.dateTo && params.dateFrom > new Date()){
    return api
        .get("/instructor/search-client",  {params})
        .then((data) =>{
          if (data.data.length == 0) {
            toast.info(
              "You don't have any instructors that match the search parameters.",
              {
                position: toast.POSITION.BOTTOM_RIGHT,
                autoClose: 2000,
              }
            );
        }
          setOffers(data.data);
          setLastSearchedOffers(data.data);
        })
        .catch((err) => {
            {toast.error("Something went wrong.", {
                position: toast.POSITION.BOTTOM_RIGHT,
                autoClose: 1500,
            });
        }
        });
    }else{
      toast.error("Date periods are not correct.", {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 2000,
      });
      return;
  }
}