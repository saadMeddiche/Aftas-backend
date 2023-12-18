package com.clubs.aftas.services;


import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.dtos.competition.Top;
import com.clubs.aftas.dtos.competition.requests.CompetitionRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.services.implementations.CompetitionServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CompetitionService {

    public List<Competition> getAllCompetitions();

    public Page<Competition> getAllCompetitionsWithPagination(Pageable pageable);

    public Competition getCompetitionById(Long id);

    public Page<Member> searchMembersOfCompetition(Long competitionId , String value , Pageable pageable);

    public Competition createCompetition(CompetitionRequest competitionRequest);

    public Competition updateCompetition(CompetitionRequest competition , Long competitionId);

    public void deleteCompetition(Long id);

    public void results(Long id);

    public Map<Integer, List<Top>> getTopThree(Long competitionId);

    public List<Competition> searchCompetitionsByCriteria(List<FilterDTO> filters);

    public Page<Competition> searchCompetitions(String value , Pageable pageable);


}
