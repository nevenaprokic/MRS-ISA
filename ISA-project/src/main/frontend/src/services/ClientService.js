import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";

export const sendDeleteRequestClient = (data) => {
    console.log(data);
    api
      .post("deleteAccount", { ...data,
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
      .get("deletion-requested?email=" + getUsernameFromToken())
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

