package engine;

import java.util.HashSet;

import chess.Chess;
import pieces.Piece;

public class Engine {

    public static int evaluatePosition(Chess game) {
        int result = 0;

        HashSet<Piece> whitePieces = game.board.whitePieces;
        HashSet<Piece> blackPieces = game.board.blackPieces;

        for (Piece whitePiece : whitePieces) {
            result += whitePiece.pieceValue(game);
        }

        for (Piece blackPiece : blackPieces) {
            result -= blackPiece.pieceValue(game);
        }

        return result;
    }

    public static int minimax(Chess game, int depth, int alpha, int beta, boolean isWhite) {
        if (depth == 0 || game.board.inCheckmate(isWhite)) {
            return evaluatePosition(game);
        }

        if (isWhite) {
            int maxEval = Integer.MIN_VALUE;
            //for each position possible by applying every move {
                int eval = minimax(game, depth - 1, alpha, beta, false);
                maxEval = Math.max(maxEval, eval);
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    // break; (for loop)
                // }
                }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            //for each position possible by applying every move {
                int eval = minimax(game, depth - 1, alpha, beta, true);
                minEval = Math.min(minEval, eval);
                alpha = Math.min(beta, eval);
                if (beta <= alpha) {
                    // break; (for loop)
                // }
                }
            return minEval;
        }
    }

    public static void main(String[] args) {

    }
}
