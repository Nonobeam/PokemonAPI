package com.example.PokemonStrategy.controller;

import com.example.PokemonStrategy.core.player.Player;
import com.example.PokemonStrategy.core.pokemon.Pokemon;
import com.example.PokemonStrategy.service.PlayerService;
import com.example.PokemonStrategy.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/player")
@RestController
@CrossOrigin
@RequiredArgsConstructor
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

    @GetMapping("/all")
    public ResponseEntity<List<Player>> findPlayers(){
        return ResponseEntity.ok(playerService.findPlayers());
    }

    @GetMapping("/search")
    public ResponseEntity<Player> getPlayerInfoByName(@RequestParam("name") String name) {
        Optional<Player> playerOptional = playerService.searchPlayer(name);
        Player player = playerOptional.orElse(null);

        if (player == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(player);
    }

    @PostMapping("/add/{id}")
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


}
