package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.fish.requests.FishRequest;
import com.clubs.aftas.entities.Fish;
import com.clubs.aftas.entities.Level;
import com.clubs.aftas.repositories.FishRepository;
import com.clubs.aftas.services.BaseService;
import com.clubs.aftas.services.FishService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class FishServiceImpl extends BaseService<Fish, Long> implements FishService {


    private final FishRepository fishRepository;

    public FishServiceImpl(FishRepository fishRepository) {
        super(fishRepository, Fish.class);
        this.fishRepository = fishRepository;
    }
    @Override
    public List<Fish> getAllFishs() {
        return getAllEntities();
    }

    @Override
    public Page<Fish> getAllFishsWithPagination(Pageable pageable) {
        return getAllEntitiesWithPagination(pageable);
    }

    @Override
    public Fish getFishById(Long id) {
        return getEntityById(id);
    }

    @Override
    public Fish createFish(FishRequest competitionRequest) {
        return null;
    }

    @Override
    public Fish updateFish(FishRequest competition, Long competitionId) {
        return null;
    }

    @Override
    public void createDefaultFishes() {

        try {

            // https://stackoverflow.com/questions/44589381/how-to-convert-json-string-into-list-of-java-object

            InputStream inputStream = getClass().getResourceAsStream("/fishes.json");
            List<Fish> fishes = new ObjectMapper().readValue(inputStream, new TypeReference<List<Fish>>() {});

            fishRepository.saveAll(fishes);

            System.out.println("Fishes added successfully!");

        } catch (IOException e) {
            System.err.println("Error in adding fishes: " + e.getMessage());
        }
    }
}
