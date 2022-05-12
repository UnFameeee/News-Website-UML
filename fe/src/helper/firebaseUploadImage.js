import { storage } from "../firebase/firebase-db";
import { ref, getDownloadURL, uploadBytesResumable } from "firebase/storage";

const uploadFiles = (file) => {
    return new Promise((resolve, reject) => {
        try{
            if(file === null){
                return "";
            } 
            const sotrageRef = ref(storage, `files/${file.name}`);
            const uploadTask = uploadBytesResumable(sotrageRef, file);
    
            uploadTask.on(
                "state_changed",
                (snapshot) => {
                    const prog = Math.round(
                        (snapshot.bytesTransferred / snapshot.totalBytes) * 100
                    )
                    console.log(prog);
                },
                (error) => console.log(error),
                () => {
                    getDownloadURL(uploadTask.snapshot.ref).then((downloadURL) => {
                        // console.log("File available at", downloadURL)
                        resolve(downloadURL);
                        return downloadURL;
                    })
                }
            );
        }catch(error){
            console.error(error);
            reject(error);
        };
    })
};

export default uploadFiles;