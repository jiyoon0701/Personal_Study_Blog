// src/main/frontend/src/App.js

import React, {useEffect, useState} from 'react';
import axios from 'axios';

function App() {
   const [hello, setHello] = useState('')

    const abc = ()=>{
        axios.get("/api/hello")
        .then(response =>console.log(response))
        .catch(error => console.log(error))
    }

    return (
        <>
        <div>
            백엔드에서 가져온 데이터입니다 : {hello}
        </div>
        <button onClick={abc} ></button>
        </>
    );
}

export default App;