import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";

import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";

export function changeShipOwnerData(newShipOwnerData) {
  let email = getUsernameFromToken();
  newShipOwnerData["email"] = email;
  api
    .post("/changeShipOwnerData", newShipOwnerData)
    .then((responseData) => {
      toast.success("You successfully changed the data.", {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 2000,
      });
    })
    .catch((err) => alert(err.data));
}

export function getShipOwnerByUsername(username) {
  return api
    .get("/shipOwnerProfileInfo", {
      params: {
        email: username,
      },
    })
    .then((responseData) => responseData)
    .catch((err) => {
      console.log("Nije uspesna prijava");
      return err.message;
    });
}
