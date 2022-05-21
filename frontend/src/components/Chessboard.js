import { useState, useEffect, useRef } from "react";
import Tile from "./Tile";
import "./Chessboard.css";

const Chessboard = () => {
    const [board, setBoard] = useState([]);
    const [pickedSquare, setPickedSquare] = useState();
    // let square1 = null;
    const square1 = useRef(null);
    const square2 = useRef(null);

    // setPickedSquare(null);

    let output = [];
    let count = 0;

    //call from the api
    useEffect(() => {
        console.log("inital board state");
    }, []);

    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            output.push(
                <Tile
                    setPickedSquare={setPickedSquare}
                    colored={count % 2 === 1 ? true : false}
                    board={board}
                    setBoard={setBoard}
                    location={{ x: i, y: j }}
                />
            );
            count++;
        }
        count--;
    }

    useEffect(() => {
        console.log(pickedSquare);
        if (square1.current == null) {
            square1.current = pickedSquare;
        } else {
            square2.current = pickedSquare;
            //api call
        }

        console.log(square1.current);
        console.log(square2.current);
    }, [pickedSquare]);

    return <div className="chessboard">{output}</div>;
};

export default Chessboard;

// function getData() {
//     fetch("http://localhost:8081/profile", { method: "POST" })
//       .then((response) => {
//         const res = response.json();
//         console.log(res);
//         return res;
//       })
//       .then((data) => {
//         console.log(data);
//         setBackendData({
//           results: data.result,
//           about: data.about,
//         });
//         console.log(backendData);
//       });
//   }

//   let param = "MSFT";

//   function getData2() {
//     fetch("http://localhost:8081/stockExample", {
//       body: JSON.stringify({ name: param }),
//       method: "POST",
//       headers: { "Content-Type": "application/json" },
//     })
//       .then((response) => {
//         // console.log(response.json());
//         const res = response.json();
//         console.log(res);
//         // setProfileData(res);
//         return res;
//       })
//       .then((data) => {
//         console.log(data);
//         setBackendData({
//           results: data.result,
//           about: data.about,
//         });
//         console.log(backendData);
//       });
//   }
