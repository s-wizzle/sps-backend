package com.wizzle.sps_backend.service;

import com.wizzle.sps_backend.model.GameMode;
import com.wizzle.sps_backend.model.SpsGame;

public interface SpsGameplayService {
    String generateNpcChoice(GameMode gameMode);
    SpsGame determineGameResult(SpsGame game);
}
