package chess;

import pieces.Piece;

public class Square {
    public int xpos;
    public int ypos;
    public Piece piece;
    boolean isLightSquare;

    public Square(int xpos, int ypos, Piece piece, boolean isLightSquare) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.piece = piece;
        this.isLightSquare = isLightSquare;
    }
}
