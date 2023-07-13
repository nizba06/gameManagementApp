package com.example.gamemanagement.repository;

import ch.qos.logback.core.recovery.ResilientSyslogOutputStream;
import com.example.gamemanagement.model.Game;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class GameRepository {

    private Map<String, Game> gameMap;
    public GameRepository(){
        gameMap = new ConcurrentHashMap<>();
    }

    public void createGame(Game game) {
        gameMap.put(game.getName(), game);
    }

    public void deleteGame(String name) {
        gameMap.remove(name);
    }

    public Game getGameByName(String name) {
        return gameMap.get(name);
    }

    public List<Game> getAllGames() {
        return gameMap.values().stream().collect(Collectors.toList());
    }
}
