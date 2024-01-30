package com.BackendPoke.BackendPoke.repository;


import org.springframework.data.repository.CrudRepository;

import com.BackendPoke.BackendPoke.api.model.Pokemon;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    
}
