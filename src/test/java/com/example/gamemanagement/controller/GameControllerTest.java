package com.example.gamemanagement.controller;

import com.example.gamemanagement.model.Game;
import com.example.gamemanagement.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class GameControllerTest {
    @Mock
    private GameService gameService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(new GameController(gameService)).build();
    }

    @Test
    public void testCreateGame() throws Exception {
        Game game = new Game();
        game.setName("abc");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        game.setCreateDate(LocalDate.parse("13-07-2023".toString(), formatter));
        game.setActive("Yes");

        mockMvc.perform(post("/games")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"abc\",\"createDate\":\"13-07-2023\",\"active\":\"Yes\"}"))
                        .andExpect(status().isOk());

        verify(gameService, times(1)).saveGame(game);
    }

    @Test
    public void testUpdateGame() throws Exception {
        Game game = new Game();
        game.setName("abc");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        game.setCreateDate(LocalDate.parse("13-07-2023", formatter));
        game.setActive("No");

        mockMvc.perform(post("/games/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"abc\",\"createDate\":\"13-07-2023\",\"active\":\"No\"}"))
                        .andExpect(status().isOk());

        verify(gameService, times(1)).saveGame(game);
    }

    @Test
    public void testDeleteGame() throws Exception {
        mockMvc.perform(delete("/games/abc"))
                .andExpect(status().isOk());

        verify(gameService, times(1)).deleteGame("abc");
    }

    @Test
    public void testGetGame() throws Exception {
        Game game = new Game();
        game.setName("abc");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        game.setCreateDate(LocalDate.parse("13-07-2023", formatter));
        game.setActive("Yes");

        when(gameService.getGameByName("abc")).thenReturn(game);

        mockMvc.perform(get("/games/abc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("abc"))
                .andExpect(jsonPath("$.createDate").value("13-07-2023"))
                .andExpect(jsonPath("$.active").value("Yes"));

        verify(gameService, times(1)).getGameByName("abc");
    }

    @Test
    public void testGetAllGames() throws Exception {
        Game game1 = new Game();
        game1.setName("abc");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        game1.setCreateDate(LocalDate.parse("13-07-2023", formatter));
        game1.setActive("Yes");

        Game game2 = new Game();
        game2.setName("xyz");
        game2.setCreateDate(LocalDate.parse("13-07-2023", formatter));
        game2.setActive("No");

        List<Game> games = Arrays.asList(game1, game2);

        when(gameService.getAllGames()).thenReturn(games);

        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("abc"))
                .andExpect(jsonPath("$[0].createDate").value("13-07-2023"))
                .andExpect(jsonPath("$[0].active").value("Yes"))
                .andExpect(jsonPath("$[1].name").value("xyz"))
                .andExpect(jsonPath("$[1].createDate").value("13-07-2023"))
                .andExpect(jsonPath("$[1].active").value("No"));

        verify(gameService, times(1)).getAllGames();
    }
}
