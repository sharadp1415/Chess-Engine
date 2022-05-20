import { useState, useEffect } from "react";
import Tile from "./Tile";
import "./Chessboard.css";

const Chessboard = () => {
    const { board, setBoard } = useState([]);
    const { pickedSquare, setPickedSquare } = useState();

    let output = [];
    let count = 0;

    //call from the api

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

    return <div className="chessboard">{output}</div>;
};

export default Chessboard;
