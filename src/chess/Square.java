package chess;

import pieces.Piece;

public class Square {
    int xpos;
    int ypos;
    Piece piece;

    public Square(int xpos, int ypos, Piece piece) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.piece = piece;
    }
}
