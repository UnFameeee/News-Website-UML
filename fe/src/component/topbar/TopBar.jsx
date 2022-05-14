import { useContext } from "react";
import { Link } from "react-router-dom";
import { Context } from "../../context/Context";
import "./topbar.css"

export default function TopBar(){
    const {user, dispatch} = useContext(Context);
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
                            src={user.image ? user.image : "https://firebasestorage.googleapis.com/v0/b/uml-final.appspot.com/o/static_img%2Favatar-placeholder.png?alt=media&token=cce1eeaa-6b3a-407b-92ec-ff505016f167"}
                            alt="" />
                    </Link>
                ):( 
                    <ul className="topList">
                        <li className="topListItem">
                            <Link className="link" to="/login">LOGIN</Link>
                        </li>
                    </ul>
                )}

                <text className="topUsernameText">{user.account.username}</text>
            </div>
        </div>
    )
}