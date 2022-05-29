import "./postAdmin.css"
// import Single from "../../pages/single/Single";
import {Link} from "react-router-dom"

export default function PostAdmin({postAdmin}){
    return ( 
        <div className="postAdmin">
            {console.log(postAdmin)}
            {postAdmin.image && (
                <img className="postAdminImg"
                 src={postAdmin.image}
                 alt="" />
            )}
            <div className="postAdminInfo">
                <Link to={`/articles/${postAdmin.id}`} className="link"> 
                    <div className="postAdminTitle">{postAdmin.title}</div>
                </Link>
                <div className="postAdminSubInfo">
                    <div className="postAdminCats">
                        <span className="postAdminCat">Genshin</span>
                        {/* {post.categories.map((c)=>
                            <span className="postCat">{c.name}</span>
                        )} */}
                    </div>
                    <div className="postAdminDate">{postAdmin.publishedDate}</div>
                </div>
                <p className="postAdminDesc">{postAdmin.content}</p>
            </div>
        </div>
    )
}