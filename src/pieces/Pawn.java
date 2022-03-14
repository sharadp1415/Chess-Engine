package pieces;

import chess.Square;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }

    public String toString() {
        return isWhite ? "wp " : "bp ";
    }
}