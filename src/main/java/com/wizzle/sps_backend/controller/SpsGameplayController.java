package com.wizzle.sps_backend.controller;

import com.wizzle.sps_backend.model.GameMode;
import com.wizzle.sps_backend.model.SpsGame;
import com.wizzle.sps_backend.service.SpsGameplayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sps-gameplay")
@CrossOrigin(origins = "http://localhost:4200")
public class SpsGameplayController {
    private final SpsGameplayService gameService;

    public SpsGameplayController(SpsGameplayService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/npc/choice")
    public String getNpcChoice(@RequestParam GameMode mode) {
        return gameService.generateNpcChoice(mode);
    }

    @PatchMapping("/round/result")
    public ResponseEntity<SpsGame> updateResult(@RequestBody SpsGame game) {
        return ResponseEntity.ok(gameService.determineGameResult(game));
    }
}
