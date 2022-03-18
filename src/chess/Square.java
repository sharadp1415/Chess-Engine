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

    public String toString() {
        // Square start = board.board[8 -
        // Integer.parseInt(array[0].substring(1))][(array[0].charAt(0) - 97)];
        // Square end = board.board[8 -
        // Integer.parseInt(array[1].substring(1))][(array[1].charAt(0) - 97)];

        int row = 8 - rowpos;
        Character col = (char) (97 + colpos);
        return "" + col + "" + row;
    }
}
