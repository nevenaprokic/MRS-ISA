import axios from "axios";
import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import "react-toastify/dist/ReactToastify.css";
import { toast } from "react-toastify";
import {compareString} from './UtilService'

export function getCottageByCottageOwnerEmail(username) {
  return api
    .get("/cottage/get-cottages-by-owner-email", {
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
    .get("/cottage/get-info", {
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
    .get("/cottage/get-all")
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
    .get("/cottage/search", { params })
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
  if(params.dateFrom <= params.dateTo && params.dateFrom > new Date()){
      return api
      .post("/cottage/search-client", {...params,
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
        toast.error(err.message, {
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

export function filterCottagesClient(params, setOffers, lastSearchedOffers) {
  let maxRating = params.maxRating == "" ? Infinity : params.maxRating;
  let maxPrice = params.maxPrice == "" ? Infinity : params.maxPrice;
  let maxPeople = params.maxPeople == "" ? Infinity : params.maxPeople;

  let minRating = params.minRating == "" ? -1 : params.minRating;
  let minPrice = params.minPrice == "" ? -1 : params.minPrice;
  let minPeople = params.minPeople == "" ? -1 : params.minPeople;

  const filterOffers = (offer) => {
    return (offer.price <= maxPrice && offer.price >= minPrice)
      && (offer.numberOfPerson <= maxPeople && offer.numberOfPerson >= minPeople)
      && (offer.mark <= maxRating && offer.mark >= minRating);
 }
  let filtered = lastSearchedOffers.filter(filterOffers);
  if(filtered.length == 0)
    toast.info("No offers that satisfy these filters.", {
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
    .get("/cottage/search-by-owner", { params })
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
    .post("/cottage/add", cottageData)
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
    default:
      offers.sort((a, b) => {
        return compareString(sortAsc, a.name, b.name);
    });
  }
  setOffers([...offers]);
}
export function checkReservation(cottageData) {
  return api
    .get("/cottage/allowed-operation", {
      params: {
        cottageId: cottageData.id,
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
export function deleteCottage(cottageId) {
  return api
    .get("/cottage/delete", {
      params: {
        cottageId: cottageId,
      },
    })
    .then((response) => {
      toast.success(
        "You successfully deleted the cottage!",
        {
          position: toast.POSITION.BOTTOM_RIGHT,
          autoClose: 1500,
        }
      );
    })
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
export function updateCottage(cottageData, additionalServices){
  api
  .post("/cottage/update", cottageData)
  .then((responseData) => {
       updateAdditionalServices(cottageData.id, additionalServices);
  })
  .catch((err) => {toast.error(err.response.data, {
                  position: toast.POSITION.BOTTOM_RIGHT,
                  autoClose: 1500,
              });
          });
}
function updateAdditionalServices(offerId, additionalServiceDTOS){
  console.log(additionalServiceDTOS);
  api
  .post("/cottage/update-cottage-services",  {
      params:{
          offerId : offerId,
          additionalServiceDTOS : additionalServiceDTOS
      }
  })
  .then((responseData) => {
                      toast.success(responseData.data, {
                          position: toast.POSITION.BOTTOM_RIGHT,
                          autoClose: 1500,
                      });
                  })
  .catch((errMessage) => {toast.error(errMessage.response.data, {
                          position: toast.POSITION.BOTTOM_RIGHT,
                          autoClose: 1500,
                      });
                  });
}