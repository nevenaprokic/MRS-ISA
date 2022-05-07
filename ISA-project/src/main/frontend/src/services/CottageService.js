import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";

import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";

export function getCottageByCottageOwnerEmail(username) {
  return api
    .get("/cottage/getCottages", {
      params: {
        email: username,
      },
    })
    .then((data) => {
      if (data.data.length == 0) {
        toast.info("You don't have any cottages.", {
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
export function getCottageById(id) {
  return api
    .get("/cottage/getCottageInfo", {
      params: {
        idCottage: id,
      },
    })
    .then((data) => data)
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}

export function getCottages() {
  return api
    .get("/cottage/getAllCottages")
    .then((data) => data)
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}

export function searchCottages(params, setOffers) {
  params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople;
  params.price = params.price == "" ? -1 : params.price;
  return api
    .get("/cottage/searchCottages", { params })
    .then((data) => {
      if (data.data.length == 0) {
        toast.info("There are no cottages that match the search parameters.", {
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

export function searchCottagesClient(params, setOffers) {
  return api
    .get("/cottage/search-cottages-client", { params })
    .then((data) => {
      if (data.data.length == 0) {
        console.log("JUJU NEVERA")
        toast.info("There are no cottages that match the search parameters.", {
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


export function searchCottagesByCottageOwner(params, setOffers) {
  params.maxPeople = params.maxPeople == "" ? -1 : params.maxPeople;
  params.price = params.price == "" ? -1 : params.price;
  params.cottageOwnerUsername = getUsernameFromToken();
  return api
    .get("/cottage/searchCottagesByCottageOwner", { params })
    .then((data) => {
      if (data.data.length == 0) {
        toast.info(
          "You don't have any cottages that match the search parameters.",
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
export function addCottage(cottageData, additionalServiceData) {
  let email = getUsernameFromToken();
  cottageData.append("email", email);
  api
    .post("/cottage/addCottage", cottageData)
    .then((responseData) => {
      let cottageId = responseData.data;
      addAddtionalServices(cottageId, additionalServiceData);
      toast.success("You successfully added a new cottage.", {
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
    .post("/cottage/add-additional-services", {
      params: {
        offerId: offerId,
        additionalServiceDTO: additionalServiceDTO,
      },
    })
    .then((responseData) => console.log("Uspesno"))
    .catch((errMessage) => alert(errMessage.data));
}
