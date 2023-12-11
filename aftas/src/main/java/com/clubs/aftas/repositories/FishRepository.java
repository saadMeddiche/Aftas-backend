package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FishRepository extends JpaRepository<Fish, Long> {
}
