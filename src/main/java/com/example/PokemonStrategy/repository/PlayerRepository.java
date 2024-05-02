package com.example.PokemonStrategy.repository;

import com.example.PokemonStrategy.core.player.Player;
import com.example.PokemonStrategy.core.player.Status;
import org.apache.el.parser.AstLambdaParameters;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository extends JpaRepository<Player, UUID>{
    List<Player> findAllByStatus(Status status);
    Optional findByPlayerId(UUID id);

    Optional<Player> findByName(String name);
}
