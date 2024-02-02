package com.BackendPoke.BackendPoke.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/pokemon")
    public Optional<Pokemon> getPokemon(@RequestParam("pokemonId") int id) {
        System.out.println("Pokemon: " + id);
        // TODO skapa metod för att hämta med pokemonId
        return pokemonRepository.findById(id);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/pokemons")
    public ResponseEntity<List<Pokemon>> getPokemons() {
        List<Pokemon> pokemons = (List<Pokemon>) pokemonRepository.findAll();
        return ResponseEntity.ok().body(pokemons);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/pokemon")
    public Pokemon creatPokemon(@RequestBody Pokemon pokemon) {
        System.out.println("createPokemon: " + pokemon);

        if (pokemon != null) {
            return pokemonRepository.save(pokemon);
        } else {
            throw new IllegalStateException("pokemon är tom!");
        }
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @DeleteMapping("/pokemon")
    public ResponseEntity<String> deletePokemon(@RequestParam("pokemonId") int pokemonId) {
        System.out.println("delete: " + pokemonId);

        pokemonRepository.deletePokemon(pokemonId);

        String response = "{\"message\": \"Deleted:\", \"id\": " + pokemonId + "}";
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PatchMapping("/pokemon")
    public ResponseEntity<?> notice(@RequestParam("pokemonId") int pokemonId, @RequestBody Pokemon updatedpokemon) {
        System.out.println("Notis: " + pokemonId);
        Optional<Pokemon> optionalPokemon = pokemonRepository.findByPokemonId(pokemonId);
        if (optionalPokemon.isPresent()) {
            Pokemon changeNotice = optionalPokemon.get();
            changeNotice.setNotice(updatedpokemon.getNotice());
            pokemonRepository.save(changeNotice);
            return ResponseEntity.ok(Map.of("message", "OK"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "Fel"));
        }

    }
}
