import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";

export function getShipByShipOwnerEmail(username) {
  return api
    .get("/ship/getShips", {
      params: {
        email: username,
      },
    })
    .then((data) => data)
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}
export function getShipById(id) {
  return api
    .get("/ship/getShipInfo", {
      params: {
        idShip: id,
      },
    })
    .then((data) => data)
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}

export function getShips() {
  return api
    .get("/ship/getAllShips")
    .then((data) => data)
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}

export function searchShips(params, setOffers) {
  params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople;
  params.price = params.price == "" ? -1 : params.price;
  console.log(params);
  return api.get("/ship/searchShips", { params });
}

export function searchShipByShipOwner(params, setOffers) {
  params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople;
  params.price = params.price == "" ? -1 : params.price;
  params.shipOwnerUsername = getUsernameFromToken();
  return api
    .get("/ship/searchShipByShipOwner", { params })
    .then((data) => setOffers(data.data))
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}

export function addShip(shipData, additionalServiceData) {
  let email = getUsernameFromToken();
  shipData.append("email", email);
  api
    .post("/ship/addShip", shipData)
    .then((responseData) => {
      let shipId = responseData.data;
      addAddtionalServices(shipId, additionalServiceData);
      alert(responseData.data);
    })
    .catch((err) => alert(err.data));
}

function addAddtionalServices(offerId, additionalServiceDTO) {
  console.log(additionalServiceDTO);
  api
    .post("/ship/add-additional-services", {
      params: {
        offerId: offerId,
        additionalServiceDTO: additionalServiceDTO,
      },
    })
    .then((responseData) => alert(responseData.data))
    .catch((errMessage) => alert(errMessage.data));
}
