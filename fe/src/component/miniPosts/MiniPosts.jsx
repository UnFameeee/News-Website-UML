
import MiniPost from "../miniPost/MiniPost"
import "./miniPosts.css"

export default function MiniPosts({miniPosts}){
    return ( 
        <div className="posts">
            {miniPosts.map((p) => (
                <MiniPost miniPost = {p} />
            ))}
        </div>
    )
}