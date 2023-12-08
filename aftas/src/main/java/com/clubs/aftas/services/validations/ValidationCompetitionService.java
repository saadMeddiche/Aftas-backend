package com.clubs.aftas.services.validations;

import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.clubs.aftas.handlingExceptions.costumExceptions.DateValidationException;
import com.clubs.aftas.repositories.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidationCompetitionService {

    private final CompetitionRepository competitionRepository;

    public void validateCompetitionWhenCreating(Competition competition) {

        LocalDate competitionDate = competition.getDate();

        // Check If There is already a competition with the same date
        throwExceptionIfCompetitionWithSameDateAlreadyExists(competitionDate);

        // Check If The Date of the Competition Is not high then the current date by Three Days
        throwExceptionIfCompetitionDateIsNotMoreThanThreeDaysBeforeCurrentDate(competitionDate);

    }


    // throw excpegtion If There is already a competition with the same date
    public void throwExceptionIfCompetitionWithSameDateAlreadyExists(LocalDate competitionDate) {

        Optional<Competition> existingCompetition = competitionRepository.findByDate(competitionDate);

        if (existingCompetition.isEmpty()){
            throw new AlreadyExistsException("Competition with the same date already exists");
        }

    }

    // throw excpegtion If The Date of the Competition Is not high then the current date by Three Days
    public void throwExceptionIfCompetitionDateIsNotMoreThanThreeDaysBeforeCurrentDate(LocalDate competitionDate) {

        LocalDate currentDatePlusThreeDays = LocalDate.now().plusDays(3);

        if (competitionDate.isBefore(currentDatePlusThreeDays)) {
            throw new DateValidationException("Competition date must be at least 3 days before the current date");
        }
    }
}
