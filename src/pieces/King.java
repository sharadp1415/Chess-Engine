package pieces;

import java.util.HashSet;

import chess.Board;
import chess.Square;

public class King extends Piece {

    public King(boolean isWhite, Square square) {
        super(isWhite, square);
    }

    public boolean isValidMove(Square start, Square end, Board b) {
        if (start.equals(end)) {
            return false;
        }

        if (Math.abs(start.rowpos - end.rowpos) > 1 || Math.abs(start.colpos - end.colpos) > 1) {
            return false;
        }

        if (end.piece != null && (end.piece.isWhite == start.piece.isWhite)) {
            return false;
        }

        HashSet<Piece> oppPieces;

        if (start.piece.isWhite) {
            oppPieces = b.blackPieces;
        } else {
            oppPieces = b.whitePieces;
        }

        for (Piece piece : oppPieces) {
            Piece temp = end.piece;
            end.piece = null;
            if (piece.isValidMove(piece.square, end, b)) {
                end.piece = temp;
                return false;
            }
            end.piece = temp;
        }

        return true;
    }

    public HashSet<Square> squaresBetween(Square start, Square end, Board b) {
        return new HashSet<>();
    }

    public String toString() {
        return isWhite ? "wK " : "bK ";
    }
}
