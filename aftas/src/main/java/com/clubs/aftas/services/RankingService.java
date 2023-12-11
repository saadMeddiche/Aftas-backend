package com.clubs.aftas.services;

import com.clubs.aftas.entities.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankingService {

    public List<Ranking> getAllRankings();

    public Page<Ranking> getAllRankingsWithPagination(Pageable pageable);

    public Ranking getRankingById(Long id);

    public Ranking registerAMemberInACompetition(Ranking ranking);

}
