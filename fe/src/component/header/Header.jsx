import { useContext } from "react"
import { Context } from "../../context/Context"
import "./header.css"

export default function Header(){
    const {user} = useContext(Context)

    if(user){
        if(user.role === "creator" || user.role === "admin" || user.role === "censor"){
            return ( 
                <div className="header">
                    <div className="headerTitles">
                        
                        <span className="headerTitleSm">TRANG QUẢN TRỊ HỆ THỐNG CỦA UTE NEWS</span>
                        <span className="headerTitleLg">UTE News</span>
                    </div>
                    <img 
                        className="headerImg"
                        src="https://lh3.googleusercontent.com/d/19ag6r2vHQQyF5goiLQ00lGsuWq5RAhO1=s1920?authuser=0"
                        alt="" />
                </div>
            )
        }
    } 
    return ( 
        <div className="header">
            <div className="headerTitles">
                
                <span className="headerTitleSm">TRANG THÔNG TIN ĐIỆN TỬ MỚI NHẤT CỦA HCMUTE</span>
                <span className="headerTitleLg">UTE News</span>
            </div>
            <img 
                className="headerImg"
                // src="https://firebasestorage.googleapis.com/v0/b/uml-final.appspot.com/o/static_img%2Fthumbnail_home.jpg?alt=media&token=e4806ea1-b4d8-4aca-9fc7-9a753d5bf9b4"
                src="https://lh3.googleusercontent.com/d/1j9bwHWyTiqjM265DG0n5phy7K9aa5LgO=s1920"
                alt="" />
        </div>
    )
}