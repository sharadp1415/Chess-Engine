package pieces;

import chess.Board;
import chess.Square;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board board) {
        if (start.equals(end))
            return false;

        // check if piece moved in straight line
        if ((start.rowpos - end.rowpos) * (start.colpos - end.colpos) != 0) {
            return false;
        }

        if (start.rowpos - end.rowpos == 0) {

        }

        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        return false;
    }

    public String toString() {
        return isWhite ? "wR " : "bR ";
    }
}
