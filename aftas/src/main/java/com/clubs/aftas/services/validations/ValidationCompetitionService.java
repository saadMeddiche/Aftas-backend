package com.clubs.aftas.services.validations;

import com.clubs.aftas.dtos.competition.requests.CompetitionRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.clubs.aftas.handlingExceptions.costumExceptions.DateValidationException;
import com.clubs.aftas.repositories.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidationCompetitionService {

    private final CompetitionRepository competitionRepository;

    public void validateCompetitionWhenCreating(CompetitionRequest competition) {

        LocalDate competitionDate = competition.getDate();

        // Check If The Date of the Competition Is not high then the current date by Three Days
        throwExceptionIfCompetitionDateIsNotMoreThanThreeDaysBeforeCurrentDate(competitionDate);

        // Check If The start time is after the end time
        throwExceptionIfStartTimeIsAfterEndTime(competition.getStartTime(), competition.getEndTime());

        // Check If There is already a competition with the same date
        throwExceptionIfCompetitionWithSameDateAlreadyExists(competitionDate);

    }

    public void validateCompetitionWhenUpdating(CompetitionRequest competitionRequest , Long competitionId) {

        LocalDate competitionDate = competitionRequest.getDate();

        Optional<Competition> existingCompetition = competitionRepository.findById(competitionId);

        if(competitionRequest.getNumberOfParticipants() < existingCompetition.get().getRankings().size()) {
            throw new AlreadyExistsException("You can not update the number of participants to less than the current number of participants");

        }

        // Check If The Date of the Competition Is not high then the current date by Three Days
        throwExceptionIfCompetitionDateIsNotMoreThanThreeDaysBeforeCurrentDate(competitionDate);

        // Check If The start time is after the end time
        throwExceptionIfStartTimeIsAfterEndTime(competitionRequest.getStartTime(), competitionRequest.getEndTime());

        // Check If There is already a competition with the same date but with different id
        throwExceptionIfCompetitionWithSameDateAlreadyExistsButWithDifferentId(competitionRequest , competitionId);

    }

    // Throw  exception If There is already a competition with the same date but with different id
    public void throwExceptionIfCompetitionWithSameDateAlreadyExistsButWithDifferentId(CompetitionRequest competition , Long competitionId) {

        Optional<Competition> existingCompetition = competitionRepository.findByDate(competition.getDate());

        if (existingCompetition.isPresent() && !existingCompetition.get().getId().equals(competitionId)) {
            throw new AlreadyExistsException("The Competition : '"+ existingCompetition.get().getCode()+"' already have that date");
        }
    }


    // throw excpegtion If There is already a competition with the same date
    public void throwExceptionIfCompetitionWithSameDateAlreadyExists(LocalDate competitionDate) {

        Optional<Competition> existingCompetition = competitionRepository.findByDate(competitionDate);

        if (existingCompetition.isPresent()){
            throw new AlreadyExistsException("The Competition : '"+ existingCompetition.get().getCode()+"' already have that date");
        }

    }

    // throw excpegtion If The Date of the Competition Is not high then the current date by Three Days
    public void throwExceptionIfCompetitionDateIsNotMoreThanThreeDaysBeforeCurrentDate(LocalDate competitionDate) {

        LocalDate currentDatePlusThreeDays = LocalDate.now().plusDays(3);

        if (competitionDate.isBefore(currentDatePlusThreeDays)) {
            throw new DateValidationException("Competition date must be at least 3 days before the current date");
        }
    }

    // throw exception  If The start time is after the end time
    public void throwExceptionIfStartTimeIsAfterEndTime(LocalTime startTime, LocalTime endTime) {
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new DateValidationException("Start time cannot be after end time Or equivalent");
        }
    }
}
