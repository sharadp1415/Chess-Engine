package pieces;

import chess.Square;

public class King extends Piece {

    public King(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end) {

        if (start.xpos - end.xpos > 1 || start.ypos - end.ypos > 1) {
            return false;
        }

        return true;
    }

    public String toString() {
        return isWhite ? "wK " : "bK ";
    }
}
