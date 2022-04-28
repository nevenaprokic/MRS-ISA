import axios from "axios";
import api from "../app/api";

export function getAdventureByInstructorEmail(username){
    return api
        .get("/adventure/instructor-adventures", {
            params:{
                email:username
            }
        })
        .then((response) => response)
        .catch((err) => {
            alert(err.data)
        });
}

export function getAdventureById(id){
    return api
        .get("/adventure/details", {
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

export function updateAdventure(adventureData, additionalServices){
    api
    .post("/adventure/update-adventure", adventureData)
    .then((responseData) => {
        updateAdditionalServices(adventureData.id, additionalServices);
    })
    .catch((err) => {
        console.log(err);
        alert(err.data)});
}

function updateAdditionalServices(offerId, additionalServiceDTOS){
    console.log(additionalServiceDTOS);
    api
    .post("/adventure/update-adventure-services",  {
        params:{
            offerId : offerId,
            additionalServiceDTOS : additionalServiceDTOS
        }
    })
    .then((responseData) => alert(responseData.data))
    .catch((errMessage) => alert(errMessage.data));
}

// export function test(id){
//     api
//     .get("/adventure/getTest", {
//         params:{
//             id:id
//         }
//     })
//     .then((responseData) => {
//         //updateAdditionalServices(adventureData.get("adventureId"), additionalServices);
//         console.log(responseData.data);
//         let byte = responseData.data.photos;
//         var blob = new Blob([byte], {type: "image/jpg"});
//         let fd = new FormData();
//         fd.append("fd", blob, "danas");
//         console.log(fd.get("fd"));
//         let src = "data:image/png;base64," + byte;
//         return(
//             <img src={src}></img>
//         );
//     })
//     .catch((err) => {
//         alert(err.data)});
// }



