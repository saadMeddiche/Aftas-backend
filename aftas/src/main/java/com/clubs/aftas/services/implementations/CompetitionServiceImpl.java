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

        // Create The Code Of Competition
        String code = blCompetitionService.createCodeForCompetition(competitionAddRequest.getLocation() , competitionAddRequest.getDate());

        // Create The Competition
        Competition competition = new Competition();
        competition.setCode(code);
        competition.setDate(competitionAddRequest.getDate());
        competition.setStartTime(competitionAddRequest.getStartTime());
        competition.setEndTime(competitionAddRequest.getEndTime());
        competition.setNumberOfParticipants(competitionAddRequest.getNumberOfParticipants());
        competition.setLocation(competitionAddRequest.getLocation());
        competition.setAmount(competitionAddRequest.getAmount());

        // Save the competition to the database
        return competitionRepository.save(competition);



    }

    @Override
    public Competition updateCompetition(Competition competition) {
        return null;
    }
}
