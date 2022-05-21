
import api from "../app/api";
import { toast } from "react-toastify";

export function getAllOwnerCategories(){
    return api
    .get("/loyalty/owner-categories")
    .then((responseData) => responseData)
    .catch((err)  => {toast.err("Something went wrong!", {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    });
                })
}

export function getAllClientCategories(){
    return api
    .get("/loyalty/client-categories")
    .then((responseData) => responseData)
    .catch((err)  => {toast.error("Something went wrong!", {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    });
                })
}


export function updateClientCategory(updateData,setLoyaltyCategories){
    api
    .post("/loyalty/update-client-category", updateData)
    .then((responseData) => {
                            updateLoyaltyClientCategories(setLoyaltyCategories);
        
                            toast.success(responseData.data, {
                            position: toast.POSITION.BOTTOM_RIGHT,
                            autoClose: 1500,
                        });
                    })
    .catch((err) => {
                    console.log("aaaaa");
                    toast.error(err.response.data, {
                    position: toast.POSITION.BOTTOM_RIGHT,
                    autoClose: 1500,
                });
            })
}

export function updateOwnerCategory(updateData, setLoyaltyCategories ){
    api
    .post("/loyalty/update-owner-category", updateData)
    .then((responseData) => {
                            updateLoyaltyOwnerCategories(setLoyaltyCategories);
                            
                            toast.success(responseData.data, {
                            position: toast.POSITION.BOTTOM_RIGHT,
                            autoClose: 1500,
                        });
                    })
    .catch((err) => {
                    console.log("aaaaa");
                    toast.error(err.response.data, {
                    position: toast.POSITION.BOTTOM_RIGHT,
                    autoClose: 1500,
                });
            })
}


function updateLoyaltyOwnerCategories(setLoyaltyCategories){
    api
    .get("/loyalty/owner-categories")
    .then((responseData) => {
        console.log(responseData.data);
        console.log(setLoyaltyCategories);
        setLoyaltyCategories(responseData.data ? responseData.data : {})
    })
    .catch((err)  => {toast.error("Something went wrong!", {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    });
                })
}

function updateLoyaltyClientCategories(setLoyaltyCategories){
    api
    .get("/loyalty/client-categories")
    .then((responseData) => setLoyaltyCategories(responseData.data ? responseData.data : {}))
    .catch((err)  => {toast.error("Something went wrong!", {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    });
                })
}