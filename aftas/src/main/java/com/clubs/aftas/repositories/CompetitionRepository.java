package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Competition;

import com.clubs.aftas.entities.Hunting;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    Page<Competition> findAll(Pageable pageable);

    Optional<Competition> findByDate(LocalDate competitionDate);

    List<Competition> findAll(Specification<Competition> specification);
    Page<Competition> findAll(Specification<Competition> specification , Pageable pageable);


}
