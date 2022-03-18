package pieces;

import chess.Board;
import chess.Square;

public class King extends Piece {

    public King(boolean isWhite, Square square) {
        super(isWhite, square);
    }

    public boolean isValidMove(Square start, Square end, Board board) {

        if (start.rowpos - end.rowpos > 1 || start.colpos - end.colpos > 1) {
            return false;
        }

        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        return true;
    }

    public String toString() {
        return isWhite ? "wK " : "bK ";
    }
}
