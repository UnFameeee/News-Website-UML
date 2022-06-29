const mongoose = require('mongoose')
const Schema = mongoose.Schema;

const articleSchema = new Schema({
    id:{
        type: String,
        unique: true
    },
    userId:{
        type: String
    },
    image:{
        type: String
    },
    title:{
        type: String
    },
    content:{
        type: String
    },
    status:{
        type: String
    },
    categoryId:{
        type: String
    },
    status:{
        type: String
    },
}, {timestamps: true})

module.exports = mongoose.model('Article', articleSchema)