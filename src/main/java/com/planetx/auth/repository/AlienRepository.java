package com.planetx.auth.repository;

import com.planetx.auth.model.Alien;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AlienRepository extends MongoRepository<Alien, String> {
    Optional<Alien> findByAlienId(String alienId);
}
