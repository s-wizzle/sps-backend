package com.wizzle.sps_backend.controller;

import com.wizzle.sps_backend.model.SpsGame;
import com.wizzle.sps_backend.service.SpsGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sps-game")
public class SpsGameController {

    SpsGameService spsGameService;

    @Autowired
    public SpsGameController(SpsGameService spsGameService) {
        this.spsGameService = spsGameService;
    }

    @GetMapping
    public String getNpcChoice() {
        return spsGameService.getNpcChoice();
    }

    @GetMapping("/game/{spsGameId}")
    public ResponseEntity<SpsGame> getSpsGameById(@PathVariable Long spsGameId) {
        return ResponseEntity.ok(spsGameService.getSpsGameById(spsGameId));
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
