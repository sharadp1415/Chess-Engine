import { useState, useEffect } from "react";
import "./Tile.css";
import bB from "./images/bB.png";
import bK from "./images/bK.png";
import bN from "./images/bN.png";
import bQ from "./images/bQ.png";
import bp from "./images/bp.png";
import bR from "./images/bR.png";
import wB from "./images/wB.png";
import wK from "./images/wK.png";
import wN from "./images/wN.png";
import wp from "./images/wp.png";
import wQ from "./images/wQ.png";
import wR from "./images/wR.png";

import greydot from "./images/greydot.png";

const Tile = ({
    setPickedSquare,
    location,
    colored,
    board,
    highlightBoard,
}) => {
    // const {board, setBoard} = useState([]);

    return (
        <div
            className={(colored ? "tile colored" : "tile notcolored").concat(
                board.length !== 0 && board[location.x][location.y] !== null
                    ? " occupied"
                    : ""
            )}
            onClick={() => {
                // console.log(location);
                setPickedSquare(location);
            }}
        >
            {/* This is a Tile component */}
            {/* {board.length !== 0 ? board[location.x][location.y] : null} */}
            {/* {if ()} */}
            {imageSelector(board, location)}
            {greyDotAdder(highlightBoard, location)}
        </div>
    );
};

const imageSelector = (board, location) => {
    if (board.length === 0) {
        return null;
    }

    switch (board[location.x][location.y]) {
        case "bB":
            return <img className="icon" src={bB} alt="bp"></img>;
        case "bK":
            return <img className="icon" src={bK} alt="bK"></img>;
        case "bQ":
            return <img className="icon" src={bQ} alt="bQ"></img>;
        case "bR":
            return <img className="icon" src={bR} alt="bR"></img>;
        case "bN":
            return <img className="icon" src={bN} alt="bN"></img>;
        case "bp":
            return <img className="icon" src={bp} alt="bp"></img>;
        case "wB":
            return <img className="icon" src={wB} alt="wB"></img>;
        case "wK":
            return <img className="icon" src={wK} alt="wK"></img>;
        case "wQ":
            return <img className="icon" src={wQ} alt="wQ"></img>;
        case "wR":
            return <img className="icon" src={wR} alt="wR"></img>;
        case "wN":
            return <img className="icon" src={wN} alt="wN"></img>;
        case "wp":
            return <img className="icon" src={wp} alt="wp"></img>;
        default:
            return null;
    }
};

const greyDotAdder = (highlightBoard, location) => {
    if (
        highlightBoard.length !== 0 &&
        highlightBoard[location.x][location.y] === true
    ) {
        return <img className="greydot" src={greydot} alt="greydot"></img>;
    }
};

export default Tile;
