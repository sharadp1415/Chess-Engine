package chess;

public class Board {
    Square[][] board;

    public Board() {
        board = new Square[8][8];
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(i, j, null, count % 2 == 1 ? false : true);
                count++;
            }
            count--;
        }
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].isLightSquare) {
                    System.out.print("OO ");
                } else {
                    System.out.print("## ");
                }
            }
            System.out.println(8 - i);
        }

        System.out.print(" ");
        for (int i = 0; i < board.length; i++) {
            char letter = (char) (i + 97);
            System.out.print(letter + "  ");
        }
    }
}
