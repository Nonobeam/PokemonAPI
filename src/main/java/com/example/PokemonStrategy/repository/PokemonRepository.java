package com.example.PokemonStrategy.repository;

import com.example.PokemonStrategy.core.pokemon.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PokemonRepository extends JpaRepository<Pokemon, Long>{
    Optional findByName(Optional<String> name);
    Optional findByPokemonId(UUID id);
}
