package com.clubs.aftas.services.validations;

import com.clubs.aftas.dtos.ranking.requests.RankingAddRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.clubs.aftas.handlingExceptions.costumExceptions.DateValidationException;
import com.clubs.aftas.repositories.RankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidationRankingService {

    private final RankingRepository rankingRepository;

    public void validationRankingWhenCreating(Ranking ranking) {

        Competition competition = ranking.getCompetition();

        if(competition.getNumberOfParticipants() == competition.getRankings().size()) {
            throw new AlreadyExistsException("The competition is already full");
        }


        if(LocalDate.now().plusDays(1).isEqual(competition.getDate()) || LocalDate.now().plusDays(1).isAfter(competition.getDate())) {
            throw new DateValidationException("The date of registration is passed");
        }

        Optional<Ranking> fatchedRanking = rankingRepository.findByMemberAndCompetition(ranking.getMember(), ranking.getCompetition());

        if(fatchedRanking.isPresent()) {
            throw new AlreadyExistsException("This member is already registered in this competition");
        }
    }
}
