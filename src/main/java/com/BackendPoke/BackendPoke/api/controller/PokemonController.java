package com.BackendPoke.BackendPoke.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/pokemon")
    public Optional<Pokemon> getPokemon(@RequestParam("id") int id) {
        System.out.println("Pokemon: " + id);
        return pokemonRepository.findById(id);
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

    @DeleteMapping("/pokemon")
    public String deletePokemon(@RequestParam("id") int id) {
        System.out.println("delete: " + id);
        pokemonRepository.deleteById(id);
        return "Deleted: " + id;
    }
}
