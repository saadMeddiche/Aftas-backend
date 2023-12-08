package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.competition.requests.CompetitionRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.repositories.CompetitionRepository;
import com.clubs.aftas.services.CompetitionService;
import com.clubs.aftas.services.businessLogic.BLCompetitionService;
import com.clubs.aftas.services.validations.ValidationCompetitionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final BLCompetitionService blCompetitionService;
    private final ValidationCompetitionService validationCompetitionService;
    private final CompetitionRepository competitionRepository;

    @Override
    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    @Override
    public Page<Competition> getAllCompetitionsWithPagination(Pageable pageable){
       return competitionRepository.findAll(pageable);
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
    public Competition createCompetition(@Valid CompetitionRequest competitionRequest) {

        // Validate The Competition
        validationCompetitionService.validateCompetitionWhenCreating(competitionRequest); // If Some thing went wrong throw an exception

        // Create The Competition
        Competition competition = buildCompetition(competitionRequest , null);

        // Save the competition to the database
        return competitionRepository.save(competition);

    }

    @Override
    public Competition updateCompetition(@Valid CompetitionRequest competitionRequest, Long competitionId) {

        // Validate The Competition
        validationCompetitionService.validateCompetitionWhenUpdating(competitionRequest, competitionId); // If Some thing went wrong throw an exception

        // Create The Competition
        Competition competition = buildCompetition(competitionRequest , competitionId);

        // Save the competition to the database
        return competitionRepository.save(competition);
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
                .rankings(null)
                .build();
    }
}
