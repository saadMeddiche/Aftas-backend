package com.clubs.aftas.services.implementations;

import com.clubs.aftas.dtos.huntings.requests.HuntingRequest;
import com.clubs.aftas.entities.Hunting;
import com.clubs.aftas.repositories.HuntingRepository;
import com.clubs.aftas.services.BaseService;
import com.clubs.aftas.services.HuntingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service


public class HuntingServiceImpl extends BaseService<Hunting, Long> implements HuntingService {

    private final HuntingRepository huntingRepository;

    public HuntingServiceImpl(HuntingRepository huntingRepository){
        super(huntingRepository , Hunting.class);
        this.huntingRepository = huntingRepository;

    }
    @Override
    public List<Hunting> getAllHuntings() {
        return getAllEntities();
    }

    @Override
    public Page<Hunting> getAllHuntingsWithPagination(Pageable pageable) {
        return getAllEntitiesWithPagination(pageable);
    }

    @Override
    public Hunting getHuntingById(Long id) {
        return getEntityById(id);
    }

    @Override
    public Hunting createHunting(HuntingRequest huntingRequest) {

        Hunting hunting = buildHuntingObject(huntingRequest , null);

        return huntingRepository.save(hunting);
    }



    @Override
    public Hunting updateHunting(HuntingRequest huntingRequest, Long huntingId) {

        Hunting hunting = buildHuntingObject(huntingRequest , huntingId);

        return huntingRepository.save(hunting);
    }

    public Hunting buildHuntingObject(HuntingRequest huntingRequest , Long huntingId){

        return Hunting.builder()
                .id(huntingId)
                .numberOfFish(huntingRequest.getNumberOfFish())
                .fish(huntingRequest.getFish())
                .competition(huntingRequest.getCompetition())
                .member(huntingRequest.getMember())
                .build();

    }
}
