package com.clubs.aftas.services.implementations;

import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.repositories.RankingRepository;
import com.clubs.aftas.services.BaseService;
import com.clubs.aftas.services.CompetitionService;
import com.clubs.aftas.services.MemberService;
import com.clubs.aftas.services.RankingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingServiceImpl extends BaseService<Ranking, Long> implements RankingService {

    private final RankingRepository rankingRepository;

    private final CompetitionService competitionService;

    private final MemberService memberService;
    public RankingServiceImpl(RankingRepository rankingRepository, CompetitionService competitionService, MemberService memberService) {
        super(rankingRepository, Ranking.class);
        this.rankingRepository = rankingRepository;
        this.competitionService = competitionService;
        this.memberService = memberService;
    }
    @Override
    public List<Ranking> getAllRankings() {
        return getAllEntities();
    }

    @Override
    public Page<Ranking> getAllRankingsWithPagination(Pageable pageable) {
        return getAllEntitiesWithPagination(pageable);
    }

    @Override
    public Ranking getRankingById(Long id) {
        return getEntityById(id);
    }

    @Override
    public Ranking createRanking(Ranking ranking) {

        return rankingRepository.save(ranking);
    }
}
