package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.competition.requests.CompetitionAddRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.services.CompetitionService;
import jakarta.validation.Valid;

import java.util.List;

public class CompetitionServiceImpl implements CompetitionService {
    @Override
    public List<Competition> getAllCompetitions() {
        return null;
    }

    @Override
    public Competition getCompetitionById(Long id) {
        return null;
    }

    @Override
    public List<Member> getParticipants(Competition competition) {
        return null;
    }

    @Override
    public List<Ranking> getRankings(Competition competition) {
        return null;
    }

    @Override
    public Competition createCompetition(@Valid CompetitionAddRequest competitionAddRequest) {
        return null;
    }

    @Override
    public Competition updateCompetition(Competition competition) {
        return null;
    }
}
