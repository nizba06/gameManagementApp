package com.example.gamemanagement.controller;

import com.example.gamemanagement.model.Game;
import com.example.gamemanagement.service.IGameService;
import com.example.gamemanagement.service.GameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService){
        this.gameService = gameService;
    }

    //Save and update
    @PostMapping
    public String add(@RequestBody Game game){
        gameService.saveGame(game);
        return "Game saved successfully!";
    }

    @DeleteMapping("/{name}")
    public String delete(@PathVariable String name){
        gameService.deleteGame(name);
        return "Game deleted successfully";
    }

    @GetMapping("/{name}")
    public Game findByName(@PathVariable String name){
        return gameService.getGameByName(name);
    }

    @GetMapping()
    public List<Game> getAll(){
        return gameService.getAllGames();
    }

}
