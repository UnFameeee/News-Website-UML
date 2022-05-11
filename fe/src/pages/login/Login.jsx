import "./login.css"
import { Link } from "react-router-dom"
import { useContext, useRef } from "react";
import { Context } from "../../context/Context";
import axiosInstance from "../../helper/axios.js";

export default function Login(){
    const emailRef = useRef()
    const passwordRef = useRef()
    const {user, dispatch, isFetching} = useContext(Context)

    const handleSubmit = async (e) => {
        e.preventDefault();
        dispatch({type: "LOGIN_START"});
        try{
            const data = {
                email: emailRef.current.value,
                account: {
                    password: passwordRef.current.value
                }
            } 
            const res = await axiosInstance.post("/auth/login", data);
            dispatch({type: "LOGIN_SUCCESS", payload: res.data})
        }catch(err){
            dispatch({type: "LOGIN_FAILURE"})
        }   
    }

    return ( 
        <div className='login'>
            <div className="form">
                <form className="loginForm" onSubmit={handleSubmit}>
                    <div className="loginTitle">Login</div>
                    <label>Email</label>
                    <input type="text" className="loginInput" placeholder="Email" ref={emailRef}/>
                    <label>Password</label>
                    <input type="password" className="loginInput" placeholder="password" ref={passwordRef}/>
                    <button className="loginButton" type="submit" disabled={isFetching}>Login</button>
                </form>
                <button className="loginRegisterButton">
                    <Link className="link" to="/register">REGISTER</Link>
                </button>
            </div>
        </div>
    )
}