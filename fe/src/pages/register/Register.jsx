import { useState } from "react"
import { Link } from "react-router-dom"
import axiosInstance from "../../helper/axios.js"
import "./register.css"

export default function Register(){
    const [username, setUsername] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [error, setError] = useState(false)

    const handleSubmit = async (e) =>{
        e.preventDefault();
        setError(false);
        
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
                "role": "member",
                "description": "",
                "favoriteArticle": []
            };

            const res = await axiosInstance.post("/auth/register", data)
            res.data && window.location.replace("/login")
        }catch(err){
            setError(true);
        }
    }

    return ( 
        <div className='register'>
            {/* <img className="wallpaper" src={logo} /> */}
            <div className="form">
                <form className="registerForm" onSubmit={handleSubmit}>
                    <div className="registerTitle">Register</div>
                    <label>Username</label>
                    <input type="text" className="registerInput" placeholder="Username"
                    onChange={e => setUsername(e.target.value)}/>
                    <label>Email</label>
                    <input type="text" className="registerInput" placeholder="Email"
                    onChange={e => setEmail(e.target.value)}/>
                    <label>Password</label>
                    <input type="password" className="registerInput" placeholder="password"
                    onChange={e => setPassword(e.target.value)}/>
                    <button className="registerButton">Register</button>
                </form>
            </div>
            {error && <span style={{color:"red", textAlign:"center", marginTop:"10px"}}>Something went wrong!!!</span>}
        </div>
    )
}