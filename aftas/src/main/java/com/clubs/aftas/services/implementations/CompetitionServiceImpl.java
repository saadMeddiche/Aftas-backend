package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.competition.requests.CompetitionAddRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.repositories.CompetitionRepository;
import com.clubs.aftas.services.CompetitionService;
import com.clubs.aftas.services.businessLogic.BLCompetitionService;
import com.clubs.aftas.services.validations.ValidationCompetitionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Competition createCompetition(@Valid CompetitionAddRequest competitionAddRequest) {

        // Validate The Competition
        validationCompetitionService.validateCompetitionWhenCreating(competitionAddRequest); // If Some thing went wrong throw an exception

        // Create The Code Of Competition
        String code = blCompetitionService.createCodeForCompetition(competitionAddRequest.getLocation() , competitionAddRequest.getDate());

        // Create The Competition
        Competition competition = buildCompetitionObject(competitionAddRequest, code);

        // Save the competition to the database
        return competitionRepository.save(competition);

    }

    @Override
    public Competition updateCompetition(Competition competition) {
        return null;
    }

    private Competition buildCompetitionObject(CompetitionAddRequest competitionAddRequest, String code) {
        return Competition.builder()
                .code(code)
                .date(competitionAddRequest.getDate())
                .startTime(competitionAddRequest.getStartTime())
                .endTime(competitionAddRequest.getEndTime())
                .numberOfParticipants(competitionAddRequest.getNumberOfParticipants())
                .location(competitionAddRequest.getLocation())
                .amount(competitionAddRequest.getAmount())
                .rankings(null)
                .build();
    }
}
