import api from "../app/api";
import { getUsernameFromToken } from "../app/jwtTokenUtils";
import { toast } from "react-toastify";

export function changeAdminData(newAdminData){
    let email = getUsernameFromToken();
    newAdminData["email"] = email;
    api
    .post("/admin/change-data", newAdminData)
    .then((responseData) => {toast.success(responseData.data, {
                                position: toast.POSITION.BOTTOM_RIGHT,
                                autoClose: 1500,
                            });
})
    .catch((err) => toast.error(err.response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    }));
}

export function getAdminByEmail(){
    let email = getUsernameFromToken();
    return api
    .get("/admin/profile-info", 
        {
            params:{
            email: email
        }
    })
    .then((responseData) => responseData)
    .catch((err) => toast.error("Admin not found." ,{
                    position: toast.POSITION.BOTTOM_RIGHT,
                    autoClose: 1500,
                }));

}

export function addNewAdmin(adminData){
    api
    .post("/admin/add-admin", adminData)
    .then((response) => toast.success(response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    }))
    .catch((err) => toast.error(err.response.data, {
                        position: toast.POSITION.BOTTOM_RIGHT,
                        autoClose: 1500,
                    }))
}

export function firstLoginChangePassword(adminData, close){
  console.log(adminData);
    api
    .post("/admin/change-password/" + getUsernameFromToken(), adminData)
    .then(res =>{
        let data = {
          email: getUsernameFromToken(),
          password: adminData["newPassword1"]
        }
        login(data);
      close();
      toast.success(res.data, {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 2000,
      });
    })
    .catch((err) => {
      toast.error(err.response.data, {
        position: toast.POSITION.BOTTOM_RIGHT,
        autoClose: 1500,
      });
    });
}

function login(data){
    api
      .post("auth/login", data)
      .then((res) => {
        const token = res.data.accessToken;
        // dekodiranje tokena, da dobijes podatke
        localStorage.setItem("user", token);
        window.location = "/user-home-page/admin"
      })
      .catch((err) => {
        toast.error("Username or password is not correct.", {
          position: toast.POSITION.BOTTOM_RIGHT,
          autoClose: 1500,
        });
      });
  }

  export function getAllComplaints(){
    return api
    .get("/admin/all-complaints")
    .then((response) => console.log(response.data))
    .catch((err) => console.log(err));
  }
