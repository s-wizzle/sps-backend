package com.wizzle.sps_backend.service;

import com.wizzle.sps_backend.model.GameMode;
import com.wizzle.sps_backend.model.SpsGame;

public interface GameService {
    String getNpcChoice(GameMode gameMode);
    SpsGame calculateResult(SpsGame game);
}
