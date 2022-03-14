package pieces;

import chess.Square;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }

    public String toString() {
        return isWhite ? "wQ " : "bQ ";
    }
}
