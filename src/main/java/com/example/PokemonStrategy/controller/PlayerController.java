package com.example.PokemonStrategy.controller;

import com.example.PokemonStrategy.core.player.Player;
import com.example.PokemonStrategy.core.pokemon.Pokemon;
import com.example.PokemonStrategy.service.PlayerService;
import com.example.PokemonStrategy.service.PokemonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/player")
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Tag(name = "Player API")
public class PlayerController {
    private final PlayerService playerService;
    private final PokemonService pokemonService;

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerInfo(@PathVariable(name = "id", required = false) UUID id){
        // Use the id variable to retrieve the player with the specified ID
        Optional<Player> playerOptional = playerService.findPlayer(id);
        Player player = playerOptional.orElse(null);

        if (player == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(player);
    }

    @Operation(summary = "All players")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully"),
            @ApiResponse(responseCode = "403", description = "Don't have permission to do this"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @GetMapping("/all")
    public ResponseEntity<List<Player>> findPlayers(){
        return ResponseEntity.ok(playerService.findPlayers());
    }

    @Operation(summary = "Search for player", description = "Using name to search for player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully searched"),
            @ApiResponse(responseCode = "403", description = "Don't have permission to do this"),
            @ApiResponse(responseCode = "404", description = "Not found - the player was not found")
    })
    @GetMapping("/search")
    public ResponseEntity<Player> getPlayerInfoByName(@RequestParam("name") String name) {
        Optional<Player> playerOptional = playerService.searchPlayer(name);
        Player player = playerOptional.orElse(null);

        if (player == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(player);
    }

    @Operation(summary = "Player choose Pokemon", description = "Using player id and Pokemon id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added"),
            @ApiResponse(responseCode = "403", description = "Don't have permission to do this"),
            @ApiResponse(responseCode = "404", description = "The player or the Pokemon was not found")
    })
    @PostMapping("/catchPokemon/{id}")
    public ResponseEntity<Player> addPokemon(
            @PathVariable(name = "id", required = false) UUID playerId,
            @RequestParam("poke") UUID pokeId
    ){
        Optional<Player> playerOptional = playerService.findPlayer(playerId);
        Player player = playerOptional.orElse(null);

        if (player == null) {
            return ResponseEntity.notFound().build();
        } else {
            Optional<Pokemon> pokemonOptional = pokemonService.findPokemon(pokeId);
            Pokemon pokemon = pokemonOptional.orElse(null);

            playerService.addPokemon(player, pokemon);
        }

        return ResponseEntity.ok().body(player);
    }

    @Operation(summary = "Add new player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully added"),
            @ApiResponse(responseCode = "403", description = "Don't have permission to do this"),
            @ApiResponse(responseCode = "404", description = "Something went wrong")
    })
    @PostMapping("/add")
    public ResponseEntity<Player> addNewPlayer(@RequestBody Player player) {
        Player newPlayer = playerService.addPlayer(player);
        if (newPlayer != null){
            return ResponseEntity.ok().body(player);
        } else {
            return ResponseEntity.badRequest().body(null);
        }

    }

}
