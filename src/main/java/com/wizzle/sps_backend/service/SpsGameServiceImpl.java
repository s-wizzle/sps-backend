package com.wizzle.sps_backend.service;

import com.wizzle.sps_backend.model.SpsGame;
import com.wizzle.sps_backend.repository.SpsGameRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;

@Service
public class SpsGameServiceImpl implements SpsGameService {

    SpsGameRepository spsGameRepository;

    private static final String[] CHOICES = {"rock", "paper", "scissors"};
    private final Random random = new Random();

    public SpsGameServiceImpl(SpsGameRepository spsGameRepository) {
        this.spsGameRepository = spsGameRepository;
    }

    @Override
    public String getNpcChoice() {
        int index = random.nextInt(CHOICES.length);
        return CHOICES[index];
    }

    @Override
    public SpsGame getSpsGameById(Long spsGameId) {
        var spsGame = spsGameRepository.findById(spsGameId);
        if (spsGame.isPresent()) {
            return spsGame.get();
        } else {
            throw new RuntimeException("SpsGame not found with id: " + spsGameId);
        }
    }

    @Override
    public SpsGame createSpsGame() {
        try {
            var spsGame = new SpsGame();
            spsGame.setCreatedAt(Instant.now());

            return spsGameRepository.save(spsGame);
        } catch (Exception e) {
            throw new RuntimeException("Error creating SpsGame: " + e.getMessage());
        }
    }

    @Override
    public SpsGame updateSpsGame(Long spsGameId, SpsGame spsGame) {
        if (!spsGameRepository.existsById(spsGameId)) {
            throw new RuntimeException("SpsGame not found with id: " + spsGameId);
        }

        var existingSpsGame = spsGameRepository.findById(spsGameId).orElseThrow(() -> new RuntimeException("SpsGame not found!"));

        existingSpsGame.setPlayerName(spsGame.getPlayerName());
        existingSpsGame.setPlayerChoice(spsGame.getPlayerChoice());
        existingSpsGame.setResult(spsGame.getResult());
        existingSpsGame.setNpcChoice(spsGame.getNpcChoice());
        existingSpsGame.setUpdatedAt(spsGame.getUpdatedAt());

        try {
            return spsGameRepository.save(existingSpsGame);
        } catch (Exception e) {
            throw new RuntimeException("Error updating SpsGame: " + e.getMessage());
        }
    }

    @Override
    public void deleteSpsGame(Long spsGameId) {
        if (!spsGameRepository.existsById(spsGameId)) {
            throw new RuntimeException("SpsGame not found with id: " + spsGameId);
        }

        try {
            spsGameRepository.deleteById(spsGameId);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting SpsGame: " + e.getMessage());
        }
    }
}
