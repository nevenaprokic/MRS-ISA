import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import {compareString} from './UtilService'

export function getShipByShipOwnerEmail(username) {
  return api
    .get("/ship/get-all-by-owner", {
      params: {
        email: username,
      },
    })
    .then((data) => {
      if (data.data.length == 0) {
        toast.info("You don't have any ships.", {
          position: toast.POSITION.BOTTOM_RIGHT,
          autoClose: 2000,
        });
      }
      return data;
    })
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}
export function getShipById(id) {
  return api
    .get("/ship/get-info", {
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
    .get("/ship/get-all")
    .then((data) => data)
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}

export function searchShips(params, setOffers) {
  params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople;
  params.price = params.price == "" ? -1 : params.price;
  return api
    .get("/ship/search", { params })
    .then((data) => {
      if (data.data.length == 0) {
        toast.info("There are no ships that match the search parameters.", {
          position: toast.POSITION.BOTTOM_RIGHT,
          autoClose: 2000,
        });
      }
      setOffers(data.data);
    })
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}

export function searchShipByShipOwner(params, setOffers) {
  params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople;
  params.price = params.price == "" ? -1 : params.price;
  params.shipOwnerUsername = getUsernameFromToken();
  return api
    .get("/ship/search-by-owner", { params })
    .then((data) => {
      if (data.data.length == 0) {
        toast.info(
          "You don't have any ships that match the search parameters.",
          {
            position: toast.POSITION.BOTTOM_RIGHT,
            autoClose: 2000,
          }
        );
      }
      setOffers(data.data);
    })
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}

export function addShip(shipData, additionalServiceData) {
  let email = getUsernameFromToken();
  shipData.append("email", email);
  api
    .post("/ship/add", shipData)
    .then((responseData) => {
      let shipId = responseData.data;
      addAddtionalServices(shipId, additionalServiceData);
      toast.success("You successfully added a new ship.", {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 2000,
      });
    })
    .catch((err) => {
      toast.error("You made a mistake, try again.", {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 2000,
      });
    });
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
    .then((responseData) => console.log("uspesno"))
    .catch((errMessage) => alert(errMessage.data));
}

export function sortShips(value, sortAsc, offers, setOffers) {
  console.log(offers);
  switch(value) {
    case 1:
      offers.sort((a, b) => {
        return compareString(sortAsc, a.name, b.name);
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
    case 4:
      offers.sort((a, b) => {
        return (sortAsc) ?  a.mark - b.mark : b.mark - a.mark;
      });
      break;
    case 5:
      offers.sort((a, b) => {
        return (sortAsc) ?  a.price - b.price : b.price - a.price;
      });
      break;
    case 6:
      offers.sort((a, b) => {
        return (sortAsc) ?  a.price - b.price : b.price - a.price;
      });
      break;
      case 7:
        offers.sort((a, b) => {
          return (sortAsc) ?  a.price - b.price : b.price - a.price;
        });
        break;
    default:
      offers.sort((a, b) => {
        return compareString(sortAsc, a.name, b.name);
    });
  }
  setOffers([...offers]);
}