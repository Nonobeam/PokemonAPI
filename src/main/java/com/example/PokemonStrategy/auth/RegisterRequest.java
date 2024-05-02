package com.example.PokemonStrategy.auth;


import com.example.PokemonStrategy.core.player.Role;
import com.example.PokemonStrategy.core.player.Status;
import com.example.PokemonStrategy.core.player.Turn;
import com.example.PokemonStrategy.core.pokemon.Pokemon;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String password;
    private Role role;
}
