package com.example.PokemonStrategy.service;

import com.example.PokemonStrategy.core.Room;
import com.example.PokemonStrategy.core.player.Player;
import com.example.PokemonStrategy.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    // Get Battle Room's Id or create a new Room with 2 player's IDs
    public Optional<UUID> getRoomId(Player player1, Player player2, Boolean notExisted){
        return roomRepository.findByAttackerIdAndDefenderId(player1.getPlayerId(), player2.getPlayerId())
                // If the room existed, call the method getId from Room class mapped with room (room.getId())
                .map(Room::getId)
                // If room didn't existed, call startRoom() method to create a new one
                .or(() -> {
                   if (notExisted){
                       UUID roomId = startRoom(player1, player2);
                       // Return option value for roomId, if not created (error while doing), return empty
                       return Optional.of(roomId);
                   }
                   // Return option value for roomId
                    return Optional.empty();
                });
    }

    // Create a new battle room with 2 player's IDs
    public UUID startRoom(Player player1, Player player2){
        // Id format "ID1_ID2"
        UUID roomId = UUID.fromString(String.format("%s_%s", player1.getPlayerId().toString(), player2.toString()));

        // Make 2 rooms with same ID
        Room player1Room = Room.builder()
                .id(roomId)
                .attackerId(player1.getPlayerId())
                .defenderId(player2.getPlayerId())
                .build();

        Room player2Room = Room.builder()
                .id(roomId)
                .attackerId(player2.getPlayerId())
                .defenderId(player1.getPlayerId())
                .build();

        roomRepository.save(player1Room);
        roomRepository.save(player2Room);

        // Return "ID1_ID2"
        return roomId;
    }
}
