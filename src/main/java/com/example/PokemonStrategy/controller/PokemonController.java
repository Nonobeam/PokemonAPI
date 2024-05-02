package com.example.PokemonStrategy.controller;

import com.example.PokemonStrategy.core.pokemon.Pokemon;
import com.example.PokemonStrategy.service.PokemonService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.Name;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("/api/v1/pokemon")
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonService pokemonService;

    @GetMapping("/info/{id}")
    public ResponseEntity<Pokemon> getPokemonInfo(@PathVariable(name = "id", required = false) UUID id) {
        Optional<Pokemon> pokemonOptional = pokemonService.findPokemon(id);
        Pokemon pokemon = pokemonOptional.orElse(null);

        if (pokemon == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(pokemon);
    }

    @GetMapping("/search")
    public ResponseEntity<Pokemon> getPokemonInfoByName(@RequestParam("name") Optional<String> name) {
        Optional<Pokemon> pokemonOptional = pokemonService.searchPokemon(name);
        Pokemon pokemon = pokemonOptional.orElse(null);

        if (pokemon == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(pokemon);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pokemon>> all(){
        return ResponseEntity.ok(pokemonService.all());
    }
}
