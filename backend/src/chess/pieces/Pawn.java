package chess.pieces;

import java.util.HashSet;
import java.util.Stack;

import chess.Board;
import chess.Chess;
import chess.Move;
import chess.Square;

/**
 * @author Naman Bajaj, Sharad Prasad
 */

public class Pawn extends Piece {

    /**
     * Determines if Pawn has just moved, used for en passant
     */
    public boolean justMoved = false;

    /**
     * 2-arg constructor for Pawn class
     * 
     * @param isWhite If piece is white or black
     * @param square  Square piece resides on
     */
    public Pawn(boolean isWhite, Square square) {
        super(isWhite, square);
        if (isWhite) {
            valueTable = new int[][] {
                    { 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 50, 50, 50, 50, 50, 50, 50, 50 },
                    { 10, 10, 20, 30, 30, 20, 10, 10 },
                    { 5, 5, 10, 25, 25, 10, 5, 5 },
                    { 0, 0, 0, 20, 20, 0, 0, 0 },
                    { 5, -5, -10, 0, 0, -10, -5, 5 },
                    { 5, 10, 10, -20, -20, 10, 10, 5 },
                    { 0, 0, 0, 0, 0, 0, 0, 0 } };
        } else {
            valueTable = new int[][] {
                    { 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 5, 10, 10, -20, -20, 10, 10, 5 },
                    { 5, -5, -10, 0, 0, -10, -5, 5 },
                    { 0, 0, 0, 20, 20, 0, 0, 0 },
                    { 5, 5, 10, 25, 25, 10, 5, 5 },
                    { 10, 10, 20, 30, 30, 20, 10, 10 },
                    { 50, 50, 50, 50, 50, 50, 50, 50 },
                    { 0, 0, 0, 0, 0, 0, 0, 0 } };
        }
    }

    public boolean isValidMove(Square start, Square end, Chess game) {

        Board b = game.board;
        Stack<Move> moveStack = game.moveStack;

        // check if pawn move is valid for capturing or advancing
        if (isWhite) {
            // check for impossible move
            if (!((end.rowpos - start.rowpos == -2 && start.colpos - end.colpos == 0)
                    || (end.rowpos - start.rowpos == -1 && start.colpos - end.colpos == 0)
                    || (end.rowpos - start.rowpos == -1 && Math.abs(end.colpos - start.colpos) == 1))) {
                return false;
            }

            // check for first move if moved two spots
            if (end.rowpos - start.rowpos == -2) {
                if (moved) {
                    return false;
                }
                if (b.board[start.rowpos - 1][start.colpos].piece != null
                        || b.board[start.rowpos - 2][start.colpos].piece != null) {
                    return false;
                }
            }
            // check for diagonal move
            if (end.rowpos - start.rowpos == -1 && Math.abs(end.colpos - start.colpos) == 1) {
                // check if opposing piece is preset
                if (end.piece != null && end.piece.isWhite) {
                    return false;
                }

                if (end.piece == null) {
                    // check for en passant

                    Piece potentialPiece = b.board[start.rowpos][end.colpos].piece;

                    if (potentialPiece == null || !(potentialPiece instanceof Pawn)
                            || moveStack.isEmpty()
                            || moveStack.peek().movingPiece != potentialPiece
                            || ((Pawn) potentialPiece).isWhite) {
                        // System.out.println("returning false " + (moveStack.peek().movingPiece ==
                        // potentialPiece));
                        return false;
                    }
                }
            }

            // check if one square advance is colliding w/pieces
            if (end.rowpos - start.rowpos == -1 && end.colpos - start.colpos == 0) {
                if (b.board[start.rowpos - 1][start.colpos].piece != null) {
                    return false;
                }
            }

            if (end.rowpos - start.rowpos != -1 && end.rowpos - start.rowpos != -2) {
                return false;
            }
        } else { // same for black
            // check for impossible move
            if (!((end.rowpos - start.rowpos == 2 && start.colpos - end.colpos == 0)
                    || (end.rowpos - start.rowpos == 1 && start.colpos - end.colpos == 0)
                    || (end.rowpos - start.rowpos == 1 && Math.abs(end.colpos - start.colpos) == 1))) {
                return false;
            }

            if (end.rowpos - start.rowpos == 2) {
                if (moved) {
                    return false;
                }

                if (b.board[start.rowpos + 1][start.colpos].piece != null
                        || b.board[start.rowpos + 2][start.colpos].piece != null) {
                    return false;
                }
            }
            if (end.rowpos - start.rowpos == 1 && Math.abs(end.colpos - start.colpos) == 1) {
                if (end.piece != null && !end.piece.isWhite) {
                    return false;
                }

                if (end.piece == null) {
                    // check for en passant

                    Piece potentialPiece = b.board[start.rowpos][end.colpos].piece;
                    if (potentialPiece == null || !(potentialPiece instanceof Pawn)
                            || moveStack.isEmpty()
                            || moveStack.peek().movingPiece != potentialPiece
                            || !((Pawn) potentialPiece).isWhite) {
                        return false;
                    }
                }
            }

            if (end.rowpos - start.rowpos == 1 && end.colpos - start.colpos == 0) {
                if (b.board[start.rowpos + 1][start.colpos].piece != null) {
                    return false;
                }
            }

            if (end.rowpos - start.rowpos != 1 && end.rowpos - start.rowpos != 2) {
                return false;
            }
        }

        // checks if end square has a same color piece
        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        // check if piece is pinned (perform the move and check if the king is in check
        // and revert back)
        boolean output;
        Piece piece = start.piece;
        Move move = new Move(start, end, isWhite);
        game.performMove(move);
        // Piece capturedPiece = end.piece;
        // if (capturedPiece != null) {
        // if (capturedPiece.isWhite) {
        // b.whitePieces.remove(capturedPiece);
        // } else {
        // b.blackPieces.remove(capturedPiece);
        // }
        // }
        // start.piece = null;
        // end.piece = piece;
        // piece.square = end;
        // // capturedPiece.square = null;
        if (b.inCheck(piece.isWhite)) {
            output = false;
        } else {
            output = true;
        }

        // start.piece = piece;
        // piece.square = start;
        // end.piece = capturedPiece;
        // // capturedPiece.square = end;
        // if (capturedPiece != null) {
        // if (capturedPiece.isWhite) {
        // b.whitePieces.add(capturedPiece);
        // } else {
        // b.blackPieces.add(capturedPiece);
        // }
        // }

        game.revertMove(move);

        // if (output == true) {
        // moved = true;
        // }

        return output;
    }

    public HashSet<Square> squaresBetween(Square start, Square end, Board b) {
        HashSet<Square> squares = new HashSet<>();
        if (Math.abs(start.rowpos - end.rowpos) == 2) {
            squares.add(b.board[(start.rowpos + end.rowpos) / 2][start.colpos]);
        }
        return squares;
    }

    public int pieceValue(Chess game) {
        int bonus = 0;

        return 100 + valueTable[square.rowpos][square.colpos];
    }

    public String toString() {
        return isWhite ? "wp " : "bp ";
    }
}