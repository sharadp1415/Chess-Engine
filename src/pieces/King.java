package pieces;

import java.util.HashSet;

import chess.Board;
import chess.Square;

/**
 * @author Naman Bajaj, Sharad Prasad
 */

public class King extends Piece {
    boolean isFirstMove;

    public King(boolean isWhite, Square square) {
        super(isWhite, square);
        isFirstMove = true;
    }

    public boolean isValidMove(Square start, Square end, Board b) {
        if (start.equals(end)) {
            return false;
        }

        // castling attempt found
        if (Math.abs(start.colpos - end.colpos) == 2 && Math.abs(start.rowpos - end.rowpos) == 0) {
            // king has been moved before
            if (!this.isFirstMove)
                return false;

            // white castling
            if (this.isWhite) {
                // castling king side (right)
                if ((start.colpos - end.colpos) == -2) {
                    Piece p = b.board[7][0].piece;
                    // piece at end is not rook
                    if (p == null || !(p instanceof Rook))
                        return false;

                    // rook being castled has moved before
                    Rook r = (Rook) p;
                    if (!r.isFirstMove)
                        return false;
                    
                    return true;
                    
                    
                }

                // castling queen side (left)
                // if((start.colpos - end.colpos) == 2)
            }

            // black castling
            else {
                // castling king side (right)

                // castling queen side (left)
            }

            // pieces in between king and rook

            return true;
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

        this.isFirstMove = false;
        return true;
    }

    public HashSet<Square> squaresBetween(Square start, Square end, Board b) {
        return new HashSet<>();
    }

    public String toString() {
        return isWhite ? "wK " : "bK ";
    }
}
