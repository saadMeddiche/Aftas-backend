package com.clubs.aftas.repositories;

import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface RankingRepository extends JpaRepository<Ranking, Long> {

    Optional<Ranking> findByMemberAndCompetition(Member member, Competition competition);

    Boolean existsByMemberAndCompetition(Member member, Competition competition);

    List<Ranking> findByCompetitionOrderByScoreDesc(Competition competition);

    List<Ranking> findAll(Specification<Ranking> specification);

}
