import Sidebar from "../../component/sidebar/Sidebar"
import SinglePost from "../../component/singlePost/SinglePost"
import { Context } from "../../context/Context";
import { useContext } from "react"
import "./single.css"

export default function Single(){
    const {user} = useContext(Context)
    if(user){
        if(user.role === "creator" || user.role === "censor" || user.role === "admin"){
            return ( 
                <div className='single'>
                    <SinglePost/>
                </div>
            )
        }
    }
    return ( 
        <div className='single'>
            <SinglePost/>
            <Sidebar/>
        </div>
    )
}