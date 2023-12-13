package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Level;
import com.clubs.aftas.entities.Member;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level, Long> {

    Optional<Level> findByCode(Integer code); //

    Optional<Level> findByPoints(Integer points);

     Optional<Level> findTopByOrderByIdDesc();

    List<Level> findAll(Specification<Level> specification);
}
