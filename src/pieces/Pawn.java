package pieces;

import chess.Board;
import chess.Square;

public class Pawn extends Piece {

    boolean moved = false;

    public Pawn(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board board) {

        if (isWhite) {
            if (end.xpos - start.xpos == -2) {
                if (moved) {
                    System.out.println("bruh1");
                    return false;
                }
            }
            if (end.xpos - start.xpos == -1 && Math.abs(end.ypos - start.ypos) == 1) {
                if (end.piece == null || end.piece.isWhite) {
                    System.out.println("bruh2");
                    return false;
                }
            }
            if (end.xpos - start.xpos != -1 && end.xpos - start.xpos != -2) {
                System.out.println("bruh3: " + (end.xpos) + " " + start.xpos);
                return false;
            }
        } else {
            if (end.xpos - start.xpos == 2) {
                if (moved) {
                    return false;
                }
            }
            if (end.xpos - start.xpos == 1 && Math.abs(end.ypos - start.ypos) == 1) {
                if (end.piece == null || end.piece.isWhite) {
                    return false;
                }
            }
            if (end.xpos - start.xpos != 1 && end.xpos - start.xpos != 2) {
                return false;
            }
        }

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