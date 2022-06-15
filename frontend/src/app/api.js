import axios from "axios";

//"https://isa-spring-back.herokuapp.com"
const api = axios.create({
  baseURL: "http://localhost:8082",
  headers: {
    "Content-Type": "application/json",
  },
});

api.interceptors.request.use(
  (config) => {
    let token = null;
    try {
      token = localStorage.getItem("user");
    } catch (e) {}
    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    const status = error.response?.status || 500;
    switch (status) {
      case 401: {
        window.location = "/autentification-failed";
      }
      case 403: {
        window.location = "/authorization-failed";
      }
      default: {
        Promise.reject(error);
      }
    }
  }
);

export default api;
