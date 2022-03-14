package pieces;

import chess.Square;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }

    public String toString() {
        return isWhite ? "wN " : "bN ";
    }
}