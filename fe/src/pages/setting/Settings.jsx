import "./settings.css"
import SettingSidebar from "../../component/settingSidebar/SettingSidebar"
import { useContext, useState } from "react"
import { Context } from "../../context/Context"
import axiosInstance from "../../helper/axios.js"
import uploadFiles from "../../helper/firebaseUploadImage"

export default function Settings(){
    const {user, dispatch} = useContext(Context)
    const [file, setFile] = useState(null)

    const [username, setUsername] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [phone, setPhone] = useState("")
    const [fullname, setFullname] = useState("")
    const [description, setDescription] = useState("")

    const [success, setSuccess] = useState(false)

    const handleSubmit = async (e) =>{
        e.preventDefault();
        dispatch({type:"UPDATE_START"})

        let imageURL = "";
        if(file !== null){
            imageURL = await uploadFiles(file);
        }
        
        console.log(imageURL);

        const updatedUser = {
            id: user.id,
            account: {
                username,
                password
            }, 
            email,
            phone,
            fullname,
            description,
            image: imageURL,
            role: ""
        };

        console.log(updatedUser)

        try{
            const res = await axiosInstance.put("/user", updatedUser);
            setSuccess(true);
            dispatch({type:"UPDATE_SUCCESS", payload: res.data})
            setTimeout(window.location.reload(), 5000);
        }catch(err){ 
            dispatch({type:"UPDATE_FAILURE"})
        }
    }

    return (
        <div className='settings'>
           <div className="settingsWrapper">
                <div className="settingsTitle">
                    <span className="settingsUpdateTitle">Tài khoản của tôi</span>
                </div>
                <form className="settingsForm" onSubmit={handleSubmit}>
                    <label>Ảnh đại diện</label>
                    <div className="settingsPP">
                        <img
                            src={file ? URL.createObjectURL(file) : user.image ? user.image : "https://firebasestorage.googleapis.com/v0/b/uml-final.appspot.com/o/static_img%2Favatar-placeholder.png?alt=media&token=cce1eeaa-6b3a-407b-92ec-ff505016f167" }
                            alt=""
                        />
                        <label htmlFor="fileInput">
                            <i className="settingsPPIcon fa-solid fa-plus"></i>
                        </label>
                        <input type="file" id="fileInput" style={{display: "none"}} onChange={(e) => setFile(e.target.files[0])} />
                    </div>
                    <text>Tài khoản</text>
                    <label>Tên tài khoản</label>
                    <input type="text" placeholder={user.account.username} onChange={(e) => setUsername(e.target.value)}/>
                    <label>Email</label>
                    <input type="text" placeholder={user.email} onChange={(e) => setEmail(e.target.value)}/>
                    <label>Mật khẩu</label>
                    <input type="text" placeholder="password" onChange={(e) => setPassword(e.target.value)}/>

                    <text>Thông tin cá nhân</text>
                    <label>Số điện thoại</label>
                    <input type="text" placeholder={user.phone} onChange={(e) => setPhone(e.target.value)}/>
                    <label>Họ tên</label>
                    <input type="text" placeholder={user.fullname} onChange={(e) => setFullname(e.target.value)}/>
                    <label>Tiểu sử</label>
                    <input type="text" placeholder="Viết nên hành trình của bản thân..." onChange={(e) => setDescription(e.target.value)}/>
                    <button className="settingsSubmit" type="submit">Update</button>
                    {success && <span style={{color: "green", textAlign:"center", marginTop: "20px"}}>Profile has been updated...</span>}
                </form>
           </div>
           <SettingSidebar className="sidebar"/>
        </div>
    )
}