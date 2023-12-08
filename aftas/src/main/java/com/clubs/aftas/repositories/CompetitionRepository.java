package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {

    List<Competition> findAllPagination(Long id , Pageable pageable);

    Optional<Competition> findByDate(LocalDate competitionDate);


}
