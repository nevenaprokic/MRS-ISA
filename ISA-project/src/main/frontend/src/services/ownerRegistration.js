import axios from "axios";
import api from "../app/api";

export default function sendOwnerRegistration(data){ //treba dodati da mu se povratna poruka ispise
    return api
       .post("/registrationOwner", data)
       .then(() => console.log("Uspesno ste poslali zahtev za registraciju."))
    .catch((err) => {
        console.log("Niste uspesno poslali zahtev za registraciju.");
        return err.message;
    });
}


