import { initializeApp } from "firebase/app";
import { getStorage } from "firebase/storage";

const firebaseConfig = {
    apiKey: "AIzaSyCyQxQDpHne7tpTgxp7AgnckFS41hkc6Cg",
    authDomain: "uml-final.firebaseapp.com",
    databaseURL: "https://uml-final-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "uml-final",
    storageBucket: "uml-final.appspot.com",
    messagingSenderId: "1023299260968",
    appId: "1:1023299260968:web:770186a42a2f262adbdb1d",
    measurementId: "G-0Z55VY6XG0"
  };

const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);