import jwtDecode from "jwt-decode";


export function getUsernameFromToken(){
    try{
        let token = jwtDecode(localStorage.getItem("user"));
        let username = token.sub;
        return username;
    }
    catch{
        console.log("Greska");
        return null;
    }
}