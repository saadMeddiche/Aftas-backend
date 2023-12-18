package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.dtos.fish.requests.FishRequest;
import com.clubs.aftas.entities.Fish;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.handlingExceptions.costumExceptions.EmptyException;
import com.clubs.aftas.repositories.FishRepository;
import com.clubs.aftas.services.BaseService;
import com.clubs.aftas.services.FishService;
import com.clubs.aftas.services.LevelService;
import com.clubs.aftas.services.validations.ValidationFishService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

@Service
public class FishServiceImpl extends BaseService<Fish, Long> implements FishService {


    private final FishRepository fishRepository;

    private final LevelService levelService;

    private final ValidationFishService validation;

    public FishServiceImpl(FishRepository fishRepository, LevelService levelService, ValidationFishService validation) {
        super(fishRepository, Fish.class);
        this.fishRepository = fishRepository;
        this.levelService = levelService;
        this.validation = validation;
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

        Fish fish = buildFishObject(competitionRequest, null);

        validation.validateFishWhenCreating(fish);

        return fishRepository.save(fish);
    }

    @Override
    public Fish updateFish(FishRequest competition, Long competitionId) {

        Fish fish = buildFishObject(competition, competitionId);

        validation.validateFishWhenUpdating(fish , competitionId);

        return fishRepository.save(fish);
    }

    @Override
    public void deleteFish(Long id) {
        deleteEntityById(id);
    }

    private Fish buildFishObject(FishRequest competitionRequest, Long fishId) {
        return Fish.builder()
                .id(fishId)
                .name(competitionRequest.getName())
                .averageWeight(competitionRequest.getAverageWeight())
                .level(levelService.getLevelById(competitionRequest.getLevelId()))
                .build();
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

    @Override
    public List<Fish> searchFishsByCriteria(List<FilterDTO> filters) {
        return Optional.of(fishRepository.findAll(searchByCriteria(filters)))
                .orElseThrow(() -> new EmptyException("No fish has been found"));
    }



    @Override
    public Page<Fish> searchFishs(String value , Pageable pageable) {
        return Optional.of(fishRepository.findAll(search(value) , pageable))
                .orElseThrow(() -> new EmptyException("No fish has been found"));
    }
}
