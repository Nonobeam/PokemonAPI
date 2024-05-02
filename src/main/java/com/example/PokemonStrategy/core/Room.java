package com.example.PokemonStrategy.core;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Room {
    @Id
    private UUID id;
    private UUID attackerId;
    private UUID defenderId;
}
