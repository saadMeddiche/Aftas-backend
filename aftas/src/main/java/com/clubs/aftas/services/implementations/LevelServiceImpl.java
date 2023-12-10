package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.level.requests.LevelRequest;
import com.clubs.aftas.entities.Level;
import com.clubs.aftas.handlingExceptions.costumExceptions.DoNotExistException;
import com.clubs.aftas.handlingExceptions.costumExceptions.EmptyException;

import com.clubs.aftas.services.BaseService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.clubs.aftas.repositories.LevelRepository;
import com.clubs.aftas.services.LevelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service

public class LevelServiceImpl extends BaseService<Level, Long> implements LevelService {

    private final LevelRepository levelRepository;

    public LevelServiceImpl(LevelRepository levelRepository){
        super(levelRepository , Level.class);
        this.levelRepository = levelRepository;
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
        return null;
    }

    @Override
    public Level updateLevel(LevelRequest level, Long levelId) {
        return null;
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
}
