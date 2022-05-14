import "./write.css";
import { useContext, useState } from "react";
import { Context } from "../../context/Context";
import axiosInstance from "../../helper/axios.js";

export default function Write(){
    const [title, setTitle] = useState("")
    const [desc, setDesc] = useState("")
    const [file, setFile] = useState(null)
    const {user} = useContext(Context);

    const handleSubmit = async (e) =>{
        e.preventDefault();
        const newPost = {
            username: user.username,
            title,
            desc
        };

        axiosInstance.post("/posts")
        try{
            const res = await axiosInstance.post("/posts", newPost);
            window.location.replace("/post/" + res.data._id);
        }catch(err){

        }
    }

    const auto_grow = (e) => {
        e.target.style.height = 'inherit';
        e.target.style.height = `${e.target.scrollHeight}px`;
    }

    return ( 
        <div className='write'>
            <img
                className="writeImg"
                src= {file ? URL.createObjectURL(file) : "https://firebasestorage.googleapis.com/v0/b/uml-final.appspot.com/o/static_img%2Fempty%20image.png?alt=media&token=79da93b8-ce8c-4df0-9e45-82d974b1afae"}
                alt=""
            /> 
            <form className="writeForm" onSubmit={handleSubmit}>
                <div className="writeFormGroup">
                    <label htmlFor="fileInput">
                        <i className="writeIcon fa-solid fa-plus"></i>
                    </label>
                    <input type="file" id="fileInput" style={{display: "none"}} onChange={(e) => setFile(e.target.files[0])}/>
                    <input type="text" placeholder="Title" className="writeInput" autoFocus={true} onChange={e=>setTitle(e.target.value)}/>
                </div>
                <div className="writeFormGroup">
                    <textarea placeholder="Tell your story..." type="text" className="writeInput writeText" onInput={auto_grow} onChange={e=>setDesc(e.target.value)}></textarea>
                </div>
                <button className="writeSubmit" type="submit" onSubmit={handleSubmit}>Submit</button>
            </form>
        </div>
    )
}