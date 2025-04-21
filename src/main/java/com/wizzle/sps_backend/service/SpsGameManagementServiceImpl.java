package com.wizzle.sps_backend.service;

import com.wizzle.sps_backend.exception.SpsGameNotFoundException;
import com.wizzle.sps_backend.model.SpsGame;
import com.wizzle.sps_backend.repository.SpsGameRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Random;

@Service
public class SpsGameManagementServiceImpl implements SpsGameManagementService {

    private final SpsGameRepository spsGameRepository;

    public SpsGameManagementServiceImpl(SpsGameRepository spsGameRepository) {
        this.spsGameRepository = spsGameRepository;
    }

    @Override
    public List<SpsGame> findAllGames() {
        return spsGameRepository.findAll();
    }

    @Override
    public SpsGame findGameById(Long spsGameId) {
        return findGameOrThrow(spsGameId);
    }

    @Override
    @Transactional
    public SpsGame createGame() {
        var spsGame = new SpsGame();
        spsGame.setCreatedAt(Instant.now());
        return spsGameRepository.save(spsGame);
    }

    @Override
    @Transactional
    public SpsGame updateGame(Long spsGameId, SpsGame spsGame) {
        var existingSpsGame = findGameOrThrow(spsGameId);

        existingSpsGame.setPlayerName(spsGame.getPlayerName());
        existingSpsGame.setPlayerChoice(spsGame.getPlayerChoice());
        existingSpsGame.setMode(spsGame.getMode());
        existingSpsGame.setResult(spsGame.getResult());
        existingSpsGame.setNpcChoice(spsGame.getNpcChoice());
        existingSpsGame.setUpdatedAt(Instant.now());

        return spsGameRepository.save(existingSpsGame);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void deleteGame(Long spsGameId) {
        var existingSpsGame = findGameOrThrow(spsGameId);
        spsGameRepository.delete(existingSpsGame);
    }

    private SpsGame findGameOrThrow(Long spsGameId) {
        return spsGameRepository.findById(spsGameId)
                .orElseThrow(() -> new SpsGameNotFoundException("SpsGame not found with id: " + spsGameId));
    }
}
