package pieces;

import chess.Board;
import chess.Square;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board board) {
        if (Math.abs(start.xpos - end.xpos) != Math.abs(start.ypos - end.ypos)
                && (start.xpos - end.xpos) * (start.ypos - end.ypos) != 0) {
            return false;
        }

        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        return false;
    }

    public String toString() {
        return isWhite ? "wQ " : "bQ ";
    }
}
