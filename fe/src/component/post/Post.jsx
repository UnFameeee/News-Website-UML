import "./post.css"
// import Single from "../../pages/single/Single";
import {Link} from "react-router-dom"

export default function Post({post}){
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
                        <span className="postCat">Genshin</span>
                        {/* {post.categories.map((c)=>
                            <span className="postCat">{c.name}</span>
                        )} */}
                    </div>
                    <div className="postDate">{post.publishedDate}</div>
                </div>
                <p className="postDesc">{post.content}</p>
            </div>
        </div>
    )
}