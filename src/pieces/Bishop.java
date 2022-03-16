package pieces;

import chess.Board;
import chess.Square;

public class Bishop extends Piece {

    public Bishop(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board b) {
        if (start.equals(end))
            return false;

        if (Math.abs(start.xpos - end.xpos) != Math.abs(start.ypos - end.ypos)) {
            System.out.println("bruh");
            return false;
        }

        // check if any pieces are in between start and end square
        if (start.xpos < end.xpos) {
            if (start.ypos < end.ypos) {
                // down and right
                for (int i = 1; i < Math.abs(end.xpos - start.xpos); i++) {
                    if (b.board[start.xpos + i][start.ypos + i].piece != null)
                        return false;
                }
            } else {
                // down and left
                for (int i = 1; i < Math.abs(end.xpos - start.xpos); i++) {
                    if (b.board[start.xpos + i][start.ypos - i].piece != null)
                        return false;
                }
            }
        } else {
            if (start.ypos < end.ypos) {
                // up and right
                for (int i = 1; i < Math.abs(end.xpos - start.xpos); i++) {
                    if (b.board[start.xpos - i][start.ypos + i].piece != null)
                        return false;
                }
            } else {
                // up and left
                for (int i = 1; i < Math.abs(end.xpos - start.xpos); i++) {
                    if (b.board[start.xpos - i][start.ypos - i].piece != null)
                        return false;
                }
            }
        }

        // checks if end square has a same color piece
        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        return true;
    }

    public String toString() {
        return isWhite ? "wB " : "bB ";
    }
}