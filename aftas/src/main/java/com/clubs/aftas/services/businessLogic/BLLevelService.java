package com.clubs.aftas.services.businessLogic;

import com.clubs.aftas.entities.Level;
import com.clubs.aftas.handlingExceptions.costumExceptions.DoNotExistException;
import com.clubs.aftas.repositories.LevelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class BLLevelService {

    private final LevelRepository levelRepository;

    public Integer createCode(){

        Optional<Level> lastLevel = levelRepository.findTopByOrderByIdDesc();

        if(lastLevel.isPresent()){
            return lastLevel.get().getCode() + 1;
        }

        throw new DoNotExistException("There is no level in the database");
    }
}
