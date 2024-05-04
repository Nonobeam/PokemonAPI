package com.example.PokemonStrategy.core.player;

import com.example.PokemonStrategy.core.pokemon.Pokemon;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Player")
public class Player implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID playerId;

    private String name;
    private String password;

    @Enumerated(EnumType.STRING)
    private Turn turn;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    @JoinTable(
            name = "PokemonPlayer",
            joinColumns = @JoinColumn(name = "playerId"),
            inverseJoinColumns = @JoinColumn(name = "pokemonId")
    )
    private List<Pokemon> pokemonList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
//        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", turn=" + turn +
                ", status=" + status +
                ", role=" + role +
                ", pokemonList=" + pokemonList +
                '}';
    }
}
