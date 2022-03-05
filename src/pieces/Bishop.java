package pieces;

import chess.Square;

public class Bishop extends Piece {

    public Bishop(Square start) {
        super(start);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }
}