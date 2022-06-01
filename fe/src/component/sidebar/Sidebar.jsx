import { useContext, useEffect, useState } from "react"
import { Link } from "react-router-dom";
import { Context } from "../../context/Context";
import axiosInstance from "../../helper/axios";
import MiniPosts from "../miniPosts/MiniPosts";
import "./sidebar.css"

export default function Sidebar(){
    
    const {user, dispatch} = useContext(Context)
    const [cats, setCats] = useState([]);
    const [catSelected, setCatSelected] = useState("");
    const [cateInput, setCateInput] = useState("");
    const [miniPosts, setMiniPosts] = useState([]);

    useEffect(() => {
        const fetchPosts = async () => {
            let path = "";
            if(window.location.href.indexOf("/articles/") != "-1"){
                path = "/articles/category/" + window.location.href.split("/")[4];
            }else{
                path = "/articles/trending/";   
            }
            const res = await axiosInstance.get(path)
            setMiniPosts(res.data)
        }
        fetchPosts()
    }, [])

    useEffect(()=>{
        const getCats = async ()=>{
            const res = await axiosInstance.get("/category/");
            setCats(res.data);
            console.log(res.data)
        }
        getCats();
    }, [])

    const handleCateClick = (e) => {
        console.log(e.target.innerText);
        setCatSelected(e.target.innerText);
        document.getElementById("categoryInput").value = e.target.innerText;
    }

    const handleCateCreate = async (e) => {
        try{
            const data = {
                categoryName: cateInput.toUpperCase(),
                isActive: true
            } 
            const res = await axiosInstance.post("/category", data);
            console.log(res.data)
            setTimeout(window.location.reload(), 5000);
        }catch(err){
            
        }   
    }

    const handleCateUpdate = async (e) => {
        try{
            const data = {
                categoryName: catSelected.toUpperCase(),
                newCategoryName: cateInput.toUpperCase()
            }
            const res = await axiosInstance.put("/category", data);
            console.log(res.data)
            setTimeout(window.location.reload(), 5000);
        }catch(err){
            
        }   
    }
    
    const handleCateDelete = async (e) => {

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
                    : ""
                }

                {user ? user.role === "member" && (window.location.href.indexOf("/articles/")!="-1" ? <span className="sidebarTitle">Các bài báo liên quan</span> : <span className="sidebarTitle">Các bài báo nổi bật </span>)
                    : ""
                }

            </div>
            {user ? user.role === "admin" && (
                <div className="sidebarCateItem">

                    <ul className="sidebarList">
                        {cats.map((c) => (
                            <Link to={``} className="link" onClick={handleCateClick}>
                                <li className="sidebarListItem">{c.categoryName}</li>
                            </Link>
                        ))}
                    </ul>

                <input type="text" id="categoryInput" className="categoryInput" placeholder="Category..." onChange={(e) => setCateInput(e.target.value)}/>

                <button className="submitCateButton" onClick={handleCateCreate}>Tạo mới</button>
                <button className="submitCateButton" onClick={handleCateUpdate}>Chỉnh sửa</button>
                <button className="submitCateButton" onClick={handleCateDelete}>Xóa</button>
            </div>
            ) : ""}

            {user ? user.role === "member" && (
                <MiniPosts miniPosts = {miniPosts}/>
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