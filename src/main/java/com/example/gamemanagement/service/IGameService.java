package com.example.gamemanagement.service;

import com.example.gamemanagement.model.Game;

import java.util.List;

public interface IGameService {

    void saveGame(Game game);
    void deleteGame(String name);
    Game getGameByName(String name);
    List<Game> getAllGames();
}
