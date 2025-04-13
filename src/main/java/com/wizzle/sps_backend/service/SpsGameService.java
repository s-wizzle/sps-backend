package com.wizzle.sps_backend.service;

import com.wizzle.sps_backend.model.SpsGame;

public interface SpsGameService {
    String getNpcChoice();
    SpsGame getSpsGameById(Long spsGameId);
    SpsGame createSpsGame();
    SpsGame updateSpsGame(Long spsGameId, SpsGame spsGame);
    void deleteSpsGame(Long spsGameId);
}
