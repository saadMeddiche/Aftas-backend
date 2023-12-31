package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Fish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FishRepository extends JpaRepository<Fish, Long> {

    Optional<Fish> findByName(String name);

    List<Fish> findAll(Specification<Fish> specification);
    Page<Fish> findAll(Specification<Fish> specification , Pageable pageable);
}
