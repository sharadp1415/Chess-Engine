package pieces;

import chess.Board;
import chess.Square;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board board) {
        if (Math.abs(start.rowpos - end.rowpos) != Math.abs(start.colpos - end.colpos)
                && (start.rowpos - end.rowpos) * (start.colpos - end.colpos) != 0) {
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
