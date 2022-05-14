import "./header.css"

export default function Header(){
    return ( 
        <div className="header">
            <div className="headerTitles">
                
                <span className="headerTitleSm">TRANG THÔNG TIN ĐIỆN TỬ MỚI NHẤT CỦA HCMUTE</span>
                <span className="headerTitleLg">UTE News</span>
            </div>
            <img 
                className="headerImg"
                src="https://firebasestorage.googleapis.com/v0/b/uml-final.appspot.com/o/static_img%2Fthumbnail_home.jpg?alt=media&token=e4806ea1-b4d8-4aca-9fc7-9a753d5bf9b4"
                alt="" />
        </div>
    )
}