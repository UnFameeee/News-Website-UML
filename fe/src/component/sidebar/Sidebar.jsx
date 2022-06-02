import { useContext, useEffect, useState } from "react"
import { Link } from "react-router-dom";
import { Context } from "../../context/Context";
import axiosInstance from "../../helper/axios";
import MiniPosts from "../miniPosts/MiniPosts";
import "./sidebar.css"

export default function Sidebar(){
    
    const {user} = useContext(Context)
    const [cats, setCats] = useState([]);
    const [catSelected, setCatSelected] = useState("");
    const [cateInput, setCateInput] = useState("");
    const [searchInput, setSearchInput] = useState("");
    const [miniPosts, setMiniPosts] = useState([]);

    const [username, setUsername] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [role, setRole] = useState("")


    useEffect(() => {
        const fetchPosts = async () => {
            let path = "/articles/trending/";
            // console.log(window.location.href.indexOf("/articles/") != "-1")
            console.log(window.location.href.split("/"));
            if(window.location.href.indexOf("/articles/") != "-1"){
                if(window.location.href.split("/").length == 5)
                // if(window.location.href.split("/")[5].indexOf("?") == -1)
                    path = "/articles/category/" + window.location.href.split("/")[4];
            }else{
                path = "/articles/trending/";   
            }
            const res = await axiosInstance.get(path)
            setMiniPosts(res.data)
            setRole("censor")
        }
        fetchPosts()
    }, [])
    

    useEffect(()=>{
        const getCats = async ()=>{
            const res = await axiosInstance.get("/category/");
            setCats(res.data);
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
            setTimeout(window.location.reload(), 5000);
        }catch(err){
            
        }   
    }

    function refreshPage() {
        setTimeout(()=>{
            window.location.reload(false);
        }, 100);
        console.log('page to reload')
    }

    const handleSearch = async (e) => {
        try{
            window.location.replace(`/api/articles/?search=${searchInput}`)
        }catch(err){
            
        }   
    }
    
    const handleCateDelete = async (e) => {

    }

    const handleSubmit = async (e) =>{
        e.preventDefault();
        try{
            const data = {
                "account": {
                    username,
                    password
                },
                email, 
                "phone": "",
                "fullname": "", 
                "image": "", 
                "role": role,
                "description": "",
                "favoriteArticle": []
            };

            const res = await axiosInstance.post("/auth/register", data)
            res.data && window.location.replace("/login")
        }catch(err){
        }
    }

    return ( 
        <div className="sidebar">
            <div className="sidebarItem">
                {/* <span className="sidebarTitle">
                    <label>Tìm kiếm</label>
                </span> */}
                <div className="sidebarSearchItem">
                    <input type="text" className="searchInput" onChange={e => setSearchInput(e.target.value)} placeholder="Tìm kiếm tin tức..." />
                    <button className="searchButton" onClick={handleSearch}><i className="topSearchIcon fas fa-magnifying-glass"></i>Tìm kiếm</button>
                </div>
            </div>
            <div className="sidebarItem">
                {user ? user.role === "admin" && <span className="sidebarTitle">Danh sách Category</span>
                    : ""
                }

                {(user ? ((user.role !== "creator" && user.role !== "censor" && user.role !== "admin")) : (user == null)) && (window.location.href.indexOf("/articles/")!="-1" ? <span className="sidebarTitle">Các bài báo liên quan</span> : <span className="sidebarTitle">Các bài báo nổi bật </span>)
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
                <div className="holderSidebarCateItemInput">
                <input type="text" id="categoryInput" className="categoryInput" placeholder="Category..." onChange={(e) => setCateInput(e.target.value)}/>
                <div className="sidebarCateItemButton">
                    <button className="submitCateButton" onClick={handleCateCreate}>Tạo mới</button>
                    <button className="submitCateButton" onClick={handleCateUpdate}>Chỉnh sửa</button>
                    <button className="submitCateButton" onClick={handleCateDelete}>Xóa</button>
                </div>
                
                </div>
            </div>
            ) : ""}

            <div className="sidebarItem">
                {user ? user.role === "admin" && <span className="sidebarTitle">Tạo tài khoản quản trị</span> : ""}
            </div>
            {user ? user.role === "admin" && (
                <div className="sidebarCateItem">

                    <div className="form">
                    <form className="registerAdminForm" onSubmit={handleSubmit}>
                        <label>Username</label>
                        <input type="text" className="registerAdminInput" placeholder="Username"
                        onChange={e => setUsername(e.target.value)}/>
                        <label>Email</label>
                        <input type="text" className="registerAdminInput" placeholder="Email"
                        onChange={e => setEmail(e.target.value)}/>
                        <label>Password</label>
                        <input type="password" className="registerAdminInput" placeholder="password"
                        onChange={e => setPassword(e.target.value)}/>
                        <label for="cate">Choose a Role:</label>
                        <select className="registerAdminRole" onChange={(e) => setRole(e.target.value)} >
                            <option selected="selected" value="censor">Kiểm duyệt viên</option>
                            <option value="creator">Người tạo nội dung</option>
                        </select>
                        <button className="registerAdminButton">Register</button>
                    </form>
                </div>
            </div>
            ) : ""}

            {user ? user.role === "member" && (
                <MiniPosts miniPosts = {miniPosts}/>
            ) : ""}

            {user===null && (
                <MiniPosts miniPosts = {miniPosts}/>
            )}

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