package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.level.requests.LevelRequest;
import com.clubs.aftas.entities.Level;

import com.clubs.aftas.services.BaseService;
import com.clubs.aftas.services.businessLogic.BLLevelService;
import com.clubs.aftas.services.validations.ValidationLevelService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.clubs.aftas.repositories.LevelRepository;
import com.clubs.aftas.services.LevelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service

public class LevelServiceImpl extends BaseService<Level, Long> implements LevelService {

    private final LevelRepository levelRepository;

    private final ValidationLevelService validation;

    private final BLLevelService blLevelService;

    public LevelServiceImpl(LevelRepository levelRepository , ValidationLevelService validation, BLLevelService blLevelService) {
        super(levelRepository , Level.class);
        this.levelRepository = levelRepository;
        this.validation = validation;
        this.blLevelService = blLevelService;
    }

    @Override
    public List<Level> getAllLevels() {
        return getAllEntities();
    }

    @Override
    public Page<Level> getAllLevelsWithPagination(Pageable pageable){
        return getAllEntitiesWithPagination(pageable);
    }

    @Override
    public Level getLevelById(Long id) {
        return getEntityById(id);
    }


    @Override
    public Level createLevel(LevelRequest levelRequest) {

        Integer code = blLevelService.createCode();

        Level level = buildLevelObject(levelRequest ,code, null);

        validation.validateLevelWhenCreating(level);

        return levelRepository.save(level);
    }

    @Override
    public Level updateLevel(LevelRequest levelRequest, Long levelId) {

        Optional<Level> levelWantedToBeUpdated = levelRepository.findById(levelId);

        Level level = buildLevelObject(levelRequest ,levelWantedToBeUpdated.get().getCode(), levelId);

        validation.validateLevelWhenUpdating(level);

        return levelRepository.save(level);
    }

    @Override
    public void createDefaultLevels() {

        try {

            // https://stackoverflow.com/questions/44589381/how-to-convert-json-string-into-list-of-java-object

            InputStream inputStream = getClass().getResourceAsStream("/levels.json");
            List<Level> levels = new ObjectMapper().readValue(inputStream, new TypeReference<List<Level>>() {});

            levelRepository.saveAll(levels);

            System.out.println("Levels added successfully!");

        } catch (IOException e) {
            System.err.println("Error in adding levels: " + e.getMessage());
        }
    }


    private Level buildLevelObject(LevelRequest levelRequest , Integer code, Long levelId) {

            return Level.builder()
                    .id(levelId)
                    .code(code)
                    .description(levelRequest.getDescription())
                    .points(levelRequest.getPoints())
                    .defaultLevel(false)
                    .build();

    }
}
