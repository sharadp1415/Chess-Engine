package pieces;

import chess.Board;
import chess.Square;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board board) {
        if ((start.xpos - end.xpos) * (start.ypos - end.ypos) != 0) {
            return false;
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
