package pieces;

import chess.Square;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }

    public String toString() {
        return isWhite ? "wB " : "bB ";
    }
}