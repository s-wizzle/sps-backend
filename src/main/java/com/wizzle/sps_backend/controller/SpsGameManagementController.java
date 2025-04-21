package com.wizzle.sps_backend.controller;

import com.wizzle.sps_backend.model.SpsGame;
import com.wizzle.sps_backend.service.SpsGameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sps-games")
@CrossOrigin(origins = "http://localhost:4200")
public class SpsGameManagementController {

    SpsGameManagementService spsGameManagementService;

    @Autowired
    public SpsGameManagementController(SpsGameManagementService spsGameService) {
        this.spsGameManagementService = spsGameService;
    }

    @GetMapping("")
    public ResponseEntity<List<SpsGame>> getAllSpsGames() {
        return ResponseEntity.ok(spsGameManagementService.findAllGames());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpsGame> getSpsGameById(@PathVariable Long id) {
        return ResponseEntity.ok(spsGameManagementService.findGameById(id));
    }

    @PostMapping
    public ResponseEntity<SpsGame> createSpsGame() {
        SpsGame savedGame = spsGameManagementService.createGame();
        return ResponseEntity.status(201).body(savedGame);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SpsGame> updateSpsGame(@PathVariable Long id, @RequestBody SpsGame spsGame) {
        return ResponseEntity.ok(spsGameManagementService.updateGame(id, spsGame));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSpsGame(@PathVariable Long id) {
        spsGameManagementService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
