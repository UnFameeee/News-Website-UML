const mongoose = require('mongoose')
const Schema = mongoose.Schema;

const categorySchema = new Schema({
    id:{
        type: String
    },
    categoryName:{
        type: String, 
        unique: true
    },
    isActive:{
        type: Boolean
    }
}, {timestamps: true})

module.exports = mongoose.model('Article', articleSchema)