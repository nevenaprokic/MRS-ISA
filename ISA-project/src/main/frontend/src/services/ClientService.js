import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";

export const sendDeleteRequestClient = (data) => {
    console.log(data);
    api
      .post("client/delete-account", { ...data,
          "email": getUsernameFromToken(),
        })
      .then((res) => {
          toast.success( res.data, {position: toast.POSITION.BOTTOM_RIGHT, autoClose:1500});
      })
      .catch((err) => {
        toast.error( err.response.data , {position: toast.POSITION.BOTTOM_RIGHT, autoClose:1500});
      });
}

export const isDeletionRequested = (handleOpenDelete) => {
  let deleted = false;
  api
      .get("/client/deletion-requested?email=" + getUsernameFromToken())
      .then((res) => {
          deleted = res.data;
          if(!deleted)
            handleOpenDelete();
          else{
            toast.error( "You have already requested deletion.", {position: toast.POSITION.BOTTOM_RIGHT, autoClose:1500});
          }
      })
      .catch((err) => {
        toast.error( "Something went wrong." , {position: toast.POSITION.BOTTOM_RIGHT, autoClose:1500});
      });
}

export function getClientByCottageOwnerEmail(email){
  return api
    .get("/client/get-by-reservation-cottage", {
      params: {
        ownerEmail: email,
      },
    })
    .then((response) => {console.log(response.data);  return response.data;})
    .catch((err) => {
      toast.error(
        "Somethnig went wrong. Please wait a fiew seconds and try again.",
        {
          position: toast.POSITION.BOTTOM_RIGHT,
          autoClose: 1500,
        }
      );
    });
}
export function getClientByShipOwnerEmail(email){
  return api
    .get("/client/get-by-reservation-ship", {
      params: {
        ownerEmail: email,
      },
    })
    .then((response) => {console.log(response.data);  return response.data;})
    .catch((err) => {
      toast.error(
        "Somethnig went wrong. Please wait a fiew seconds and try again.",
        {
          position: toast.POSITION.BOTTOM_RIGHT,
          autoClose: 1500,
        }
      );
    });
}
export function getClientByInstructorEmail(email){
  return api
    .get("/client/get-by-reservation-adventure", {
      params: {
        ownerEmail: email,
      },
    })
    .then((response) => {console.log(response.data);  return response.data;})
    .catch((err) => {
      toast.error(
        "Somethnig went wrong. Please wait a fiew seconds and try again.",
        {
          position: toast.POSITION.BOTTOM_RIGHT,
          autoClose: 1500,
        }
      );
    });
}

