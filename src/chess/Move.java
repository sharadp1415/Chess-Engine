package chess;

import pieces.Piece;

public class Move {
    Square start;
    Square end;
    boolean whiteTurn;
    Piece movingPiece;

    public Move(Square start, Square end, boolean whiteTurn) {
        this.start = start;
        this.end = end;
        this.whiteTurn = whiteTurn;
        this.movingPiece = start.piece;
    }
}
