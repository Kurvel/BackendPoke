package com.BackendPoke.BackendPoke.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.BackendPoke.BackendPoke.api.model.Pokemon;

import jakarta.transaction.Transactional;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Pokemon p WHERE p.pokemonId = ?1")
    int deletePokemon(int pokemonId);

    @Transactional
    @Query("SELECT p FROM Pokemon p WHERE p.pokemonId = ?1 ")
    Optional<Pokemon> findByPokemonId(int pokemonId);
}
