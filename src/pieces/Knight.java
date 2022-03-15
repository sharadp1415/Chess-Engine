package pieces;

import chess.Board;
import chess.Square;

public class Knight extends Piece {

    public Knight(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board board) {

        if (Math.abs(start.xpos - end.xpos) * Math.abs(start.ypos - end.ypos) != 2) {
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