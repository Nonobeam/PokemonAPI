package com.example.PokemonStrategy.repository;

import com.example.PokemonStrategy.core.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByAttackerIdAndDefenderId(UUID attackerId, UUID defender2Id);
}
