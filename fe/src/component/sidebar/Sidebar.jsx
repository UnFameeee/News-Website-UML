import { useContext, useEffect, useRef, useState } from "react"
import { Link } from "react-router-dom";
import { Context } from "../../context/Context";
import "./sidebar.css"

export default function Sidebar(){
    const {user, dispatch} = useContext(Context)
    const cateInputRef = useRef()
    // const [cats, setCats] = useState([]);
    const [catSelected, setCatSelected] = useState("");

    // useEffect(()=>{
    //     const getCats = async ()=>{
    //         const res = await axios.get("/categories");
    //         setCats(res.data);
    //     }
    //     getCats();
    // }, [])

    const handleCateClick = (e) => {
        console.log(e.target.innerText);

        setCatSelected(e.target.innerText);
        cateInputRef.value = e.target.innerText;

        document.getElementById("categoryInput").value = e.target.innerText;
    }

    const handleCateCreate = (e) => {
        console.log(catSelected)
    }

    const handleCateUpdate = (e) => {
        console.log(cateInputRef.value)
    }
    
    const handleCateDelete = (e) => {

    }

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
                {user ? user.role === "admin" && <span className="sidebarTitle">Danh sách Category</span>
                    : <span className="sidebarTitle">Các bài báo liên quan</span>
                }
                
            </div>
            {user ? user.role === "admin" && (
                <div className="sidebarCateItem">
                    {/* <ul className="sidebarList">
                        {cats.map((c) => (
                            <Link to={`/?cat=${c.name}`} className="link">
                                <li className="sidebarListItem">{c.name}</li>
                            </Link>
                        ))}
                    </ul> */}

                    <ul className="sidebarList">
                        <li className="sidebarListItem" value="Genshin" onClick={handleCateClick}>Genshin</li>
                        <li className="sidebarListItem" value="Lmao" onClick={handleCateClick}>Lmao</li>
                        <li className="sidebarListItem" value="Cringe" onClick={handleCateClick}>Cringe</li>
                    </ul>
                <input type="text" id="categoryInput" className="categoryInput" placeholder="Category..." ref={cateInputRef} />

                <button className="submitCateButton" onClick={handleCateCreate}>Tạo mới</button>
                <button className="submitCateButton" onClick={handleCateUpdate}>Chỉnh sửa</button>
                <button className="submitCateButton" onClick={handleCateDelete}>Xóa</button>
            </div>
            ) : ""}
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