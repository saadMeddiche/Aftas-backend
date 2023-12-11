package com.clubs.aftas.services.validations;

import com.clubs.aftas.entities.Fish;
import com.clubs.aftas.handlingExceptions.costumExceptions.AlreadyExistsException;
import com.clubs.aftas.repositories.FishRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
@AllArgsConstructor
public class ValidationFishService {

    private final FishRepository fishRepository;

    public void validateFishWhenCreating(Fish fish) {
        throwExceptionIfFishNameIsAlreadyExists(fish.getName());
    }

    public void validateFishWhenUpdating(Fish fish, Long fishId) {
        throwExceptionIfFishNameIsAlreadyExistsButWithDifferentId(fish.getName() , fishId);
    }


    private void throwExceptionIfFishNameIsAlreadyExistsButWithDifferentId(String name , Long fishId) {

        Optional<Fish> fish = fishRepository.findByName(name);

        if (fish.isPresent() && !fish.get().getId().equals(fishId)) {
            throw new AlreadyExistsException("Fish with name " + name + " already exists");
        }
    }

    private void throwExceptionIfFishNameIsAlreadyExists(String name) {

        Optional<Fish> fish = fishRepository.findByName(name);

        if (fish.isPresent()) {
            throw new AlreadyExistsException("Fish with name " + name + " already exists");
        }

    }
}
