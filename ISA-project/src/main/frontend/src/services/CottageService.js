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

export function searchCottagesClient(params, setOffers, setLastSearchedOffers) {
  console.log(params);

  return api
    .post("/cottage/search-cottages-client", {...params,
       dateFrom:new Date(params.dateFrom).toISOString().split('T')[0],
       dateTo:new Date(params.dateTo).toISOString().split('T')[0],})
    .then((data) => {
      if (data.data.length == 0) {
        toast.info("There are no cottages that match the search parameters.", {
          position: toast.POSITION.BOTTOM_RIGHT,
          autoClose: 2000,
        });
      }
      setOffers(data.data);
      setLastSearchedOffers(data.data);
    })
    .catch((err) => {
      console.log("Nije uspesno dobavljeno");
      return err.message;
    });
}

export function filterCottagesClient(params, setOffers, lastSearchedOffers) {
  params.maxRating = params.maxRating == "" ? Infinity : params.maxRating;
  params.maxPrice = params.maxPrice == "" ? Infinity : params.maxPrice;
  params.maxPeople = params.maxPeople == "" ? Infinity : params.maxPeople;

  const filterOffers = (offer) => {
    return (offer.price <= params.maxPrice && offer.price >= params.minPrice)
      && (offer.numberOfPerson <= params.maxPeople && offer.numberOfPerson >= params.minPeople)
      && (offer.mark <= params.maxRating && offer.mark >= params.minRating);
 }
  let filtered = lastSearchedOffers.filter(filterOffers);
  if(filtered.length == 0)
    toast.info("No cottages that satisfie these filters.", {
      position: toast.POSITION.BOTTOM_RIGHT,
      autoClose: 2000,
    });
  setOffers(filtered);
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

export function sortCottages(value, sortAsc, offers, setOffers) {

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
        return compareString(sortAsc, a.state, b.state);
      });
      break;
      case 5:
        offers.sort((a, b) => {
          return (sortAsc) ?  a.mark - b.mark : b.mark - a.mark;
        });
        break;
    case 6:
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

function compareString(sortAsc, first, second){
  return (sortAsc) ?  ((first > second) ? 1 : ((second > first) ? -1 : 0)) : ((first < second) ? 1 : ((second < first) ? -1 : 0))
}