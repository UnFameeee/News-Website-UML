import { useContext, useEffect, useState } from "react";
import { useLocation, Link } from "react-router-dom"
import { Context } from "../../context/Context";
import axiosInstance from "../../helper/axios";
import "./singlePost.css"

export default function SinglePost(){
    const location = useLocation();
    const path = location.pathname.split("/")[2];
    const [post, setPost] = useState({})

    const {user} = useContext(Context)
    const [author, setAuthor] = useState("")
    const [title, setTitle] = useState("")
    const [content, setContent] = useState("")
    const [updateMode, setUpdateMode] = useState(false)

    useEffect(()=>{
        const getPost = async ()=>{
            const res = await axiosInstance.get("/articles/" + path);
            setPost(res.data);
            setTitle(res.data.title);
            setContent(res.data.content);
            setAuthor(await (await axiosInstance.get("/user/" + res.data.userId)).data.account.username)
        };
        getPost()
    }, [path])

    const handleDelete = async() =>{
        try{
            await axiosInstance.delete(`/posts/${post._id}`, 
                {data: {username: author}});
            window.location.replace("/")
        }catch(err){
            console.log(err)
        }
    } 

    const handleUpdate = async (e) => {
        e.preventDefault();
        try{
            await axiosInstance.put(
                `/posts/${post._id}`,
                {username: user.username, title: title, content: content}
            );
            window.location.reload();
        }catch(err){
            console.log(err)
        }
    }
    
    return ( 
        <div className='singlePost'>
            <div className="singlePostWrapper">
            {post.image && (
                <img
                    className="singlePostImg"
                    src= {post.image}
                    alt=""
                    />
            )}
            
            {updateMode ? ( 
            <input type="text" value={title} className="singlePostTitleInput" autoFocus onChange={(e)=>setTitle(e.target.value)}/>
            ) : (
                <h1 className="singlePostTitle"> 
                    {post.title}  
                    {user ? ((author === user.account.username) && (
                    <div className="singlePostEdit">
                        <i className="singlePostIcon fa-solid fa-pen-to-square" onClick={()=>setUpdateMode(true)}></i>
                        <i className="singlePostIcon fa-solid fa-trash-can" onClick={handleDelete}></i>
                    </div>
                    )) : ""}
                </h1>
            )}
                <div className="singlePostInfo">
                    <span className="singlePostAuthor">
                        Author:
                        <Link to={`/?user=${author}`} className="link">
                            <b> {author}</b>
                        </Link>
                    </span>
                    <span className="singlePostDate">
                        {post.publishedDate}
                    </span>
                </div>
                {updateMode ? (
                <textarea className="singlePostContentInput" value={content} onChange={(e)=>setContent(e.target.value)}/>
                ) : (
                    <p className="singlePostContent">{post.content}</p>  
                )} 
                {updateMode && 
                    <button className="singlePostButton" onClick={handleUpdate}>Update</button>
                }
            </div>
        </div>
    )
}