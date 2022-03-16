package pieces;

import chess.Board;
import chess.Square;

public class Pawn extends Piece {

    boolean moved = false;

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board b) {

        // check if pawn move is valid for capturing or advancing
        if (isWhite) {
            // check for first move if moved two spots
            if (end.xpos - start.xpos == -2) {
                if (moved) {
                    return false;
                }
                if (b.board[start.xpos - 1][start.ypos].piece != null
                        || b.board[start.xpos - 2][start.ypos].piece != null) {
                    return false;
                }
            }
            // check for diagonal move
            if (end.xpos - start.xpos == -1 && Math.abs(end.ypos - start.ypos) == 1) {
                // check if opposing piece is preset
                if (end.piece == null || end.piece.isWhite) {
                    return false;
                }
            }

            // check if one square advance is colliding w/pieces
            if (end.xpos - start.xpos == -1 && end.ypos - start.ypos == 0) {
                if (b.board[start.xpos - 1][start.ypos].piece != null) {
                    return false;
                }
            }

            if (end.xpos - start.xpos != -1 && end.xpos - start.xpos != -2) {
                // System.out.println("error: " + (end.xpos) + " " + start.xpos);
                return false;
            }
        } else { // same for black
            if (end.xpos - start.xpos == 2) {
                if (moved) {
                    return false;
                }

                if (b.board[start.xpos + 1][start.ypos].piece != null
                        || b.board[start.xpos + 2][start.ypos].piece != null) {
                    return false;
                }
            }
            if (end.xpos - start.xpos == 1 && Math.abs(end.ypos - start.ypos) == 1) {
                if (end.piece == null || !end.piece.isWhite) {
                    return false;
                }
            }

            if (end.xpos - start.xpos == 1 && end.ypos - start.ypos == 0) {
                if (b.board[start.xpos + 1][start.ypos].piece != null) {
                    return false;
                }
            }

            if (end.xpos - start.xpos != 1 && end.xpos - start.xpos != 2) {
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