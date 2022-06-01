import "./post.css"
// import Single from "../../pages/single/Single";
import {useEffect, useState } from "react";
import {Link} from "react-router-dom"
import axiosInstance from "../../helper/axios";


export default function Post({post}){
    const [cats, setCats] = useState([]);

    useEffect(()=>{
        const getCats = async ()=>{
            const res = await axiosInstance.get("/category/");
            setCats(res.data);
        }
        getCats();
    }, [])

    const MappingCateName = (c) => {
        for(let i = 0; i < cats.length; ++i){
            if(cats[i].id === c)
                return cats[i].categoryName
        return ""
        }
    }
    

    return ( 
        <div className="post">
            {post.image && (
                <img className="postImg"
                 src={post.image}
                 alt="" />
            )}
            {/* {console.log({post})} */}
            <div className="postInfo">
                <Link to={`/articles/${post.id}`} className="link"> 
                    <div className="postTitle">{post.title}</div>
                </Link>
                <div className="postSubInfo">
                    <div className="postCats">
                        {<span className="postCat">{MappingCateName(post.categoryId)}</span>}
                        {/* <span className="postCat">Genshin</span> */}
                        {/* {cats.map((c)=>
                            
                        )} */}
                    </div>
                    <div className="postDate">{post.publishedDate}</div>
                </div>
                <p className="postDesc">{post.content}</p>
            </div>
        </div>
    )
}