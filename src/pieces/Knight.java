package pieces;

import chess.Board;
import chess.Square;

public class Knight extends Piece {

    public Knight(boolean isWhite, Square square) {
        super(isWhite, square);
    }

    public boolean isValidMove(Square start, Square end, Board board) {

        if (Math.abs(start.rowpos - end.rowpos) * Math.abs(start.colpos - end.colpos) != 2) {
            return false;
        }

        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        return true;
    }

    public String toString() {
        return isWhite ? "wN " : "bN ";
    }
}