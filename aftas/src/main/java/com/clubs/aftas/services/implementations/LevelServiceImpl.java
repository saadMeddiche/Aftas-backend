package com.clubs.aftas.services.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.clubs.aftas.entities.Level;
import com.clubs.aftas.repositories.LevelRepository;
import com.clubs.aftas.services.LevelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class LevelServiceImpl implements LevelService {

    private final LevelRepository levelRepository;
    @Override
    public List<Level> getAllLevels() {
        return null;
    }
    @Override
    public Level getLevelById(Long id) {
        return null;
    }

    @Override
    public Level addLevel(Level level) {
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
