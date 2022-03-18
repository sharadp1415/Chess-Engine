package pieces;

import java.util.HashSet;

import chess.Board;
import chess.Square;

public class Rook extends Piece {

    public Rook(boolean isWhite, Square square) {
        super(isWhite, square);
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

    public HashSet<Square> squaresBetween(Square start, Square end, Board b) {
        HashSet<Square> squares = new HashSet<>();

        if (start.rowpos - end.rowpos == 0) {
            for (int i = 1; i < Math.abs(start.colpos - end.colpos); i++) {
                if (start.colpos < end.colpos) {
                    squares.add(b.board[start.rowpos][start.colpos + i]);
                } else {
                    squares.add(b.board[start.rowpos][start.colpos - i]);
                }
            }
        } else {
            for (int i = 1; i < Math.abs(start.rowpos - end.rowpos); i++) {
                if (start.rowpos < end.rowpos) {
                    squares.add(b.board[start.rowpos + i][start.colpos]);
                } else {
                    squares.add(b.board[start.rowpos - i][start.colpos]);
                }
            }
        }

        return squares;
    }

    public String toString() {
        return isWhite ? "wR " : "bR ";
    }
}
