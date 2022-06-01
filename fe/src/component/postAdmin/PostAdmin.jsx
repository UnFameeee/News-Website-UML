import "./postAdmin.css"
// import Single from "../../pages/single/Single";
import {Link} from "react-router-dom"
import axiosInstance from "../../helper/axios";
import {useEffect, useState } from "react";

export default function PostAdmin({postAdmin}){

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
                {postAdmin.status === "Chờ duyệt" && <div className="postAdminStatusWaitInfo">{postAdmin.status}</div>}

                {postAdmin.status === "Đã duyệt" && <div className="postAdminStatusRejectInfo">{postAdmin.status}</div>}

                {postAdmin.status === "Không được duyệt" && <div className="postAdminStatusInfo">{postAdmin.status}</div>}

                <div className="postAdminSubInfo">
                    <div className="postAdminCats">
                        {<span className="postAdminCat">{MappingCateName(postAdmin.categoryId)}</span>}
                    </div>
                    <div className="postAdminDate">{postAdmin.publishedDate}</div>
                </div>
                <p className="postAdminDesc">{postAdmin.content}</p>
            </div>
        </div>
    )
}