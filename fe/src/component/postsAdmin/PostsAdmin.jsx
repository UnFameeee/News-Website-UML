

// import Post from "../post/Post"

import Post from "../post/Post"
import "./postsAdmin.css"
import PostAdmin from "../postAdmin/PostAdmin"


export default function PostsAdmin({posts}){
    return ( 
        <div className="postsAdmin">
            {/* {postsAdmin.map((p) => (
                <PostAdmin postAdmin = {p} />
            ))} */}
            {/* {console.log(posts)} */}
            {posts.map((p) => (
                <PostAdmin postAdmin = {p} />
            ))}
        </div>
    )
}