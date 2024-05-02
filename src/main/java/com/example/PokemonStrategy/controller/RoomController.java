package com.example.PokemonStrategy.controller;

import com.example.PokemonStrategy.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/room")
@RestController
@RequiredArgsConstructor
@CrossOrigin
public class RoomController {
    private final RoomService roomService;

    @GetMapping("/start")
    public void startRoom(){
//        roomService.startRoom();
    }

}
