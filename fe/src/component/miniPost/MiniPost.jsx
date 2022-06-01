import "./miniPost.css"
import {Link} from "react-router-dom"

export default function MiniPost({miniPost}){

    function refreshPage() {
        setTimeout(()=>{
            window.location.reload(false);
        }, 100);
        console.log('page to reload')
    }

    return ( 
        <div className="postMini">
            {miniPost.image && (
                <img className="postMiniImg"
                 src={miniPost.image}
                 alt="" />
            )}
            <div className="postMiniInfo">
                <Link onClick={refreshPage} to={`/articles/${miniPost.id}`}  className="link"> 
                    <div className="postMiniTitle">{miniPost.title}</div>
                </Link>
            </div>
        </div>
    )
}