import "./settings.css"
import Sidebar from "../../component/sidebar/Sidebar"
import { useContext, useState } from "react"
import { Context } from "../../context/Context"
import axiosInstance from "../../helper/axios.js"

export default function Settings(){
    const {user, dispatch} = useContext(Context)
    const [file, setFile] = useState(null)

    const [username, setUsername] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [phone, setPhone] = useState("")
    const [fullname, setFullname] = useState("")


    const [success, setSuccess] = useState(false)
    const PF = "http://localhost:5000/images/"

    const handleSubmit = async (e) =>{
        e.preventDefault();
        dispatch({type:"UPDATE_START"})
        const updatedUser = {
            id: user.id,
            account: {
                username,
                password
            }, 
            email,
            phone,
            fullname,
            image: "",
            role: ""
        };
        console.log(updatedUser);
        // if(file){
        //     const data = new FormData();
        //     const filename = Date.now() + file.name;
        //     data.append("name", filename);
        //     data.append("file", file);
        //     updatedUser.profilePic = filename;
        //     try{
        //         await axiosInstance.post("/upload", data);
        //         setSuccess(true);
        //     }catch(err){}
        // }
        try{
            const res = await axiosInstance.put("/user", updatedUser);
            setSuccess(true);
            dispatch({type:"UPDATE_SUCCESS", payload: res.data})
            setTimeout(window.location.reload(), 4000);
            // 
        }catch(err){ 
            dispatch({type:"UPDATE_FAILURE"})
        }
    }

    return (
        <div className='settings'>
           <div className="settingsWrapper">
                <div className="settingsTitle">
                    <span className="settingsUpdateTitle">Update Your Account</span>
                </div>
                <form className="settingsForm" onSubmit={handleSubmit}>
                    <label>Profile Picture</label>
                    <div className="settingsPP">
                        <img
                            src={file ? URL.createObjectURL(file) : PF+user.profilePic}
                            alt=""
                        />
                        <label htmlFor="fileInput">
                            <i className="settingsPPIcon fa-solid fa-user"></i>
                        </label>
                        <input type="file" id="fileInput" style={{display: "none"}} onChange={(e) => setFile(e.target.files[0])}/>
                    </div>
                    <label>Username</label>
                    <input type="text" placeholder={user.account.username} onChange={(e) => setUsername(e.target.value)}/>
                    <label>Email</label>
                    <input type="text" placeholder={user.email} onChange={(e) => setEmail(e.target.value)}/>
                    <label>Password</label>
                    <input type="text" placeholder="password" onChange={(e) => setPassword(e.target.value)}/>
                    <label>Phone</label>
                    <input type="text" placeholder={user.phone} onChange={(e) => setPhone(e.target.value)}/>
                    <label>Fullname</label>
                    <input type="text" placeholder={user.fullname} onChange={(e) => setFullname(e.target.value)}/>
                    <button className="settingsSubmit" type="submit">Update</button>
                    {success && <span style={{color: "green", textAlign:"center", marginTop: "20px"}}>Profile has been updated...</span>}
                </form>
           </div>
           <Sidebar/>
        </div>
    )
}