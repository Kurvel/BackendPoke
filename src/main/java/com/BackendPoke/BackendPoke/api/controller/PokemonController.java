package com.BackendPoke.BackendPoke.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BackendPoke.BackendPoke.api.model.Pokemon;
import com.BackendPoke.BackendPoke.repository.PokemonRepository;

@RestController
public class PokemonController {
    
    @Autowired
    private final PokemonRepository pokemonRepository;

    public PokemonController(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @GetMapping("/pokemons")
    public ResponseEntity<List<Pokemon>> getPokemons() {
        List<Pokemon> pokemons = (List<Pokemon>) pokemonRepository.findAll();
        return ResponseEntity.ok().body(pokemons);
    }


    @PostMapping("/pokemon")
    public Pokemon creatPokemon(@RequestBody Pokemon pokemon) {
        System.out.println("createPokemon: " + pokemon);

        if (pokemon != null) {
            return pokemonRepository.save(pokemon);
        } else {
            throw new IllegalStateException("pokemon är tom!");
        }
    }
}
