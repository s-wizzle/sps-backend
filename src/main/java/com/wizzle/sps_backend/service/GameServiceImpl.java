package com.wizzle.sps_backend.service;

import com.wizzle.sps_backend.model.GameMode;
import com.wizzle.sps_backend.model.SpsGame;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {
    private final Random random = new Random();

    public String getNpcChoice(GameMode mode) {
        List<String> choices = switch (mode) {
            case DEFAULT -> List.of("stone", "paper", "scissors");
            case HARD -> List.of("stone", "paper", "scissors", "lizard", "spock");
            case EXPERT -> List.of("stone", "paper", "scissors", "lizard", "spock", "fire", "water");
        };

        return choices.get(random.nextInt(choices.size()));
    }

    public SpsGame calculateResult(SpsGame game) {
        if (game.getPlayerChoice().equals(game.getNpcChoice())) {
            game.setResult("draw");
        } else if (doesPlayerWin(game.getPlayerChoice(), game.getNpcChoice(), game.getMode())) {
            game.setResult("win");
        } else {
            game.setResult("lose");
        }

        return game;
    }

    private boolean doesPlayerWin(String player, String npc, GameMode mode) {
        Map<String, Set<String>> winMap = switch (mode) {
            case DEFAULT -> getWinMapDefault();
            case HARD -> getWinMapHard();
            case EXPERT -> getWinMapExpert();
        };
        
        Set<String> beats = winMap.getOrDefault(player, Collections.emptySet());
        return beats.contains(npc);
    }
    
    private Map<String, Set<String>> getWinMapDefault() {
        return Map.of(
                "stone", Set.of("scissors"),
                "paper", Set.of("stone"),
                "scissors", Set.of("paper")
        );
    }
    
    private Map<String, Set<String>> getWinMapHard() {
        return Map.of(
                "stone", Set.of("scissors", "spock"),
                "paper", Set.of("stone", "lizard"),
                "scissors", Set.of("paper", "spock"),
                "lizard", Set.of("stone", "scissors"),
                "spock", Set.of("paper", "lizard")
        );
    }
    
    private Map<String, Set<String>> getWinMapExpert() {
        return Map.of(
                "stone", Set.of("scissors", "spock", "fire"),
                "paper", Set.of("stone", "lizard", "water"),
                "scissors", Set.of("paper", "spock", "fire"),
                "lizard", Set.of("stone", "scissors", "spock"),
                "spock", Set.of("paper", "lizard", "fire"),
                "fire", Set.of("water", "lizard"),
                "water", Set.of("stone", "spock")
        );
    }
}
