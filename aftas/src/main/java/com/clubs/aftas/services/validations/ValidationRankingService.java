package com.clubs.aftas.services.validations;

import com.clubs.aftas.dtos.ranking.requests.RankingAddRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.clubs.aftas.handlingExceptions.costumExceptions.DateValidationException;
import com.clubs.aftas.repositories.RankingRepository;
import lombok.AllArgsConstructor;
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

        // Check if the current date is before the date of competition by One day Or Less
        if(competition.getDate().plusDays(1).isBefore(LocalDate.now())) {
            throw new DateValidationException("The date of registration is passed");
        }

        Optional<Ranking> fatchedRanking = rankingRepository.findByMemberAndCompetition(ranking.getMember(), ranking.getCompetition());

        if(fatchedRanking.isPresent()) {
            throw new AlreadyExistsException("There is already a ranking for this member in this competition");
        }
    }
}
