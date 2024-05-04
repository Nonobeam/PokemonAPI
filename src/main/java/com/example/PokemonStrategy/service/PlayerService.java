package com.example.PokemonStrategy.service;

import com.example.PokemonStrategy.core.player.Player;
import com.example.PokemonStrategy.core.pokemon.Pokemon;
import com.example.PokemonStrategy.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository playerRepository;

    public void addPokemon(Player player, Pokemon chosePoke) {
        player.getPokemonList().add(chosePoke);
        playerRepository.save(player);
    }

    public Optional<Player> searchPlayer(String name) {
        return playerRepository.findByName(name);
    }


    public Optional findPlayer(UUID id){
        return playerRepository.findByPlayerId(id);
    }

    public List<Player> findPlayers() {
//        return playerRepository.findAllByStatus(Status.ONLINE);
        return playerRepository.findAll();
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

}
