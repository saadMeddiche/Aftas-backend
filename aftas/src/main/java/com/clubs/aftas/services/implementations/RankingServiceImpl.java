package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.ranking.requests.RankingAddRequest;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.repositories.RankingRepository;
import com.clubs.aftas.services.BaseService;
import com.clubs.aftas.services.CompetitionService;
import com.clubs.aftas.services.MemberService;
import com.clubs.aftas.services.RankingService;
import com.clubs.aftas.services.validations.ValidationRankingService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingServiceImpl extends BaseService<Ranking, Long> implements RankingService {

    private final RankingRepository rankingRepository;

    private final CompetitionService competitionService;

    private final MemberService memberService;

    private final ValidationRankingService validation;

    public RankingServiceImpl(RankingRepository rankingRepository, CompetitionService competitionService, MemberService memberService, ValidationRankingService validation) {
        super(rankingRepository, Ranking.class);
        this.rankingRepository = rankingRepository;
        this.competitionService = competitionService;
        this.memberService = memberService;
        this.validation = validation;
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
    public Ranking registerAMemberInACompetition(RankingAddRequest rankingAddRequest) {

        Ranking ranking = buildRankingObject(rankingAddRequest , null);

        validation.validationRankingWhenCreating(ranking); // If there is a problem it will throw an exception

        return rankingRepository.save(null);
    }

    private Ranking buildRankingObject(RankingAddRequest rankingAddRequest , Long rankingId) {
        return Ranking.builder()
                .id(rankingId)
                .rank(null)
                .score(0)
                .member(memberService.getMemberById(rankingAddRequest.getMember().getId()))
                .competition(competitionService.getCompetitionById(rankingAddRequest.getCompetition().getId()))
                .build();
    }
}
