package pieces;

import chess.Square;

public class Knight extends Piece {

    public Knight(Square start) {
        super(start);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }
}