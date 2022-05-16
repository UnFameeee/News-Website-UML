import { useEffect, useState } from "react"
import { Link } from "react-router-dom";
import "./sidebar.css"

export default function Sidebar(){

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
                {/* <span className="sidebarTitle">
                    <label>Tìm kiếm</label>
                </span> */}
                <div className="sidebarSearchItem">
                    <input type="text" className="searchInput" placeholder="Tìm kiếm tin tức..." />
                    <button className="searchButton"><i className="topSearchIcon fas fa-magnifying-glass"></i>Tìm kiếm</button>
                </div>
            </div>
            <div className="sidebarItem">
                <span className="sidebarTitle">Các bài báo liên quan</span>
                <ul className="sidebarList">
                    {/* {cats.map((c) => (
                        <Link to={`/?cat=${c.name}`} className="link">
                            <li className="sidebarListItem">{c.name}</li>
                        </Link>
                    ))} */}
                </ul>
            </div>

            {/* <div className="sidebarItem">
                <span className="sidebarTitle">FOLLOW US</span>
                <ul className="sidebarSocial">
                    <i className="sidebarIcon fa-brands fa-facebook-square"></i>
                    <i className="sidebarIcon fa-brands fa-twitter-square"></i>
                    <i className="sidebarIcon fa-brands fa-google-plus-square"></i>
                </ul>
            </div> */}
        </div>
    )
}