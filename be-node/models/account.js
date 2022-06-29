const mongoose = require('mongoose')
const Schema = mongoose.Schema

const accountSchema = new Schema({
    username:{
        type: String,
        unique: true
    },
    password:{
        type: String
    }
})

module.exports = mongoose.model('Account', accountSchema)