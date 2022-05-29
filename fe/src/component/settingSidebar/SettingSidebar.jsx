import axios from "axios";
import { useContext, useEffect, useState } from "react"
import { Link } from "react-router-dom";
import { Context } from "../../context/Context"
import "./settingSidebar.css"

export default function SidebarSetting(){
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
        <div className="sidebarSetting">
            <div className="sidebarSettingItem">
                <span className="sidebarSettingTitle">Ảnh đại diện</span>
                <img
                src= {user.image ? user.image : "https://lh3.googleusercontent.com/d/1NCdOtipy2LSXUiNbk6Eop5sdx2WZwqvR=s512?authuser=0"}
                alt=""
                />
            </div>
            <div className="sidebarSettingItem">
                <span className="sidebarSettingTitle">Tiểu sử của tôi</span>
                <p> 
                {
                    user.description ? user.description : "Lorem ipsum dolor sit amet consectetur adipisicing elit. Lorem ipsum dolor sit amet consectetur adipisicing elit. Lorem ipsum dolor sit amet consectetur adipisicing elit. Lorem ipsum dolor sit amet consectetur adipisicing elit."
                }
                </p>
            </div>
            <div className="sidebarSettingItem">
                <span className="sidebarSettingTitle">Liên kết tài khoản</span>
                <ul className="sidebarSettingSocial">
                    <i className="sidebarSettingIcon fa-brands fa-facebook-square"></i>
                    <i className="sidebarSettingIcon fa-brands fa-twitter-square"></i>
                    <i className="sidebarSettingIcon fa-brands fa-google-plus-square"></i>
                </ul>
            </div>
        </div>
    )
}