package pieces;

import chess.Board;
import chess.Square;

public class Queen extends Piece {
    public Queen(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board b) {
        if (start.equals(end))
            return false;

        // check for valid move direction
        if (Math.abs(start.rowpos - end.rowpos) != Math.abs(start.colpos - end.colpos)
                && (start.rowpos - end.rowpos) * (start.colpos - end.colpos) != 0) {
            return false;
        }

        // check if any pieces are between start and end square
        if (start.rowpos - end.rowpos == 0 || start.colpos - end.colpos == 0) {
            if (start.rowpos - end.rowpos == 0) {
                for (int i = 1; i < Math.abs(start.colpos - end.colpos); i++) {
                    if (start.colpos < end.colpos) {
                        if (b.board[start.rowpos][start.colpos + i].piece != null)
                            return false;
                    } else {
                        if (b.board[start.rowpos][start.colpos - i].piece != null)
                            return false;
                    }
                }
            } else {
                for (int i = 1; i < Math.abs(start.rowpos - end.rowpos); i++) {
                    if (start.rowpos < end.rowpos) {
                        if (b.board[start.rowpos + i][start.colpos].piece != null)
                            return false;
                    } else {
                        if (b.board[start.rowpos - i][start.colpos].piece != null)
                            return false;
                    }
                }
            }
        } else {
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
        }

        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        return false;
    }

    public String toString() {
        return isWhite ? "wQ " : "bQ ";
    }
}
