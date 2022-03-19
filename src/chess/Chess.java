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
            if(isWhiteTurn)
                System.out.print("\nWhite's Move: ");
            else
                System.out.print("\nBlack's Move: ");

            input = scanner.nextLine();

            // if one player resigns
            if(input.equals("resign")){
                if(isWhiteTurn)
                    System.out.println("Black wins");
                else
                    System.out.println("White wins");
                break;
            }

            String[] array = input.split(" ");

            // check for draw offered
            if(array.length > 2 && array[2].equals("draw?")){
                while(!scanner.nextLine().equals("draw"))
                    System.out.println("Illegal move, try again");
                break;
            }

            // System.out.println(8 - (array[0].charAt(0) - 97));
            Square start = board.board[8 - Integer.parseInt(array[0].substring(1))][(array[0].charAt(0) - 97)];
            Square end = board.board[8 - Integer.parseInt(array[1].substring(1))][(array[1].charAt(0) - 97)];
            Piece piece = start.piece;

            if(piece == null || (isWhiteTurn && !piece.isWhite) || (!isWhiteTurn && piece.isWhite)){
                System.out.print("Illegal move, try again");
                continue;
            }

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
                System.out.println();
                board.printBoard();
                System.out.println();
            } else {
                System.out.println("Illegal move, try again");
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
