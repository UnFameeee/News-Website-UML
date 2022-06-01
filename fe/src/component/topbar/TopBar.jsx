import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { Context } from "../../context/Context";
import axiosInstance from "../../helper/axios";
import "./topbar.css"

export default function TopBar(){
    const {user, dispatch} = useContext(Context);
    const [cats, setCats] = useState([]);

    const handleLogout = () => {
        dispatch({type: "LOGOUT"});
        console.log(user)
        // window.location.replace("/")
    }

    useEffect(()=>{
        const getCats = async ()=>{
            const res = await axiosInstance.get("/category/");
            setCats(res.data);
        }
        getCats();
    }, [])

    const [stateDrop, setStateDrop] = useState(false);

    const showDropdown = () => {
        setStateDrop(true)
    }
    const hideDropdown = () => {
        setStateDrop(false)
    }

    return ( 
        <div className='top'>
            <div className="topLeft">
                {/* <i className="topIcon fa-brands fa-facebook-square"></i>
                <i className="topIcon fa-brands fa-twitter-square"></i>
                <i className="topIcon fa-brands fa-google-plus-square"></i> */}
                <Link className="link" to="/"><i className="topIcon fa-solid fa-house-chimney"></i></Link>
            </div>
            <div className="topCenter">
                <ul className="topList">
                    {cats.map((c) => (
                        // <Link to={`/search/articles/?cate=${c.categoryName}`} className="link">
                        <Link to={`/api/articles/?cate=${c.categoryName}`} className="link">
                            <li className="topListItem">{c.categoryName}</li>
                        </Link>
                    ))}
                    {/* <li className="topListItem">
                        <Link className="link" to="/">ABOUT</Link> 
                    </li>
                    <li className="topListItem">
                        <Link className="link" to="/">CONTACT</Link>
                    </li> */}
                    {(
                        user ? user.role === "creator" &&  <li className="topListItem">
                        <Link className="link" to="/write">WRITE</Link></li>
                    : "")}
                </ul>
            </div>
            <div className="topRight">
                {user ? (
                        <div className="dropdown-menu" onMouseEnter={showDropdown} onMouseLeave={hideDropdown}>
                            <div className="topLeft">
                            <img 
                                className="topImg"
                                src={user.image ? user.image : "https://lh3.googleusercontent.com/d/1NCdOtipy2LSXUiNbk6Eop5sdx2WZwqvR=s512?authuser=0"}
                                alt=""/> 

                                <text className="topUsernameText">{user ? user.account.username : ""}</text> 
                            </div>
                            <div>
                            {
                                stateDrop ? 
                                (<div className="dropdown-list">
                                    <Link className="dropdown-list-ele" to="/setting">
                                        <div onClick={hideDropdown}>Tài khoản của tôi</div>
                                    </Link>
                                    {
                                        user.role === "member" && 
                                        <Link className="dropdown-list-ele" to="/">
                                        <div onClick={hideDropdown}>Bài viết đã lưu</div>
                                        </Link>
                                    }
                                    {
                                        user.role === "creator" && 
                                        <Link className="dropdown-list-ele" to="/">
                                        <div onClick={hideDropdown}>Bài viết đã viết</div>
                                        </Link>
                                    }
                                    {
                                        user.role === "censor" && 
                                        <Link className="dropdown-list-ele" to="/">
                                        <div onClick={hideDropdown}>Bài viết chờ duyệt</div>
                                        </Link>
                                    }
                                    {
                                        user.role === "censor" && 
                                        <Link className="dropdown-list-ele" to="/">
                                        <div onClick={hideDropdown}>Bài viết đã duyệt</div>
                                        </Link>
                                    }
                                    
                                    {
                                        user.role === "admin" && 
                                        <Link className="dropdown-list-ele" to="/">
                                        <div onClick={hideDropdown}>Quản lý tài khoản</div>
                                        </Link>
                                    }
                                    {
                                        user.role === "admin" && 
                                        <Link className="dropdown-list-ele" to="/">
                                        <div onClick={hideDropdown}>Quản lý bài viết</div>
                                        </Link>
                                    }
                                    <div className="dropdown-list-ele" onClick={() =>{handleLogout(); hideDropdown()}}>Đăng xuất</div>
                                </div> ) : null
                            }
                            </div>
                    </div>
                ):( 
                    <ul className="topList">
                        <li className="topListItem">
                            <Link className="link" to="/login">LOGIN</Link>
                        </li>
                    </ul>
                )}
            </div>
        </div>
    )
}