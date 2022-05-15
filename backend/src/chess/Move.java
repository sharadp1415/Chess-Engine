package chess;

import pieces.Piece;

public class Move {
    public Square start;
    public Square end;
    public boolean whiteTurn;
    public Piece movingPiece;
    public Piece capturedPiece;
    public int promotion;
    public Piece promotedPiece;
    public boolean isPromotion;
    public boolean piecePreviouslyMoved;
    // public boolean isCastling;

    public Move(Square start, Square end, boolean whiteTurn) {
        this.start = start;
        this.end = end;
        this.whiteTurn = whiteTurn;
        this.movingPiece = start.piece;
        capturedPiece = null;
        promotion = 0;
        promotedPiece = null;
        piecePreviouslyMoved = movingPiece.moved;
    }

    public String toString() {
        if (capturedPiece != null) {
            return movingPiece + "x" + end;
        } else {
            return movingPiece + " " + end;
        }
    }
}
