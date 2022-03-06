package pieces;

import chess.Square;

public abstract class Piece {

    Square currentPosition;
    boolean isTaken;
    boolean isWhite;

    public Piece(Square start, boolean isWhite) {
        currentPosition = start;
        isTaken = false;
        this.isWhite = isWhite;
    }

    public Piece(Square start) {
        this(start, false);
    }

    public abstract boolean isValidMove(Square start, Square end);
}