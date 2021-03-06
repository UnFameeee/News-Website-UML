import "./settings.css"
import SidebarSetting from "../../component/settingSidebar/SettingSidebar"
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
            role: user.role
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

    const auto_grow = (e) => {
        e.target.style.height = 'inherit';
        e.target.style.height = `${e.target.scrollHeight}px`;
    }

    if(user.role === "member" || user.role === "creator" || user.role === "censor"){
        return (
            <div className='settings'>
               <div className="settingsWrapper">
                    <div className="settingsTitle">
                        <span className="settingsUpdateTitle">T??i kho???n c???a t??i</span>
                    </div>
                    <form className="settingsForm" onSubmit={handleSubmit}>
                        <label>???nh ?????i di???n</label>
                        <div className="settingsPP">
                            <img
                                src={file ? URL.createObjectURL(file) : user.image ? user.image : "https://firebasestorage.googleapis.com/v0/b/uml-final.appspot.com/o/static_img%2Favatar-placeholder.png?alt=media&token=c060bc06-00e2-461f-94f6-aac6e44cc718"  }
                                //"https://lh3.googleusercontent.com/d/1NCdOtipy2LSXUiNbk6Eop5sdx2WZwqvR=s512?authuser=0"
                                alt=""
                            />
                            <label htmlFor="fileInput">
                                <i className="settingsPPIcon fa-solid fa-plus"></i>
                            </label>
                            <input type="file" id="fileInput" style={{display: "none"}} onChange={(e) => setFile(e.target.files[0])} />
                        </div>
                        <text>T??i kho???n</text>
                        <label>T??n t??i kho???n</label>
                        <input type="text" placeholder={user.account.username} onChange={(e) => setUsername(e.target.value)}/>
                        <label>Email</label>
                        <input type="text" placeholder={user.email} onChange={(e) => setEmail(e.target.value)}/>
                        <label>M???t kh???u</label>
                        <input type="text" placeholder="password" onChange={(e) => setPassword(e.target.value)}/>
    
                        <text>Th??ng tin c?? nh??n</text>
                        <label>S??? ??i???n tho???i</label>
                        <input type="text" placeholder={user.phone} onChange={(e) => setPhone(e.target.value)}/>
                        <label>H??? t??n</label>
                        <input type="text" placeholder={user.fullname} onChange={(e) => setFullname(e.target.value)}/>
                        <label>Ti???u s???</label>
                        <textarea type="text" maxLength={1000} placeholder="Vi???t n??n 1000 t??? v??? h??nh tr??nh c???a b???n th??n..." onInput={auto_grow} onChange={(e) => setDescription(e.target.value)}/>
                        <button className="settingsSubmit" type="submit">Update</button>
                        {success && <span style={{color: "green", textAlign:"center", marginTop: "20px"}}>Profile has been updated...</span>}
                    </form>
               </div>
               <SidebarSetting className="sidebarSetting"/>
            </div>
        )
    }else if(user.role === "admin"){
        
    }
    
}