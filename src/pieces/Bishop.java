package pieces;

import chess.Board;
import chess.Square;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board board) {
        if (Math.abs(start.xpos - end.xpos) != Math.abs(start.ypos - end.ypos)) {
            return false;
        }

        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        return false;
    }

    public String toString() {
        return isWhite ? "wB " : "bB ";
    }
}