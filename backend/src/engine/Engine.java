package engine;

import java.util.HashSet;

import chess.Chess;
import chess.Move;
import chess.pieces.Piece;

public class Engine {

    // private static Move bestMove = null;

    public static int evaluatePosition(Chess game) {
        if (game.board.inCheckmate(true)) {
            // System.out.println("Line Found with White King in Checkmate");
            return Integer.MIN_VALUE;
        }

        if (game.board.inCheckmate(false)) {
            return Integer.MAX_VALUE;
        }

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
            // int test = evaluatePosition(game);
            // // System.out.println(test);
            // if (test == Integer.MIN_VALUE) {
            // System.out.println("\nTHIS IS THE IDEAL BOARD");
            // game.board.printBoard();
            // }
            // return test;
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

    public static Move bestMove(Chess game, boolean isWhiteTurn) {
        Move bestMove = null;
        // depth 4 takes a long time
        int depth = 3;

        int minmax = 0;

        if (isWhiteTurn) {
            minmax = Integer.MIN_VALUE;
        } else {
            minmax = Integer.MAX_VALUE;
        }

        for (Move potentialMove : game.generateAllLegalMoves(isWhiteTurn)) {
            game.performMove(potentialMove);
            // test with iswhiteturn and !iswhiteturn (DONE)
            int eval = minimax(game, depth, Integer.MIN_VALUE, Integer.MAX_VALUE, !isWhiteTurn);
            if (!isWhiteTurn && eval < minmax) {
                minmax = eval;
                bestMove = potentialMove;
            }

            if (isWhiteTurn && eval > minmax) {
                minmax = eval;
                bestMove = potentialMove;
            }

            game.revertMove(potentialMove);
        }

        System.out.println("Best Eval: " + minmax);
        System.out.println("Best Move: " + bestMove);
        return bestMove;
    }

    public static void main(String[] args) {

    }
}
