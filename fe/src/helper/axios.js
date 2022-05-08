import axios from "axios";

const baseURL = "http://localhost:5000/api";
let headers = {};

const axiosInstance = axios.create({
    baseURL: baseURL,
    headers,
});

export default axiosInstance;