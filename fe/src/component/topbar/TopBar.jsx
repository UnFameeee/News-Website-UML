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
        window.location.replace("/")
    }

    function refreshPage() {
        setTimeout(()=>{
            window.location.reload(false);
        }, 100);
        console.log('page to reload')
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
                {(
                    (user ? ((user.role !== "creator" && user.role !== "censor")) : (user === null)) && <Link className="link" to="/"><i className="topIcon fa-solid fa-house-chimney"></i></Link>
                )}
            </div>
            <div className="topCenter">
                <ul className="topList">
                    {(
                    (user ? ((user.role !== "creator" && user.role !== "censor" && user.role !== "admin")) : (user === null)) && 
                        cats.map((c) => (
                            <Link to={`/api/articles/?cate=${c.categoryName}`} onClick={refreshPage} className="link">
                                <li className="topListItem">{c.categoryName}</li>
                            </Link>
                        ))
                    )}
                    
                </ul>
            </div>
            
            <div className="topRight">
                {user ? (
                        <div className="dropdown-menu" onMouseEnter={showDropdown} onMouseLeave={hideDropdown}>
                            <div className="topLeft">
                            <img 
                                className="topImg"
                                src={user.image ? user.image : "https://firebasestorage.googleapis.com/v0/b/uml-final.appspot.com/o/static_img%2Favatar-placeholder.png?alt=media&token=c060bc06-00e2-461f-94f6-aac6e44cc718" }
                                //"https://lh3.googleusercontent.com/d/1NCdOtipy2LSXUiNbk6Eop5sdx2WZwqvR=s512?authuser=0"
                                alt=""/> 

                                <text className="topUsernameText">{user ? user.account.username : ""}</text> 
                            </div>
                            <div>
                            {
                                stateDrop ? 
                                (<div className="dropdown-list">
                                    <Link className="dropdown-list-ele" to="/setting">
                                        <div onClick={hideDropdown}>T??i kho???n c???a t??i</div>
                                    </Link>
                                    {/* {
                                        user.role === "member" && 
                                        <Link className="dropdown-list-ele" to="/">
                                        <div onClick={hideDropdown}>B??i vi???t ???? l??u</div>
                                        </Link>
                                    } */}

                                    {
                                        user.role === "creator" && 
                                        <Link to={`/api/articles/creator/${user.id}/?status=Ch??? duy???t`} className="dropdown-list-ele" >
                                        <div onClick={hideDropdown}>B??i vi???t ch??? duy???t</div>
                                        </Link>
                                    }
                                    {
                                        user.role === "creator" && 
                                        <Link to={`/api/articles/creator/${user.id}/?status=???? duy???t`} className="dropdown-list-ele" >
                                        <div onClick={hideDropdown}>B??i vi???t ???? duy???t</div>
                                        </Link>
                                    }
                                    {
                                        user.role === "creator" && 
                                        <Link to={`/api/articles/creator/${user.id}/?status=Kh??ng ???????c duy???t`} className="dropdown-list-ele" >
                                        <div onClick={hideDropdown}>B??i vi???t kh??ng ???????c duy???t</div>
                                        </Link>
                                    }
                                    {
                                        user.role === "creator" && 
                                        <Link className="dropdown-list-ele" to="/write">
                                        <div onClick={hideDropdown}>Vi???t b??i m???i</div>
                                        </Link>
                                    }

                                    {
                                        user.role === "censor" && 
                                        <Link to={`/api/articles/creator/${user.id}/?status=Ch??? duy???t`} className="dropdown-list-ele" >
                                        <div onClick={hideDropdown}>C??c b??i vi???t ch??? duy???t</div>
                                        </Link>
                                    }
                                    {
                                        user.role === "censor" && 
                                        <Link to={`/api/articles/creator/${user.id}/?status=???? duy???t`} className="dropdown-list-ele">
                                        <div onClick={hideDropdown}>B??i vi???t ???? duy???t b???i t??i</div>
                                        </Link>
                                    }
                                    
                                    {/* {
                                        user.role === "admin" && 
                                        <Link className="dropdown-list-ele" to="/">
                                        <div onClick={hideDropdown}>Qu???n l?? t??i kho???n</div>
                                        </Link>
                                    } */}
                                    {/* {
                                        user.role === "admin" && 
                                        <Link className="dropdown-list-ele" to="/">
                                        <div onClick={hideDropdown}>Qu???n l?? b??i vi???t</div>
                                        </Link>
                                    } */}
                                    <div className="dropdown-list-ele" onClick={() =>{handleLogout(); hideDropdown()}}>????ng xu???t</div>
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