package com.wizzle.sps_backend.service;

import com.wizzle.sps_backend.model.SpsGame;

import java.util.List;

public interface SpsGameService {
    List<SpsGame> getAllSpsGames();
    SpsGame getSpsGameById(Long spsGameId);
    SpsGame createSpsGame();
    SpsGame updateSpsGame(Long spsGameId, SpsGame spsGame);
    void deleteSpsGame(Long spsGameId);
}
