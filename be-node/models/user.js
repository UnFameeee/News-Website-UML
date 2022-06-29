const mongoose = require('mongoose')
const Schema = mongoose.Schema;
const Account = require("./account")

const userSchema= new Schema({
    id:{
        type: String
    },
    account:{
        type: mongoose.Schema.Types.ObjectId,
        ref: "Account"
    },
    email:{
        type: String,
        unique: true
    },
    phone:{
        type: String
    },
    fullname:{
        type: String
    },
    image:{
        type: String
    },
    description:{
        type: String
    },
    role:{
        type: String
    },
    isActive:{
        type: Boolean,
    },
    role:{
        type: Array
    },
}, {timestamps: true})

module.exports = mongoose.model('User', userSchema)