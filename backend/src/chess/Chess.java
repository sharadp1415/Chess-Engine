package chess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.Queen;
import chess.pieces.Rook;
import engine.Engine;

/**
 * @author Naman Bajaj, Sharad Prasad
 */

/**
 * Main class
 * <p>
 * Runs a Chess game until a user wins, draws, or resigns
 */
public class Chess {

    public Board board;
    public Stack<Move> moveStack;
    Boolean isWhiteTurn;
    Boolean drawOffered;

    public Chess() {
        board = new Board(this);
        moveStack = new Stack<>();
        isWhiteTurn = true;
        drawOffered = false;
    }

    public void playGame() {
        board.printBoard();

        Scanner scanner = new Scanner(System.in);
        String input = "";

        while (!input.equals("exit")) {
            if (isWhiteTurn)
                System.out.print("\nWhite's Move: ");
            else
                System.out.print("\nBlack's Move: ");

            input = scanner.nextLine();

            // if one player resigns
            if (input.equals("resign")) {
                if (isWhiteTurn)
                    System.out.println("Black wins");
                else
                    System.out.println("White wins");
                break;
            }

            if (input.equals("undo")) {
                revertMove(moveStack.pop());
                isWhiteTurn = !isWhiteTurn;
                System.out.println();
                board.printBoard();
                continue;
            }

            String[] array = input.split(" ");

            // check for draw offered
            if (array.length > 2 && array[2].equals("draw?")) {
                drawOffered = true;
            }

            if (input.equals("best move")) {
                Move bestMove = Engine.bestMove(this, isWhiteTurn);
                performMove(bestMove);
                moveStack.add(bestMove);
                System.out.println();
                board.printBoard();
            } else {
                Square start = board.board[8 - Integer.parseInt(array[0].substring(1))][(array[0].charAt(0) - 97)];
                Square end = board.board[8 - Integer.parseInt(array[1].substring(1))][(array[1].charAt(0) - 97)];
                Piece piece = start.piece;

                if (piece == null || (isWhiteTurn && !piece.isWhite) || (!isWhiteTurn && piece.isWhite)) {
                    System.out.print("Illegal move, try again");
                    continue;
                }

                Move move = new Move(start, end, isWhiteTurn);
                if (array.length > 2) {
                    int convertPiece = array[2].charAt(0);
                    move.promotion = convertPiece;
                }

                if (piece.isValidMove(start, end, this)) {
                    // add move to stack
                    performMove(move);
                    moveStack.add(move);
                    System.out.println();
                    board.printBoard();
                } else {
                    System.out.println("Illegal move, try again");
                    continue;
                }
            }

            if (board.inCheckmate(!isWhiteTurn)) {
                System.out.println("\nCheckmate");
                if (isWhiteTurn) {
                    System.out.println("White wins");
                } else {
                    System.out.println("Black wins");
                }
                break;
            }

            if (board.inCheck(!isWhiteTurn)) {
                System.out.print("\nCheck");
            }

            if (drawOffered) {
                if (isWhiteTurn)
                    System.out.print("\nBlack's Move: ");
                else
                    System.out.print("\nWhite's Move: ");

                while (!scanner.nextLine().equals("draw"))
                    System.out.println("Illegal move, try again");
                break;
            }

            isWhiteTurn = !isWhiteTurn;
        }

        scanner.close();
    }

    // does not check for valid move
    public void performMove(Move move) {
        Square start = move.start;
        Square end = move.end;
        Piece piece = start.piece;
        Piece capturedPiece = end.piece;
        // System.out.println("captured piece: " + capturedPiece);

        // for en passant
        if (piece instanceof Pawn) {
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

        // pawn promotion
        if (piece instanceof Pawn) {
            Pawn p = (Pawn) piece;
            if (end.rowpos == 7 || end.rowpos == 0) { // pawn reached end
                move.isPromotion = true;
                int convertPiece = move.promotion;

                boolean iW = piece.isWhite;

                if (p.isWhite)
                    board.whitePieces.remove(piece);
                else
                    board.blackPieces.remove(piece);

                switch (convertPiece) {
                    // convert to knight
                    case 78:
                        piece = new Knight(iW, end);
                        break;

                    // convert to rook
                    case 82:
                        piece = new Rook(iW, end);
                        break;

                    // convert to bishop
                    case 66:
                        piece = new Bishop(iW, end);
                        break;

                    // convert to queen
                    default:
                        piece = new Queen(iW, end);
                        break;
                }

                if (p.isWhite)
                    board.whitePieces.add(piece);
                else
                    board.blackPieces.add(piece);

                move.promotedPiece = piece;
                move.isPromotion = true;
            }

        }

        // for castling
        if (piece instanceof King && Math.abs(start.colpos - end.colpos) == 2) {
            if (piece.isWhite) {
                // castling king side (right)
                if ((start.colpos - end.colpos) == -2) {
                    ((Rook) board.board[7][7].piece).moved = true;
                    board.board[7][5].piece = board.board[7][7].piece;
                    board.board[7][7].piece = null;
                    board.board[7][5].piece.square = board.board[7][5];
                }

                // castling queen side (left)
                if ((start.colpos - end.colpos) == 2) {
                    ((Rook) board.board[7][0].piece).moved = true;
                    board.board[7][3].piece = board.board[7][0].piece;
                    board.board[7][0].piece = null;
                    board.board[7][3].piece.square = board.board[7][3];
                }
            }

            // black castling
            else {
                // castling king side (right)
                if ((start.colpos - end.colpos) == -2) {
                    ((Rook) board.board[0][7].piece).moved = true;
                    board.board[0][5].piece = board.board[0][7].piece;
                    board.board[0][7].piece = null;
                    board.board[0][5].piece.square = board.board[0][5];
                }

                // castling queen side (left)
                if ((start.colpos - end.colpos) == 2) {
                    ((Rook) board.board[0][0].piece).moved = true;
                    board.board[0][3].piece = board.board[0][0].piece;
                    board.board[0][0].piece = null;
                    board.board[0][3].piece.square = board.board[0][3];

                }
            }
        }

        // remove capturedPiece
        if (capturedPiece != null) {
            move.capturedPiece = capturedPiece;
            capturedPiece.isTaken = true;
            // System.out.println("captured " + move.capturedPiece);
            if (capturedPiece.isWhite) {
                board.whitePieces.remove(capturedPiece);
            } else {
                board.blackPieces.remove(capturedPiece);
            }
        }

        // update kingposition
        if (piece instanceof King) {
            if (piece.isWhite) {
                board.whiteKingPosition = end;
            } else {
                board.blackKingPosition = end;
            }
        }

        piece.moved = true;
        start.piece = null;
        end.piece = piece;
        piece.square = end;
    }

    public void revertMove(Move move) {
        Square end = move.end;
        Square start = move.start;
        Piece movingPiece = move.movingPiece;
        Piece capturedPiece = move.capturedPiece;

        start.piece = movingPiece;
        movingPiece.square = start;
        movingPiece.moved = move.piecePreviouslyMoved;
        end.piece = null;

        if (capturedPiece != null) {
            capturedPiece.square.piece = capturedPiece;
            // capturedPiece.square = end;
            capturedPiece.isTaken = false;
            if (capturedPiece.isWhite) {
                board.whitePieces.add(capturedPiece);
            } else {
                board.blackPieces.add(capturedPiece);
            }
        }

        if (move.isPromotion) {
            if (movingPiece.isWhite) {
                board.whitePieces.add(movingPiece);
                board.whitePieces.remove(move.promotedPiece);
            } else {
                board.blackPieces.add(movingPiece);
                board.blackPieces.remove(move.promotedPiece);
            }
            return;
        }

        // check for castling and revert
        if (movingPiece instanceof King && Math.abs(start.colpos - end.colpos) == 2) {
            if (movingPiece.isWhite) {
                // castling king side (right)
                if ((start.colpos - end.colpos) == -2) {
                    ((Rook) board.board[7][5].piece).moved = false;
                    board.board[7][7].piece = board.board[7][5].piece;
                    board.board[7][5].piece = null;
                    board.board[7][7].piece.square = board.board[7][7];
                }

                // castling queen side (left)
                if ((start.colpos - end.colpos) == 2) {
                    ((Rook) board.board[7][3].piece).moved = false;
                    board.board[7][0].piece = board.board[7][3].piece;
                    board.board[7][3].piece = null;
                    board.board[7][0].piece.square = board.board[7][0];
                }
            }

            // black castling
            else {
                // castling king side (right)
                if ((start.colpos - end.colpos) == -2) {
                    ((Rook) board.board[0][5].piece).moved = false;
                    board.board[0][7].piece = board.board[0][5].piece;
                    board.board[0][5].piece = null;
                    board.board[0][7].piece.square = board.board[0][7];
                }

                // castling queen side (left)
                if ((start.colpos - end.colpos) == 2) {
                    ((Rook) board.board[0][3].piece).moved = false;
                    board.board[0][0].piece = board.board[0][3].piece;
                    board.board[0][3].piece = null;
                    board.board[0][0].piece.square = board.board[0][0];
                }
            }
        }

        // update kingPosition
        if (movingPiece instanceof King) {
            if (movingPiece.isWhite) {
                board.whiteKingPosition = start;
            } else {
                board.blackKingPosition = start;
            }
        }
    }

    public List<Move> generateAllLegalMoves(boolean isWhiteTurn) {
        List<Move> outputList = new ArrayList<>();

        Set<Piece> pieces;
        if (isWhiteTurn) {
            pieces = new HashSet<>(board.whitePieces);
        } else {
            pieces = new HashSet<>(board.blackPieces);
        }

        for (Piece piece : pieces) {
            if (piece.isTaken)
                continue;
            Square start = piece.square;
            for (Square[] array : board.board) {
                for (Square square : array) {
                    if (piece.isValidMove(start, square, this)) {
                        Move potentialMove = new Move(start, square, isWhiteTurn);
                        outputList.add(potentialMove);
                    }
                }
            }
        }

        // pieces.stream().forEach(piece -> {
        // Square start = piece.square;
        // Arrays.stream(board.board)
        // .forEach(array -> {
        // Arrays.stream(array)
        // .forEach(square -> {
        // if (piece.isValidMove(start, square, this)) {
        // Move potentialMove = new Move(start, square, isWhiteTurn);
        // outputList.add(potentialMove);
        // }
        // });
        // });
        // });

        return outputList;
    }

    /**
     * Main chess runner that runs until a user wins, draws, or resigns
     * 
     * @param args main arguments (unused)
     */
    public static void main(String[] args) {
        Chess chessGame = new Chess();
        chessGame.playGame();
    }
}
