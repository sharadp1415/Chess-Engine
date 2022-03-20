package chess;

import java.util.Scanner;

import pieces.King;
import pieces.Pawn;
import pieces.Piece;

/**
 * @author Naman Bajaj, Sharad Prasad
 */

/**
 * En passant in pawn class is work in progress may break code, comment out if
 * does
 */
public class Chess {

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        Scanner scanner = new Scanner(System.in);
        String input = "";

        Boolean isWhiteTurn = true;
        int moveCounter = 0;

        while (!input.equals("exit")) {
            if (isWhiteTurn)
                System.out.print("\nWhite's Move: ");
            else
                System.out.print("\nBlack's Move: ");

            moveCounter++;
            input = scanner.nextLine();

            // if one player resigns
            if (input.equals("resign")) {
                if (isWhiteTurn)
                    System.out.println("Black wins");
                else
                    System.out.println("White wins");
                break;
            }

            String[] array = input.split(" ");

            // check for draw offered
            if (array.length > 2 && array[2].equals("draw?")) {
                while (!scanner.nextLine().equals("draw"))
                    System.out.println("Illegal move, try again");
                break;
            }

            // System.out.println(8 - (array[0].charAt(0) - 97));
            Square start = board.board[8 - Integer.parseInt(array[0].substring(1))][(array[0].charAt(0) - 97)];
            Square end = board.board[8 - Integer.parseInt(array[1].substring(1))][(array[1].charAt(0) - 97)];
            Piece piece = start.piece;

            if (piece == null || (isWhiteTurn && !piece.isWhite) || (!isWhiteTurn && piece.isWhite)) {
                System.out.print("Illegal move, try again");
                continue;
            }

            if (piece.isValidMove(start, end, board)) {

                // castling check (moved to King class)

                Piece capturedPiece = end.piece;

                // for en passant
                if (piece instanceof Pawn) {
                    Piece p = (Pawn) piece;
                    if (Math.abs(end.rowpos - start.rowpos) == 1 && Math.abs(end.colpos -
                            start.colpos) == 1
                            && end.piece == null) {
                        if (piece.isWhite) {
                            capturedPiece = board.board[end.rowpos + 1][end.colpos].piece;
                            board.board[end.rowpos + 1][end.colpos].piece = null;
                        } else {
                            capturedPiece = board.board[end.rowpos - 1][end.colpos].piece;
                            board.board[end.rowpos - 1][end.colpos].piece = null;
                        }
                        // board.printBoard();

                    }
                }

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
                continue;
            }

            if (board.inCheck(false)) {
                System.out.println("\nCheck");
            }

            if (board.inCheckmate(false)) {
                System.out.println("Checkmate");
            }

            // for en passant
            for (Piece pawn : board.blackPieces) {
                if (pawn instanceof Pawn) {
                    ((Pawn) pawn).justMoved = false;
                }
            }

            for (Piece pawn : board.whitePieces) {
                if (pawn instanceof Pawn) {
                    ((Pawn) pawn).justMoved = false;
                }
            }

            if (piece instanceof Pawn && Math.abs(start.rowpos - end.rowpos) == 2) {
                ((Pawn) piece).justMoved = true;
            }

            isWhiteTurn = !isWhiteTurn;
        }

        scanner.close();
    }
}
