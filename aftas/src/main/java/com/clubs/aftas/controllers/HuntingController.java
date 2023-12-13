package com.clubs.aftas.controllers;

import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.dtos.huntings.requests.HuntingRequest;
import com.clubs.aftas.entities.Hunting;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.services.HuntingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/huntings")
@AllArgsConstructor
public class HuntingController {

    private final HuntingService huntingService;

    @GetMapping
    public List<Hunting> getHuntings() {
        return huntingService.getAllHuntings();
    }

    @GetMapping("/{huntingId}")
    public Hunting getHunting(@PathVariable Long huntingId) {
        return huntingService.getHuntingById(huntingId);
    }
    @GetMapping("/pagination")
    public Page<Hunting> getHuntingsWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return huntingService.getAllHuntingsWithPagination(pageable);
    }

    @PostMapping()
    public ResponseEntity<?> addHunting(@Valid @RequestBody HuntingRequest huntingRequest) {
       huntingService.addHunting(huntingRequest);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }

    @PutMapping("/{huntingId}")
    public ResponseEntity<?> decreaseHunting(@PathVariable Long huntingId) {
        huntingService.decreaseHunting(huntingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/searchByCriteria")
    public ResponseEntity<?> searchHuntingsByCriteria(@RequestBody List<FilterDTO> filters) {
        List<Hunting> huntings = huntingService.searchHuntingsByCriteria(filters);
        return new ResponseEntity<>(huntings, HttpStatus.OK);
    }

    @GetMapping("/search/{value}")
    public ResponseEntity<?> searchHuntings(@PathVariable String value) {
        List<Hunting> huntings = huntingService.searchHuntings(value);
        return new ResponseEntity<>(huntings, HttpStatus.OK);
    }
}
