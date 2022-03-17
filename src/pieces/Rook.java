package pieces;

import chess.Board;
import chess.Square;

public class Rook extends Piece {

    public Rook(boolean isWhite) {
        super(isWhite);
    }

    public boolean isValidMove(Square start, Square end, Board b) {
        if (start.equals(end))
            return false;

        // check if piece moved in straight line
        if ((start.rowpos - end.rowpos) * (start.colpos - end.colpos) != 0) {
            return false;
        }

        // check if move goes through pieces
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

        // check if piece lands on same color piece
        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        return true;
    }

    public String toString() {
        return isWhite ? "wR " : "bR ";
    }
}
