package pieces;

import java.util.HashSet;

import chess.Board;
import chess.Square;

public abstract class Piece {

    public boolean isTaken;
    public boolean isWhite;
    public Square square;

    public Piece(boolean isWhite, Square square) {
        this.isTaken = false;
        this.square = square;
        this.isWhite = isWhite;
    }

    public Piece() {
        this(false, null);
    }

    // checks if move is valid for the piece, does NOT check for OutOfBounds
    public abstract boolean isValidMove(Square start, Square end, Board b);

    public abstract HashSet<Square> squaresBetween(Square start, Square end, Board b);
}