package pieces;

import chess.Square;

public class King extends Piece {

    public King(Square start) {
        super(start);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }
}
