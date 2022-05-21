package com.backend.chess_api;

import com.backend.chess_api.chess.Chess;

public class ChessState {
    // private final String test;

    private String[][] board;
    private boolean isCheckmate;
    private boolean isCheck;
    private boolean isWhiteTurn;

    public ChessState(Chess game) {
        this.isWhiteTurn = game.isWhiteTurn;
        this.board = game.board.boardString();
        this.isCheckmate = game.isCheckmate;
        this.isCheck = game.isCheck;
    }

    public String[][] getBoard() {
        return board;
    }

    public boolean getIsCheckmate() {
        return isCheckmate;
    }

    public boolean getIsCheck() {
        return isCheck;
    }

    public boolean getIsWhiteTurn() {
        return isWhiteTurn;
    }
}
