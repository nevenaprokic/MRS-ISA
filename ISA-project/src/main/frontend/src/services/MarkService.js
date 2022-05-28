import axios from "axios";
import api from "../app/api";

export function getMarkByOfferId(id){
    return api
        .get("/mark/get", {
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

export function getMarkByCottageOwnerEmail(email){
    return api
        .get("/mark/get-all-cottage", {
            params:{
                email:email
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}
export function getMarkByInstructorEmail(email){
    return api
        .get("/mark/get-all-adventure", {
            params:{
                email:email
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}
export function getMarkByShipOwnerEmail(email){
    return api
        .get("/mark/get-all-ship", {
            params:{
                email:email
            }
        })
        .then((data) => data)
        .catch((err) => {
            console.log("Nije uspesno dobavljeno");
            return err.message;
        });
}