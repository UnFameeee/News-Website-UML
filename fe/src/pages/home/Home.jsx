import { useEffect, useState } from "react"

import Header from "../../component/header/Header.jsx"
import Posts from "../../component/posts/Posts.jsx"
import Sidebar from "../../component/sidebar/Sidebar.jsx"
import "./home.css"
import { useLocation } from "react-router-dom"
import axiosInstance from "../../helper/axios.js"

export default function Home(){
    const [posts, setPosts] = useState([]);
    const {search} = useLocation();

    useEffect(() => {
        const fetchPosts = async () => {
            const res = await axiosInstance.get("/articles/" + search)
            setPosts(res.data)
        }
        fetchPosts()
    }, [search])

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