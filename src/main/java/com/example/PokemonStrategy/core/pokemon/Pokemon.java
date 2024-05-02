package com.example.PokemonStrategy.core.pokemon;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Pokemon")
public class Pokemon {
    @Id
    private UUID pokemonId;
    private String name;
    private int hp;
    private int atk;
    private int def;
    // Included these type
//    NORMAL, FIRE, WATER, ELECTRIC
//    , GRASS, ICE, FIGHTING, POISON
//    , GROUND, FLYING, PSYCHIC, BUG
//    , ROCK, GHOST, DRAGON, DARK, STEEL, FAIRY;
    private String type;
}
