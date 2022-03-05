package pieces;

import chess.Square;

public class Queen extends Piece {
    public Queen(Square start) {
        super(start);
    }

    public boolean isValidMove(Square start, Square end) {
        return false;
    }
}
