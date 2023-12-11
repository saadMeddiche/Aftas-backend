package com.clubs.aftas.services.validations;

import com.clubs.aftas.dtos.huntings.requests.HuntingRequest;
import com.clubs.aftas.entities.Fish;
import com.clubs.aftas.services.FishService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ValidationHuntingService {

    private final FishService fishService;

    public Boolean checkIfHuntedFishIsValid(Fish huntedFish , Double weightOfHuntedFish){
       return huntedFish.getAverageWeight() <= weightOfHuntedFish;
    }
}
