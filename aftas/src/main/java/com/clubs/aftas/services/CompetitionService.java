package com.clubs.aftas.services;


import com.clubs.aftas.dtos.competition.requests.CompetitionAddRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CompetitionService {

    public List<Competition> getAllCompetitions();

    public Page<Competition> getAllCompetitionsWithPagination(Pageable pageable);

    public Competition getCompetitionById(Long id);

    public List<Member> getParticipants(Competition competition);

    public List<Ranking> getRankings(Competition competition);

    public Competition createCompetition(CompetitionAddRequest competitionAddRequest);

    public Competition updateCompetition(Competition competition);


}
