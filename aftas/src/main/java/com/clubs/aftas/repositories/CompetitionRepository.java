package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Competition;

import com.clubs.aftas.entities.Hunting;
import com.clubs.aftas.entities.Member;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    // Get The participants of a competition


    @Query("SELECT DISTINCT r.member FROM Ranking r " +
            "WHERE r.competition.id = :competitionId " +
            "AND (:searchValue IS NULL OR " +
            "LOWER(r.member.name) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR " +
            "LOWER(r.member.familyName) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR " +
            "LOWER(r.member.nationality) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR " +
            "LOWER(r.member.identityNumber) LIKE LOWER(CONCAT('%', :searchValue, '%')) OR " +
            "LOWER(r.member.identityDocument) LIKE LOWER(CONCAT('%', :searchValue, '%')))")
    public Page<Member> findMembersByCompetitionIdAndSearchValue(Long competitionId, String searchValue, Pageable pageable);


    Page<Competition> findByDateBefore(LocalDate currentDate, String searchValue, Pageable pageable);

    Page<Competition> findByDateAfter(LocalDate currentDate, String searchValue, Pageable pageable);



}
