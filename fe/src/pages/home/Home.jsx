import { useEffect, useState } from "react"

import Header from "../../component/header/Header.jsx"
import Posts from "../../component/posts/Posts.jsx"
import Sidebar from "../../component/sidebar/Sidebar.jsx"
import "./home.css"
import { useLocation } from "react-router-dom"
import axiosInstance from "../../helper/axios.js"
import { Context } from "../../context/Context.js"
import { useContext, useState } from "react"

export default function Home(){
    const {user} = useContext(Context)
    const [posts, setPosts] = useState([]);
    const {search} = useLocation();

    useEffect(() => {
        const fetchPosts = async () => {
            const res = await axiosInstance.get("/articles/" + search)
            setPosts(res.data)
        }
        fetchPosts()
    }, [search])

    if(user.role === "member"){
        return ( 
            <>
                <Header/>
                <div className="home">
    
                    <Posts posts = {posts} />
                    <Sidebar/>
                    
                </div>
            </>
        )
    }else if(user.role === "creator"){
        return ( 
            <>
                <Header/>
                <div className="home">
    
                    <Posts posts = {posts} />
                    <Sidebar/>
                    
                </div>
            </>
        )
    }else if(user.role === "censor"){
        return ( 
            <>
                <Header/>
                <div className="home">
    
                    <Posts posts = {posts} />
                    <Sidebar/>
                    
                </div>
            </>
        )
    }else if(user.role === "admin"){
        return ( 
            <>
                <Header/>
                <div className="home">
    
                    <Posts posts = {posts} />
                    <Sidebar/>
                    
                </div>
            </>
        )
    }
    
}