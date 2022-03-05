package pieces;

import chess.Square;

public class Pawn extends Piece {

    public Pawn(Square start) {
        super(start);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }
}