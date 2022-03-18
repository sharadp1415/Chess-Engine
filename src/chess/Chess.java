package chess;

import java.util.Scanner;

import pieces.Piece;

public class Chess {

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("exit")) {
            System.out.print("\n\nEnter Move: ");
            input = scanner.nextLine();
            String[] array = input.split(" ");
            // System.out.println(8 - (array[0].charAt(0) - 97));
            Square start = board.board[8 - Integer.parseInt(array[0].substring(1))][(array[0].charAt(0) - 97)];
            Square end = board.board[8 - Integer.parseInt(array[1].substring(1))][(array[1].charAt(0) - 97)];
            Piece piece = start.piece;
            if (piece.isValidMove(start, end, board)) {
                start.piece = null;
                end.piece = piece;
                piece.square = end;
                board.printBoard();
            } else {
                System.out.println("Invalid Move");
            }

            if (board.inCheck(true)) {
                // System.out.println("Check");
            }
        }

        scanner.close();
    }
}
