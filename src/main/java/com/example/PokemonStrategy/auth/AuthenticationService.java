package com.example.PokemonStrategy.auth;

import com.example.PokemonStrategy.config.JwtService;
import com.example.PokemonStrategy.core.player.Player;
import com.example.PokemonStrategy.core.player.Role;
import com.example.PokemonStrategy.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var player = Player.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.Admin)
                .build();
        playerRepository.save(player);
        var jwtToken = jwtService.generateToken(player);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getName(),
                        request.getPassword()
                )
        );
        var player = playerRepository.findByName(request.getName())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(player);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
