package pieces;

import chess.Square;

public class Rook extends Piece {

    public Rook(Square start) {
        super(start);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }
}
