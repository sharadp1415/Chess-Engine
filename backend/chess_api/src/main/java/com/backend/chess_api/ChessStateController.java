package com.backend.chess_api;

import com.backend.chess_api.chess.Chess;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChessStateController {

    Chess gameInstance = null;

    // @GetMapping("/test")
    // public ChessState chessState(@RequestParam(value = "name", defaultValue =
    // "World") String name) {
    // return new ChessState(name);
    // }

    @GetMapping("/new-game")
    public ChessState newGameState() {
        gameInstance = new Chess();
        return new ChessState(gameInstance);
    }

    @PostMapping("perform-move")
    public ChessState performMove(@RequestBody String move) {

        return new ChessState(gameInstance);
    }
}
