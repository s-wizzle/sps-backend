package com.wizzle.sps_backend.controller;

import com.wizzle.sps_backend.model.GameMode;
import com.wizzle.sps_backend.model.SpsGame;
import com.wizzle.sps_backend.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
@CrossOrigin(origins = "http://localhost:4200")
public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/npc-choice")
    public String getNpcChoice(@RequestParam GameMode mode) {
        return gameService.getNpcChoice(mode);
    }

    @PatchMapping("/result")
    public ResponseEntity<SpsGame> updateResult(@RequestBody SpsGame game) {
        return ResponseEntity.ok(gameService.calculateResult(game));
    }
}
