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

export function searchShipsClient(params, setOffers, setLastSearchedOffers) {
  console.log(params);
  console.log(params.dateFrom <= params.dateTo);
  console.log(params.dateFrom > new Date());

  if(params.dateFrom <= params.dateTo && params.dateFrom > new Date()){
      return api
      .post("/ship/search-client", {...params,
        dateFrom:new Date(params.dateFrom).toISOString().split('T')[0],
        dateTo:new Date(params.dateTo).toISOString().split('T')[0],})
      .then((data) => {
        if (data.data.length == 0) {
          toast.info("There are no ships that match the search parameters.", {
            position: toast.POSITION.BOTTOM_RIGHT,
            autoClose: 2000,
          });
        }
        setOffers(data.data);
        setLastSearchedOffers(data.data);
      })
      .catch((err) => {
          toast.error("Something went wrong.", {
          position: toast.POSITION.BOTTOM_RIGHT,
          autoClose: 2000,
        });
      });
  }else{
      toast.error("Date periods are not correct.", {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 2000,
      });
      return;
  }
}

export function filterShipsClient(params, setOffers, lastSearchedOffers) {
  
  let maxRating = params.maxRating == "" ? Infinity : params.maxRating;
  let maxPrice = params.maxPrice == "" ? Infinity : params.maxPrice;
  let maxPeople = params.maxPeople == "" ? Infinity : params.maxPeople;
  let maxSize = params.maxSize == "" ? Infinity : params.maxSize;

  let minRating = params.minRating == "" ? -1 : params.minRating;
  let minPrice = params.minPrice == "" ? -1 : params.minPrice;
  let minPeople = params.minPeople == "" ? -1 : params.minPeople;
  let minSize = params.minSize == "" ? -1 : params.minSize;

  const filterOffers = (offer) => {
    return (offer.price <= maxPrice && offer.price >= minPrice)
      && (offer.numberOfPerson <= maxPeople && offer.numberOfPerson >= minPeople)
      && (offer.mark <= maxRating && offer.mark >= minRating)
      && (offer.size <= maxSize && offer.size >= minSize);
 }
  let filtered = lastSearchedOffers.filter(filterOffers);
  if(filtered.length == 0)
    toast.info("No offers that satisfy these filters.", {
      position: toast.POSITION.BOTTOM_RIGHT,
      autoClose: 2000,
    });
  setOffers(filtered);
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
        return (sortAsc) ?  a.size - b.size : b.size - a.size;
      });
      break;
      case 7:
        offers.sort((a, b) => {
          return (sortAsc) ?  a.maxSpeed - b.maxSpeed : b.maxSpeed - a.maxSpeed;
        });
        break;
    default:
      offers.sort((a, b) => {
        return compareString(sortAsc, a.name, b.name);
    });
  }
  setOffers([...offers]);
}