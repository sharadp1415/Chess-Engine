package engine;

import java.util.HashSet;

import chess.Chess;
import pieces.Piece;

public class Engine {

    public static int evaluatePosition(Chess game) {

        HashSet<Piece> whitePieces = game.board.whitePieces;
        HashSet<Piece> blackPieces = game.board.blackPieces;

        for (Piece whitePiece : whitePieces) {

        }

        return 0;
    }

    public static void main(String[] args) {

    }
}
