package engine;

import java.util.HashSet;

import chess.Chess;
import chess.Move;
import pieces.Piece;

public class Engine {

    // private static Move bestMove = null;

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
            // for each position possible by applying every move {
            for (Move potentialMove : game.generateAllLegalMoves(isWhite)) {
                game.performMove(potentialMove);
                int eval = minimax(game, depth - 1, alpha, beta, false);
                game.revertMove(potentialMove);
                maxEval = Math.max(maxEval, eval);
                // if (eval > maxEval) {
                // maxEval = eval;
                // bestMove = potentialMove;
                // }
                alpha = Math.max(alpha, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            // for each position possible by applying every move {
            for (Move potentialMove : game.generateAllLegalMoves(isWhite)) {
                game.performMove(potentialMove);
                int eval = minimax(game, depth - 1, alpha, beta, true);
                game.revertMove(potentialMove);
                minEval = Math.min(minEval, eval);
                alpha = Math.min(beta, eval);
                if (beta <= alpha) {
                    break;
                }
            }
            return minEval;
        }
    }

    public Move bestMove(Chess game, boolean isWhiteTurn) {
        Move bestMove = null;
        int max = Integer.MIN_VALUE;

        for (Move potentialMove : game.generateAllLegalMoves(isWhiteTurn)) {
            game.performMove(potentialMove);
            // test with iswhiteturn and !iswhiteturn
            int eval = minimax(game, 2, Integer.MIN_VALUE, Integer.MAX_VALUE, !isWhiteTurn);
            if (eval > max) {
                bestMove = potentialMove;
            }
            game.revertMove(potentialMove);
        }

        return bestMove;
    }

    public static void main(String[] args) {

    }
}
