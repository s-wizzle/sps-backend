package com.wizzle.sps_backend.service;

import com.wizzle.sps_backend.model.GameMode;
import com.wizzle.sps_backend.model.SpsGame;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpsGameplayServiceImpl implements SpsGameplayService {
    private final Random random;
    private final Map<GameMode, List<String>> choicesMap;
    private final Map<GameMode, Map<String, Set<String>>> winMaps;

    public SpsGameplayServiceImpl(Random random) {
        this.random = random;
        this.choicesMap = initializeChoicesMap();
        this.winMaps = initializeWinMaps();
    }

    @Override
    public String generateNpcChoice(GameMode mode) {
        List<String> choices = choicesMap.getOrDefault(mode, List.of());
        return choices.get(random.nextInt(choices.size()));
    }

    @Override
    public SpsGame determineGameResult(SpsGame game) {
        validateGame(game);

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
        Map<String, Set<String>> winMap = winMaps.getOrDefault(mode, Map.of());
        Set<String> beats = winMap.getOrDefault(player, Collections.emptySet());
        return beats.contains(npc);
    }

    private Map<GameMode, List<String>> initializeChoicesMap() {
        return Map.of(
                GameMode.DEFAULT, List.of("stone", "paper", "scissors"),
                GameMode.HARD, List.of("stone", "paper", "scissors", "lizard", "spock"),
                GameMode.EXPERT, List.of("stone", "paper", "scissors", "lizard", "spock", "fire", "water")
        );
    }

    private Map<GameMode, Map<String, Set<String>>> initializeWinMaps() {
        return Map.of(
                GameMode.DEFAULT, Map.of(
                        "stone", Set.of("scissors"),
                        "paper", Set.of("stone"),
                        "scissors", Set.of("paper")
                ),
                GameMode.HARD, Map.of(
                        "stone", Set.of("scissors", "spock"),
                        "paper", Set.of("stone", "lizard"),
                        "scissors", Set.of("paper", "spock"),
                        "lizard", Set.of("stone", "scissors"),
                        "spock", Set.of("paper", "lizard")
                ),
                GameMode.EXPERT, Map.of(
                        "stone", Set.of("scissors", "spock", "fire"),
                        "paper", Set.of("stone", "lizard", "water"),
                        "scissors", Set.of("paper", "spock", "fire"),
                        "lizard", Set.of("stone", "scissors", "spock"),
                        "spock", Set.of("paper", "lizard", "fire"),
                        "fire", Set.of("water", "lizard"),
                        "water", Set.of("stone", "spock")
                )
        );
    }

    private void validateGame(SpsGame game) {
        if (game == null || game.getPlayerChoice() == null || game.getNpcChoice() == null || game.getMode() == null) {
            throw new IllegalArgumentException("Invalid game object or missing fields");
        }
    }

    /*public String generateNpcChoice(GameMode mode) {
        List<String> choices = switch (mode) {
            case DEFAULT -> List.of("stone", "paper", "scissors");
            case HARD -> List.of("stone", "paper", "scissors", "lizard", "spock");
            case EXPERT -> List.of("stone", "paper", "scissors", "lizard", "spock", "fire", "water");
        };

        return choices.get(random.nextInt(choices.size()));
    }

    public SpsGame determineGameResult(SpsGame game) {
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
    }*/
}
