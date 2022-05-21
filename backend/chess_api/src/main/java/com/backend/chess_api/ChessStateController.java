package com.backend.chess_api;

import com.backend.chess_api.chess.Chess;
import com.backend.chess_api.chess.Move;
import com.backend.chess_api.chess.Board;
import com.backend.chess_api.chess.Square;

import org.springframework.web.bind.annotation.CrossOrigin;
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

    @CrossOrigin()
    @GetMapping("/new-game")
    public ChessState newGameState() {
        gameInstance = new Chess();
        return new ChessState(gameInstance);
    }

    @CrossOrigin()
    @PostMapping("perform-move")
    public ChessState performMove(@RequestBody String input) {
        System.out.println("Move:" + input);
        gameInstance.processInput(input);
        gameInstance.resetHightlight();
        return new ChessState(gameInstance);
    }

    @CrossOrigin()
    @PostMapping("see-squares")
    public ChessState seeSquares(@RequestBody String input) {
        System.out.println("Square:" + input);
        gameInstance.seeSquares(input);
        return new ChessState(gameInstance);
    }
}
