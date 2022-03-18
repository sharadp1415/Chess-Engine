package pieces;

import chess.Board;
import chess.Square;

public class Pawn extends Piece {

    boolean moved = false;

    public Pawn(boolean isWhite, Square square) {
        super(isWhite, square);
    }

    public boolean isValidMove(Square start, Square end, Board b) {

        // check if pawn move is valid for capturing or advancing
        if (isWhite) {
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
                if (end.piece == null || end.piece.isWhite) {
                    return false;
                }
            }

            // check if one square advance is colliding w/pieces
            if (end.rowpos - start.rowpos == -1 && end.colpos - start.colpos == 0) {
                if (b.board[start.rowpos - 1][start.colpos].piece != null) {
                    return false;
                }
            }

            if (end.rowpos - start.rowpos != -1 && end.rowpos - start.rowpos != -2) {
                // System.out.println("error: " + (end.xpos) + " " + start.xpos);
                return false;
            }
        } else { // same for black
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
                if (end.piece == null || !end.piece.isWhite) {
                    return false;
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

        moved = true;
        return true;
    }

    public String toString() {
        return isWhite ? "wp " : "bp ";
    }
}