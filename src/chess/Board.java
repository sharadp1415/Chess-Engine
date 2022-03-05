package chess;

public class Board {
    Square[][] board;

    public Board() {
        board = new Square[8][8];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(i, j, null);
            }
        }
    }
}
