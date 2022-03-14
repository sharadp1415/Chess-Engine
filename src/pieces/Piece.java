package pieces;

import chess.Square;

public abstract class Piece {

    public boolean isTaken;
    public boolean isWhite;

    public Piece(boolean isWhite) {
        isTaken = false;
        this.isWhite = isWhite;
    }

    public Piece() {
        this(false);
        isTaken = false;
    }

    public abstract boolean isValidMove(Square start, Square end);
}