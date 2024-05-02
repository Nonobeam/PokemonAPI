package com.example.PokemonStrategy.service;

import com.example.PokemonStrategy.core.pokemon.Pokemon;
import com.example.PokemonStrategy.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    public Optional findPokemon(UUID id){
        return pokemonRepository.findByPokemonId(id);
    }

    public Optional searchPokemon(Optional<String> name){
        return pokemonRepository.findByName(name);
    }

    public List<Pokemon> all(){
        return pokemonRepository.findAll();
    }
}
