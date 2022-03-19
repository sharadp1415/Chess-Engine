package chess;

import java.util.HashSet;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/**
 * @author Naman Bajaj, Sharad Prasad
 */

public class Board {
    public Square[][] board;
    // might add HashSets for set of black pieces and set of white pieces
    public HashSet<Piece> blackPieces;
    public HashSet<Piece> whitePieces;

    public Board() {
        board = new Square[8][8];
        blackPieces = new HashSet<>();
        whitePieces = new HashSet<>();

        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = new Square(i, j, null, count % 2 == 1 ? false : true);
                count++;
            }
            count--;
        }

        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            board[1][i].piece = new Pawn(false, board[1][i]);

            board[6][i].piece = new Pawn(true, board[6][i]);
        }

        board[0][0].piece = new Rook(false, board[0][0]);
        board[0][7].piece = new Rook(false, board[0][7]);
        board[0][1].piece = new Knight(false, board[0][1]);
        board[0][6].piece = new Knight(false, board[0][6]);
        board[0][2].piece = new Bishop(false, board[0][2]);
        board[0][5].piece = new Bishop(false, board[0][5]);
        board[0][3].piece = new Queen(false, board[0][3]);
        board[0][4].piece = new King(false, board[0][4]);

        board[7][0].piece = new Rook(true, board[7][0]);
        board[7][7].piece = new Rook(true, board[7][7]);
        board[7][1].piece = new Knight(true, board[7][1]);
        board[7][6].piece = new Knight(true, board[7][6]);
        board[7][2].piece = new Bishop(true, board[7][2]);
        board[7][5].piece = new Bishop(true, board[7][5]);
        board[7][3].piece = new Queen(true, board[7][3]);
        board[7][4].piece = new King(true, board[7][4]);

        for (int i = 0; i < 8; i++) {
            blackPieces.add(board[0][i].piece);
            blackPieces.add(board[1][i].piece);
            whitePieces.add(board[7][i].piece);
            whitePieces.add(board[6][i].piece);
        }
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j].piece != null) {
                    System.out.print(board[i][j].piece);
                } else if (board[i][j].isLightSquare) {
                    System.out.print("   ");
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

    // check whether white or black king is in check
    public boolean inCheck(boolean isWhite) {
        HashSet<Piece> oppPieces = null;
        Square kingPosition = null;

        if (isWhite) {
            oppPieces = blackPieces;
            for (Piece piece : whitePieces) {
                if (piece instanceof King) {
                    kingPosition = piece.square;
                }
            }
        } else {
            oppPieces = whitePieces;
            for (Piece piece : blackPieces) {
                if (piece instanceof King) {
                    kingPosition = piece.square;
                }
            }
        }

        for (Piece piece : oppPieces) {
            if (piece.isValidMove(piece.square, kingPosition, this)) {
                // System.out.println("\ncheck\tattacking piece: " + piece + "\tKing Position: "
                // + kingPosition);
                return true;
            }
        }

        return false;
    }

    public boolean inCheckmate(boolean isWhite) {
        // check if king is in check
        if (!inCheck(isWhite)) {
            return false;
        }

        System.out.println("cleared1");

        HashSet<Piece> oppPieces = null;
        HashSet<Piece> ownPieces = null;
        Square kingPosition = null;

        // assign opponent & own pieces and king position
        if (isWhite) {
            oppPieces = blackPieces;
            ownPieces = whitePieces;
            for (Piece piece : whitePieces) {
                if (piece instanceof King) {
                    kingPosition = piece.square;
                }
            }
        } else {
            oppPieces = whitePieces;
            ownPieces = blackPieces;
            for (Piece piece : blackPieces) {
                if (piece instanceof King) {
                    kingPosition = piece.square;
                }
            }
        }

        // check if king has valid squares (wip)
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (kingPosition.rowpos + i > -1 && kingPosition.rowpos + i < 8 && kingPosition.colpos + j > -1
                        && kingPosition.colpos + j < 8) {
                    Square end = board[kingPosition.rowpos + i][kingPosition.colpos + j];
                    if (kingPosition.piece.isValidMove(kingPosition, end, this)) {
                        System.out.println("King Position: " + kingPosition + "\tPossible Square: " + end);
                        return false;
                    }
                }
            }
        }

        System.out.println("cleared2");

        // need to add option for pieces to block
        // introduce a set of all pieces checking the king
        // iterate through the set and for each piece, develop another set of all
        // squares in between (implemented in each Piece subclass)
        // return whether the set is not empty, signifying that a piece cannot be
        // blocked

        HashSet<Piece> attackPieces = new HashSet<>();

        for (Piece piece : oppPieces) {
            if (piece.isValidMove(piece.square, kingPosition, this)) {
                attackPieces.add(piece);
            }
        }

        // check if block attacking piece
        for (Piece attackPiece : attackPieces) {
            HashSet<Square> squaresBetween = attackPiece.squaresBetween(attackPiece.square, kingPosition, this);
            for (Square square : squaresBetween) {
                for (Piece ownPiece : ownPieces) {
                    if (ownPiece.isValidMove(ownPiece.square, square, this)) {
                        return false;
                    }
                }
            }
        }

        // check if capture attacking piece
        for (Piece attackPiece : attackPieces) {
            for (Piece ownPiece : ownPieces) {
                if (ownPiece.isValidMove(ownPiece.square, attackPiece.square, this)) {
                    return false;
                }
            }
        }

        return true;
    }
}
