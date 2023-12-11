package com.clubs.aftas.services;

import com.clubs.aftas.dtos.huntings.requests.HuntingRequest;
import com.clubs.aftas.entities.Hunting;
import com.clubs.aftas.entities.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HuntingService {


    public List<Hunting> getAllHuntings();

    public Page<Hunting> getAllHuntingsWithPagination(Pageable pageable);

    public Hunting getHuntingById(Long id);

    public Hunting addHunting(HuntingRequest huntingRequest);

    public void decreaseHunting(HuntingRequest huntingRequest);
}
