package com.wizzle.sps_backend.service;

import com.wizzle.sps_backend.model.SpsGame;

import java.util.List;

public interface SpsGameManagementService {
    List<SpsGame> findAllGames();
    SpsGame findGameById(Long spsGameId);
    SpsGame createGame();
    SpsGame updateGame(Long spsGameId, SpsGame spsGame);
    void deleteGame(Long spsGameId);
}
