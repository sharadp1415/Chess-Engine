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

        if (Math.abs(start.rowpos - end.rowpos) != Math.abs(start.colpos - end.colpos)) {
            return false;
        }

        // check if any pieces are in between start and end square
        if (start.rowpos < end.rowpos) {
            if (start.colpos < end.colpos) {
                // down and right
                for (int i = 1; i < Math.abs(end.rowpos - start.rowpos); i++) {
                    if (b.board[start.rowpos + i][start.colpos + i].piece != null)
                        return false;
                }
            } else {
                // down and left
                for (int i = 1; i < Math.abs(end.rowpos - start.rowpos); i++) {
                    if (b.board[start.rowpos + i][start.colpos - i].piece != null)
                        return false;
                }
            }
        } else {
            if (start.colpos < end.colpos) {
                // up and right
                for (int i = 1; i < Math.abs(end.rowpos - start.rowpos); i++) {
                    if (b.board[start.rowpos - i][start.colpos + i].piece != null)
                        return false;
                }
            } else {
                // up and left
                for (int i = 1; i < Math.abs(end.rowpos - start.rowpos); i++) {
                    if (b.board[start.rowpos - i][start.colpos - i].piece != null)
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