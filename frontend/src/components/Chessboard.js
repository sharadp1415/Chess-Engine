import { useState, useEffect, useRef } from "react";
import Tile from "./Tile";
import "./Chessboard.css";

const Chessboard = () => {
    const [board, setBoard] = useState([]);
    const [pickedSquare, setPickedSquare] = useState(null);
    const [highlightBoard, setHighlightBoard] = useState([]);
    // let square1 = null;
    const square1 = useRef(null);
    const square2 = useRef(null);

    // setPickedSquare(null);

    let output = [];
    let count = 0;

    //call from the api
    useEffect(() => {
        console.log("inital board state");
        fetch("http://localhost:8080/new-game", { method: "GET" })
            .then((response) => {
                const res = response.json();
                // console.log(res);
                return res;
            })
            .then((data) => {
                console.log(data);
                setBoard(data.board);
            });
    }, []);

    useEffect(() => {
        console.log(pickedSquare);
        if (square1.current == null) {
            square1.current = pickedSquare;
            if (square1.current !== null) {
                console.log(square1.current);
                let square = square1.current.x + " " + square1.current.y;
                fetch("http://localhost:8080/see-squares", {
                    body: square,
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                })
                    .then((response) => {
                        const res = response.json();
                        return res;
                    })
                    .then((data) => {
                        console.log(data);
                        setHighlightBoard(data.highlightBoard);
                    });
            }
        } else {
            square2.current = pickedSquare;
            let move =
                square1.current.x +
                " " +
                square1.current.y +
                " " +
                square2.current.x +
                " " +
                square2.current.y;
            //api call
            fetch("http://localhost:8080/perform-move", {
                body: move,
                method: "POST",
                headers: { "Content-Type": "application/json" },
            })
                .then((response) => {
                    const res = response.json();
                    return res;
                })
                .then((data) => {
                    console.log(data);
                    setBoard(data.board);
                    setHighlightBoard(data.highlightBoard);
                });
            square1.current = null;
            square2.current = null;
        }

        console.log(square1.current);
        console.log(square2.current);
    }, [pickedSquare]);

    for (let i = 0; i < 8; i++) {
        for (let j = 0; j < 8; j++) {
            output.push(
                <Tile
                    setPickedSquare={setPickedSquare}
                    colored={count % 2 === 1 ? true : false}
                    board={board}
                    location={{ x: i, y: j }}
                    highlightBoard={highlightBoard}
                />
            );
            count++;
        }
        count--;
    }

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
