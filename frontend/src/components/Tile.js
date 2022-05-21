import { useState, useEffect } from "react";

const Tile = ({ setPickedSquare, location, colored }) => {
    // const {board, setBoard} = useState([]);

    return (
        <div
            className={colored ? "tile colored" : "tile notcolored"}
            onClick={() => {
                // console.log(location);
                setPickedSquare(location);
            }}
        >
            This is a Tile component
        </div>
    );
};

export default Tile;
