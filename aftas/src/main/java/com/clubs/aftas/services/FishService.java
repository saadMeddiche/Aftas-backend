package com.clubs.aftas.services;


import com.clubs.aftas.dtos.fish.requests.FishRequest;
import com.clubs.aftas.entities.Fish;
import com.clubs.aftas.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FishService {


    public List<Fish> getAllFishs();

    public Page<Fish> getAllFishsWithPagination(Pageable pageable);

    public Fish getFishById(Long id);

    public Fish createFish(FishRequest competitionRequest);

    public Fish updateFish(FishRequest competition , Long competitionId);

    void deleteFish(Long id);

    void createDefaultFishes();

    public List<Fish> searchFishs(String value);
}
