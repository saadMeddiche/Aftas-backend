package com.clubs.aftas.services.validations;

import com.clubs.aftas.dtos.ranking.requests.RankingAddRequest;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.clubs.aftas.repositories.RankingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidationRankingService {

    private final RankingRepository rankingRepository;

    public void validationRankingWhenCreating(Ranking ranking) {

        Optional<Ranking> fatchedRanking = rankingRepository.findByMemberAndCompetition(ranking.getMember(), ranking.getCompetition());

        if(fatchedRanking.isPresent()) {
            throw new AlreadyExistsException("There is already a ranking for this member in this competition");
        }
    }
}
