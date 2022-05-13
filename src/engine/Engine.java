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

    public static void main(String[] args) {

    }
}
