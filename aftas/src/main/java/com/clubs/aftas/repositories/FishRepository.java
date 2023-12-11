package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FishRepository extends JpaRepository<Fish, Long> {

    Optional<Fish> findByName(String name);
}
