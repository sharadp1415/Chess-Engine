package pieces;

import chess.Square;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }

    public String toString() {
        return isWhite ? "wR " : "bR ";
    }
}
