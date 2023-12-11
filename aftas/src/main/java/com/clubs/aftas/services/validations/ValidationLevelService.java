package com.clubs.aftas.services.validations;

import com.clubs.aftas.entities.Level;
import com.clubs.aftas.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.clubs.aftas.handlingExceptions.costumExceptions.ValidationException;
import com.clubs.aftas.repositories.LevelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ValidationLevelService {

    private final LevelRepository levelRepository;

    public void validateLevelWhenCreating(Level level) {

        throwExceptionIfAlreadyThereIsALevelWithTheSameCode(level);

        throwExceptionIfAlreadyThereIsALevelWithTheSamePoints(level);

        throwExceptionIfPointsOfTheNewLevelIsLowerThanThePointsOfTheLastLevel(level);
    }

    public void validateLevelWhenUpdating(Level level) {

        throwExceptionIfAlreadyThereIsALevelWithTheSameCodeButWithADifferentId(level);

        throwExceptionIfAlreadyThereIsALevelWithTheSamePointsButWithADifferentId(level);

        throwExceptionIfPointsOfUpdatedLevelDoNotMatchTheTerms(level);

    }

    public void throwExceptionIfPointsOfTheNewLevelIsLowerThanThePointsOfTheLastLevel(Level level) {

        // Get the last level
        Optional<Level> lastLevel = levelRepository.findTopByOrderByIdDesc();

        // check if the points of the new level is lower than the last level
        if(lastLevel.isPresent() && level.getPoints() < lastLevel.get().getPoints()) {
            throw new ValidationException("The points of the new level is lower than the last level [Level Code: " + lastLevel.get().getCode() + " | " + lastLevel.get().getPoints() +" points]");
        }

    }

    public void throwExceptionIfPointsOfUpdatedLevelDoNotMatchTheTerms(Level currentLevel ) {

        // check if the points of the updated level is higher than the before level
        Optional<Level> beforeLevel = levelRepository.findByCode(currentLevel.getCode() - 1);
        if(beforeLevel.isPresent() && currentLevel.getPoints() < beforeLevel.get().getPoints()) {
            throw new ValidationException("The points of the updated level can not be lower then Level: " + beforeLevel.get().getCode() +" | " + beforeLevel.get().getPoints() +" points");
        }

        // check if the points of the updated level is lower than the after level
        Optional<Level> afterLevel = levelRepository.findByCode(currentLevel.getCode() + 1);
        if(afterLevel.isPresent() && currentLevel.getPoints() > afterLevel.get().getPoints()) {
            throw new ValidationException("The points of the updated level can not be higher then Level: " + afterLevel.get().getCode() +" | " + afterLevel.get().getPoints() +" points");
        }


    }

    private void throwExceptionIfAlreadyThereIsALevelWithTheSameCodeButWithADifferentId(Level level) {

        Optional<Level> fetchedlevel = levelRepository.findByCode(level.getCode());

        if(fetchedlevel.isPresent() && !fetchedlevel.get().getId().equals(level.getId())) {
            throw new AlreadyExistsException("There is already a level with the same code [Level Code: " + fetchedlevel.get().getCode() + "]");
        }
    }

    private void throwExceptionIfAlreadyThereIsALevelWithTheSameCode(Level level) {

        Optional<Level> fetchedlevel = levelRepository.findByCode(level.getCode());

        if(fetchedlevel.isPresent()) {
            throw new AlreadyExistsException("There is already a level with the same code [Level Code: " + fetchedlevel.get().getCode() + "]" );
        }
    }

    private void throwExceptionIfAlreadyThereIsALevelWithTheSamePointsButWithADifferentId(Level level) {

        Optional<Level> fetchedlevel = levelRepository.findByPoints(level.getPoints());

        if(fetchedlevel.isPresent() && !fetchedlevel.get().getId().equals(level.getId())) {
            throw new AlreadyExistsException("There is already a level with the same points [Level Code: " + fetchedlevel.get().getCode() + " | Points: " + fetchedlevel.get().getPoints() + "]");
        }
    }

    private void throwExceptionIfAlreadyThereIsALevelWithTheSamePoints(Level level) {

        Optional<Level> fetchedlevel = levelRepository.findByPoints(level.getPoints());

        if(fetchedlevel.isPresent()) {
            throw new AlreadyExistsException("There is already a level with the same points [Level Code: " + fetchedlevel.get().getCode() + " | Points: " + fetchedlevel.get().getPoints() + "]");
        }
    }
}
