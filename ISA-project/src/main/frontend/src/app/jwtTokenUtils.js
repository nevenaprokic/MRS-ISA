export function getUsernameFromToken(){
    try{
        let token = JSON.parse(localStorage.getItem("user")).token;
        let parsedToken = JSON.parse(token.split("."[1]));
        console.log(parsedToken);
        let username = parsedToken.username;
        return username;
    }
    catch{
        console.log("Greska");
        return null;
    }
}