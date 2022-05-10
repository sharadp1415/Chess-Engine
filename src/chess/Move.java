package chess;

import pieces.Piece;

public class Move {
    public Square start;
    public Square end;
    public boolean whiteTurn;
    public Piece movingPiece;

    public Move(Square start, Square end, boolean whiteTurn) {
        this.start = start;
        this.end = end;
        this.whiteTurn = whiteTurn;
        this.movingPiece = start.piece;
    }
}
