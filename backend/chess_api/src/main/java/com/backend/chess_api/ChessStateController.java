package com.backend.chess_api;

import com.backend.chess_api.chess.Chess;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChessStateController {

    Chess gameInstance;

    @GetMapping("/test")
    public ChessState chessState(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new ChessState(name);
    }
}
