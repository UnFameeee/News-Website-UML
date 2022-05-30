import axios from "axios";

const baseURL = "http://localhost:8080/api";
const headers = {"Access-Control-Allow-Origin": "*"};

const axiosInstance = axios.create({
    baseURL: baseURL,
    headers,
});

export default axiosInstance;