const express = require("express")
const bodyParser = require("body-parser")
const app = express();

const cors = require("./middleware/cors")
const exception = require("./middleware/exception")

app.use(bodyParser.json())

app.use(cors)

app.use(exception)

app.listen("8080", () => {
    console.log("Backend is working");
})