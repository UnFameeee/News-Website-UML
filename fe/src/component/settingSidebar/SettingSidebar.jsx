import axios from "axios";
import { useContext, useEffect, useState } from "react"
import { Link } from "react-router-dom";
import { Context } from "../../context/Context"
import "./settingSidebar.css"

export default function Sidebar(){
    const {user} = useContext(Context)
    // const [cats, setCats] = useState([]);

    // useEffect(()=>{
    //     const getCats = async ()=>{
    //         const res = await axios.get("/categories");
    //         setCats(res.data);
    //     }
    //     getCats();
    // }, [])

    return ( 
        <div className="sidebar">
            <div className="sidebarItem">
                <span className="sidebarTitle">Ảnh đại diện</span>
                <img
                src= {user.image ? user.image : "https://firebasestorage.googleapis.com/v0/b/uml-final.appspot.com/o/static_img%2Favatar-placeholder.png?alt=media&token=cce1eeaa-6b3a-407b-92ec-ff505016f167"}
                alt=""
                />
            </div>
            <div className="sidebarItem">
                <span className="sidebarTitle">Tiểu sử của tôi</span>
                <p> 
                {
                    user.description ? user.description : "Lorem ipsum dolor sit amet consectetur adipisicing elit. Lorem ipsum dolor sit amet consectetur adipisicing elit. Lorem ipsum dolor sit amet consectetur adipisicing elit. Lorem ipsum dolor sit amet consectetur adipisicing elit."
                }
                </p>
            </div>
            <div className="sidebarItem">
                <span className="sidebarTitle">Liên kết tài khoản</span>
                <ul className="sidebarSocial">
                    <i className="sidebarIcon fa-brands fa-facebook-square"></i>
                    <i className="sidebarIcon fa-brands fa-twitter-square"></i>
                    <i className="sidebarIcon fa-brands fa-google-plus-square"></i>
                </ul>
            </div>
        </div>
    )
}