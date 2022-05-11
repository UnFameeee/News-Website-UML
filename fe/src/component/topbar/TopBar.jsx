import { useContext } from "react";
import { Link } from "react-router-dom";
import { Context } from "../../context/Context";
import "./topbar.css"

export default function TopBar(){
    const {user, dispatch} = useContext(Context);
    const PF = "http://localhost:5000/images/"
    const handleLogout = () => {
        dispatch({type: "LOGOUT"});
    }


    return ( 
        <div className='top'>
            <div className="topLeft">
                <i className="topIcon fa-brands fa-facebook-square"></i>
                <i className="topIcon fa-brands fa-twitter-square"></i>
                <i className="topIcon fa-brands fa-google-plus-square"></i>
            </div>
            <div className="topCenter">
                <ul className="topList">
                    <li className="topListItem">
                        <Link className="link" to="/posts">HOME</Link>
                    </li>
                    <li className="topListItem">
                        <Link className="link" to="/">ABOUT</Link> 
                    </li>
                    <li className="topListItem">
                        <Link className="link" to="/">CONTACT</Link>
                    </li>
                    <li className="topListItem">
                        <Link className="link" to="/write">WRITE</Link>
                    </li>
                    <li className="topListItem" onClick={handleLogout}>
                        {user && "LOGOUT"}
                    </li>
                </ul>
            </div>
            <div className="topRight">
                {user ? (
                    <Link to="/setting">
                        <img 
                            className="topImg"
                            src={PF+user.profilePic}
                            alt="" />
                    </Link>
                ):( 
                    <ul className="topList">
                        <li className="topListItem">
                            <Link className="link" to="/login">LOGIN</Link>
                        </li>
                    </ul>
                )}

                <i className="topSearchIcon fas fa-magnifying-glass"></i>
            </div>
        </div>
    )
}

// <Link className="link" to="/login">LOGOUT</Link>