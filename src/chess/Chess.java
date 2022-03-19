package chess;

import java.util.Scanner;

import pieces.Piece;

/**
 * @author Naman Bajaj, Sharad Prasad
 */

public class Chess {

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        Scanner scanner = new Scanner(System.in);
        String input = "";

        Boolean isWhiteTurn = true;

        while (!input.equals("exit")) {
            System.out.print("\n\nEnter Move: ");
            input = scanner.nextLine();

            if(input.equals("resign")){
                if(isWhiteTurn)
                    System.out.println("Black wins");
                else
                    System.out.println("White wins");
                break;
            }

            String[] array = input.split(" ");
            // System.out.println(8 - (array[0].charAt(0) - 97));
            Square start = board.board[8 - Integer.parseInt(array[0].substring(1))][(array[0].charAt(0) - 97)];
            Square end = board.board[8 - Integer.parseInt(array[1].substring(1))][(array[1].charAt(0) - 97)];
            Piece piece = start.piece;
            if (piece.isValidMove(start, end, board)) {
                // implement capturing a piece and removing it from set of black or white pieces
                // and check for castling
                // might move this logic to a different method in Chess class

                Piece capturedPiece = end.piece;
                if (capturedPiece != null) {
                    if (capturedPiece.isWhite) {
                        board.whitePieces.remove(capturedPiece);
                    } else {
                        board.blackPieces.remove(capturedPiece);
                    }
                }
                start.piece = null;
                end.piece = piece;
                piece.square = end;
                board.printBoard();
            } else {
                System.out.println("Invalid Move");
            }

            if (board.inCheck(false)) {
                System.out.println("\nCheck");
            }

            if (board.inCheckmate(false)) {
                System.out.println("Checkmate");
            }

            isWhiteTurn = !isWhiteTurn;
        }

        scanner.close();
    }
}
