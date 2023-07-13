package com.example.gamemanagement.service;

import com.example.gamemanagement.model.Game;
import com.example.gamemanagement.repository.GameRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GameService implements IGameService {

    private final GameRepository gameRepository;

    GameService(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @CachePut(value = "gameCache", key = "#game.name")
    @Override
    public void saveGame(Game game) {
        log.info("Saving game " +game.getName());
        gameRepository.createGame(game);
    }

    @CacheEvict("gameCache")
    @Override
    public void deleteGame(String name) {
        log.info("Deleting game " +name);
        gameRepository.deleteGame(name);
    }

    @Override
    public Game getGameByName(String name) {
        log.info("Getting game " + name);
        return gameRepository.getGameByName(name);
    }

    @Override
    public List<Game> getAllGames() {
        log.info("Getting all the games");
        return gameRepository.getAllGames();
    }
}
