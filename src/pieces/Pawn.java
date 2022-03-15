package pieces;

import chess.Board;
import chess.Square;

public class Pawn extends Piece {

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board board) {
        return false;
    }

    public String toString() {
        return isWhite ? "wp " : "bp ";
    }
}