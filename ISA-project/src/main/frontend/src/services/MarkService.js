import axios from "axios";
import api from "../app/api";

export function getMarkByOfferId(id){
    return api
        .get("/getMark", {
            params:{
                id:id
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}
export let images = {
    ["5"]: require("../components/images/5.png"),
    ["4.5"]: require("../components/images/4_5.png"),
    ["4"]: require("../components/images/4.png"),
    ["3.5"] : require("../components/images/3.5.png"),
    ["3"]: require("../components/images/3.png"),
    ["2.5"]:require("../components/images/2.5.png"),
    ["2"]: require("../components/images/2.png"),
    ["1.5"]:require("../components/images/1.5.png"),
    ["1"]: require("../components/images/1.png"),
    ["0.5"]:require("../components/images/0_5.png"),
    ["0"]: require("../components/images/nula.png"),
  };