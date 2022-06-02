import "./write.css";
import { useContext, useState, useEffect } from "react";
import { Context } from "../../context/Context";
import axiosInstance from "../../helper/axios.js";
import uploadFiles from "../../helper/firebaseUploadImage";

export default function Write(){
    const [title, setTitle] = useState("")
    const [content, setContent] = useState("")
    const [file, setFile] = useState(null)
    const {user} = useContext(Context);
    const [cats, setCats] = useState([]);
    const [catSelected, setCatSelected] = useState("")


    const handleSubmit = async (e) =>{
        e.preventDefault();
        
        let imageURL = "";
        if(file !== null){
            imageURL = await uploadFiles(file);
        }

        const newPost = {
            userId: user.id,
            image: imageURL,
            title,
            content,
            status: "Chờ duyệt",
            categoryId: catSelected,
            publishedDate: "",
            updatedDate: "",
            censorId: ""   
        };

        try{
            const res = await axiosInstance.post("/articles", newPost);
            window.location.replace("/articles/" + res.data.id);
        }catch(err){

        }
    }

    useEffect(()=>{
        const getCats = async ()=>{
            const res = await axiosInstance.get("/category/");
            setCats(res.data);
            setCatSelected(res.data[0].id)
        }
        getCats();
    }, [])

    const auto_grow = (e) => {
        e.target.style.height = 'inherit';
        e.target.style.height = `${e.target.scrollHeight}px`;
    }

    return ( 
        <div className='write'>
            <img
                className="writeImg"
                src= {file ? URL.createObjectURL(file) : "https://lh3.googleusercontent.com/d/1ZRDEEB8d6lytFp18WiRPesyaP3YpEVn0=s1920?authuser=0"}
                alt=""
            /> 
            <form className="writeForm" onSubmit={handleSubmit}>
                
                <div className="writeFormSubmit">    
                
                <label for="cate">Choose a category:</label>
                    <select className="cate" onChange={(e) => setCatSelected(e.target.value)} >
                        {cats.map((c) => (
                            <option value={c.id}>{c.categoryName}</option>
                        ))}
                    </select>
                </div>
                
                <div className="writeFormGroup">
                    <label htmlFor="fileInput">
                        <i className="writeIcon fa-solid fa-plus"></i>
                    </label>
                    <input type="file" id="fileInput" style={{display: "none"}} onChange={(e) => setFile(e.target.files[0])}/>
                    <input type="text" placeholder="Title" maxLength={85} className="writeInput" autoFocus={true} onChange={e=>setTitle(e.target.value)}/>
                </div>
                <div className="writeFormGroup">
                    <textarea placeholder="Tell your story..." type="text" className="writeInput writeText" onInput={auto_grow} onChange={e=>setContent(e.target.value)}></textarea>
                </div>
                <button className="writeSubmit" type="submit" onSubmit={handleSubmit}>Submit</button>
                
            </form>
        </div>
    )
}

