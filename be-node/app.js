const express = require("express")
const bodyParser = require("body-parser")
const app = express();
const mongoose = require('mongoose')
require('dotenv').config()

const cors = require("./middleware/cors")
const exception = require("./middleware/exception")

app.use(bodyParser.json())

const db = process.env.MONGO_URL;

app.use(cors)

app.use(exception)

mongoose.connect(db, {useNewUrlParser: true, useUnifiedTopology: true})
.then(() => {
    console.log("Connect to MongoDB successfully")
    app.enable('trust proxy');
    app.listen(8080);
})
.catch(err => console.log(err))