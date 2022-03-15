package pieces;

import chess.Board;
import chess.Square;

public abstract class Piece {

    public boolean isTaken;
    public boolean isWhite;

    public Piece(boolean isWhite) {
        isTaken = false;
        this.isWhite = isWhite;
    }

    public Piece() {
        this(false);
        isTaken = false;
    }

    // checks if move is valid for the piece, does NOT check for OutOfBounds
    public abstract boolean isValidMove(Square start, Square end, Board board);
}