package com.wizzle.sps_backend.controller;

import com.wizzle.sps_backend.model.SpsGame;
import com.wizzle.sps_backend.service.SpsGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sps-game")
@CrossOrigin(origins = "http://localhost:4200")
public class SpsGameController {

    SpsGameService spsGameService;

    @Autowired
    public SpsGameController(SpsGameService spsGameService) {
        this.spsGameService = spsGameService;
    }

    @GetMapping("/game/{spsGameId}")
    public ResponseEntity<SpsGame> getSpsGameById(@PathVariable Long spsGameId) {
        return ResponseEntity.ok(spsGameService.getSpsGameById(spsGameId));
    }

    @GetMapping("/game")
    public ResponseEntity<List<SpsGame>> getAllSpsGames() {
        return ResponseEntity.ok(spsGameService.getAllSpsGames());
    }

    @PostMapping
    public ResponseEntity<SpsGame> createSpsGame() {
        SpsGame savedGame = spsGameService.createSpsGame();
        return ResponseEntity.status(201).body(savedGame);
    }

    @PutMapping("/game/{spsGameId}")
    public ResponseEntity<SpsGame> updateSpsGame(@PathVariable Long spsGameId, @RequestBody SpsGame spsGame) {
        return ResponseEntity.ok(spsGameService.updateSpsGame(spsGameId, spsGame));
    }

    @DeleteMapping("/game/{spsGameId}")
    public ResponseEntity<?> deleteSpsGame(@PathVariable Long spsGameId) {
        spsGameService.deleteSpsGame(spsGameId);
        return ResponseEntity.noContent().build();
    }
}
