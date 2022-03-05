package pieces;

import chess.Square;

public abstract class Piece {

    Square currentPosition;
    boolean isTaken;

    public Piece(Square start) {
        currentPosition = start;
        isTaken = false;
    }

    public abstract boolean isValidMove(Square start, Square end);
}