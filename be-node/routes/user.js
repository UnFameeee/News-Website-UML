const express = require('express')
const {body} = require('express-validator')

const userController = require("../controllers/UserController");

const router = express.Router();

// GET /api/user
