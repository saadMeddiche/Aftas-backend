package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.dtos.competition.Top;
import com.clubs.aftas.dtos.competition.requests.CompetitionRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Hunting;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.handlingExceptions.costumExceptions.DoNotExistException;
import com.clubs.aftas.handlingExceptions.costumExceptions.EmptyException;
import com.clubs.aftas.repositories.CompetitionRepository;
import com.clubs.aftas.services.BaseService;
import com.clubs.aftas.services.CompetitionService;
import com.clubs.aftas.services.businessLogic.BLCompetitionService;
import com.clubs.aftas.services.validations.ValidationCompetitionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class CompetitionServiceImpl extends BaseService<Competition, Long> implements CompetitionService {

    private final BLCompetitionService blCompetitionService;
    private final ValidationCompetitionService validationCompetitionService;
    private final CompetitionRepository competitionRepository;

    public CompetitionServiceImpl(CompetitionRepository competitionRepository, BLCompetitionService blCompetitionService, ValidationCompetitionService validationCompetitionService){
        super(competitionRepository , Competition.class);
        this.competitionRepository = competitionRepository;
        this.blCompetitionService = blCompetitionService;
        this.validationCompetitionService = validationCompetitionService;
    }

    @Override
    public List<Competition> getAllCompetitions() {
        return getAllEntities();
    }

    @Override
    public Page<Competition> getAllCompetitionsWithPagination(Pageable pageable){
        return getAllEntitiesWithPagination(pageable);
    }

    @Override
    public Competition getCompetitionById(Long id) {
       return getEntityById(id);
    }

    @Override
    public Page<Member> searchMembersOfCompetition(Long competitionId, String value , Pageable pageable) {
       return Optional.of( competitionRepository.findMembersByCompetitionIdAndSearchValue(competitionId, value , pageable)).orElseThrow(() -> new EmptyException("No participants in this competition"));
    }

    @Override
    public Competition createCompetition(@Valid CompetitionRequest competitionRequest) {

        // Validate The Competition
        validationCompetitionService.validateCompetitionWhenCreating(competitionRequest); // If Some thing went wrong throw an exception

        //Create The Competition and Save the competition to the database
        return competitionRepository.save(buildCompetition(competitionRequest , null));

    }

    @Override
    public Competition updateCompetition(@Valid CompetitionRequest competitionRequest, Long competitionId) {

        // Validate The Competition
        validationCompetitionService.validateCompetitionWhenUpdating(competitionRequest, competitionId); // If Some thing went wrong throw an exception

        // Save the competition to the database
        return competitionRepository.save(buildCompetition(competitionRequest , competitionId));
    }

    @Override
    public void deleteCompetition(Long id) {
        deleteEntityById(id);
    }

    @Override
    public void results(Long competitionId) {
        Competition competition = getCompetitionById(competitionId);
        blCompetitionService.resultsOfCompetition(competition);
    }


    @Override
    public Map<Integer, List<Top>> getTopThree(Long competitionId) {

        Competition competition = getCompetitionById(competitionId);

        List<Ranking> rankingList = competition.getRankings();


        Map<Integer, List<Ranking>> rankingsByScore = rankingList.stream()
                .collect(Collectors.groupingBy(Ranking::getScore));


        List<Integer> bigScores = rankingList.stream()
                .map(Ranking::getScore)
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());


        List<Integer> topThreeScores = bigScores.stream().limit(3).collect(Collectors.toList());


        Map<Integer, List<Top>> topThree = new HashMap<>();

        int rank = 1;
        for (Integer score : topThreeScores) {
            List<Ranking> rankingsWithScore = rankingsByScore.get(score);
            List<Top> topList = rankingsWithScore.stream()
                    .map(ranking -> new Top(ranking.getMember(), ranking.getScore()))
                    .collect(Collectors.toList());
            topThree.put(rank++, topList);
        }

        return topThree;

    }

//    @Override
//    public Map<Integer, List<Top>> getTopThree(Long competitionId) {
//
//        Competition competition = getCompetitionById(competitionId);
//
//        List<Ranking> rankingList = competition.getRankings();
//
//        Map<Integer, List<Ranking>> rankingsByScore = blCompetitionService.groupRankingsByScore(rankingList);
//
//        List<Integer> topThreeScores = blCompetitionService.getTopThreeScores(rankingList);
//
//        return blCompetitionService.createTopThreeMap(rankingsByScore, topThreeScores);
//    }

    @Override
    public List<Competition> searchCompetitionsByCriteria(List<FilterDTO> filters) {
        return Optional.of(competitionRepository.findAll(searchByCriteria(filters)))
                .orElseThrow(() -> new EmptyException("No competition has been found"));
    }

    @Override
    public Page<Competition> searchCompetitions(String value , Pageable pageable) {
        return Optional.of(competitionRepository.findAll(search(value) , pageable))
                .orElseThrow(() -> new EmptyException("No competition has been found"));
    }

    private Competition buildCompetition( CompetitionRequest competitionRequest , Long competitionId) {

        // Create The Code Of Competition
        String code = blCompetitionService.createCodeForCompetition(competitionRequest.getLocation() , competitionRequest.getDate());

        // Create The Competition
        return buildCompetitionObject(competitionRequest, code , competitionId);
    }

    private Competition buildCompetitionObject(CompetitionRequest competitionRequest, String code , Long competitionId) {
        return Competition.builder()
                .id(competitionId)
                .code(code)
                .date(competitionRequest.getDate())
                .startTime(competitionRequest.getStartTime())
                .endTime(competitionRequest.getEndTime())
                .numberOfParticipants(competitionRequest.getNumberOfParticipants())
                .location(competitionRequest.getLocation())
                .amount(competitionRequest.getAmount())
                .rankings(new ArrayList<Ranking>())
                .huntings(new ArrayList<Hunting>())
                .build();
    }
}
