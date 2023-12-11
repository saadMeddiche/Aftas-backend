package com.clubs.aftas.controllers;

import com.clubs.aftas.dtos.fish.requests.FishRequest;
import com.clubs.aftas.entities.Fish;
import com.clubs.aftas.services.FishService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
public class FishController {

    private final FishService fishService;

    @GetMapping
    public List<Fish> getFishs() {
        return fishService.getAllFishs();
    }

    @GetMapping("/{fishId}")
    public Fish getFish(@PathVariable Long fishId) {
        return fishService.getFishById(fishId);
    }
    @GetMapping("/pagination")
    public Page<Fish> getFishsWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return fishService.getAllFishsWithPagination(pageable);
    }

    @PostMapping()
    public ResponseEntity<?> createFish(@Valid @RequestBody FishRequest fishRequest) {
        Fish addedFish = fishService.createFish(fishRequest);
        return new ResponseEntity<>(addedFish, HttpStatus.CREATED);
    }

    @PutMapping("/{fishId}")
    public ResponseEntity<?> updateFish(@PathVariable Long fishId,@Valid @RequestBody FishRequest fishRequest) {
        Fish updatedFish = fishService.updateFish(fishRequest, fishId);
        return new ResponseEntity<>(updatedFish, HttpStatus.OK);
    }
}
