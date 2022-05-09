import axios from "axios";

const baseURL = "http://localhost:8080/api";
let headers = {};

const axiosInstance = axios.create({
    baseURL: baseURL,
    headers,
});

export default axiosInstance;