package chess;

import pieces.Piece;

public class Square {
    public int rowpos;
    public int colpos;
    public Piece piece;
    boolean isLightSquare;

    public Square(int rowpos, int colpos, Piece piece, boolean isLightSquare) {
        this.rowpos = rowpos;
        this.colpos = colpos;
        this.piece = piece;
        this.isLightSquare = isLightSquare;
    }

}
