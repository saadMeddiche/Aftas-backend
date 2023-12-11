package com.clubs.aftas.controllers;

import com.clubs.aftas.dtos.competition.requests.CompetitionRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.services.CompetitionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/competitions")
@AllArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @GetMapping
    public List<Competition> getCompetitions() {
        return competitionService.getAllCompetitions();
    }

    @GetMapping("/{competitionId}")
    public Competition getCompetition(@PathVariable Long competitionId) {
        return competitionService.getCompetitionById(competitionId);
    }
    @GetMapping("/pagination")
    public Page<Competition> getCompetitionsWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return competitionService.getAllCompetitionsWithPagination(pageable);
    }

    @PostMapping()
    public ResponseEntity<?> createCompetition(@Valid @RequestBody CompetitionRequest competitionRequest) {
        Competition addedCompetition = competitionService.createCompetition(competitionRequest);
        return new ResponseEntity<>(addedCompetition, HttpStatus.CREATED);
    }

    @PutMapping("/{competitionId}")
    public ResponseEntity<?> updateCompetition(@PathVariable Long competitionId,@Valid @RequestBody CompetitionRequest competitionRequest) {
        Competition updatedCompetition = competitionService.updateCompetition(competitionRequest, competitionId);
        return new ResponseEntity<>(updatedCompetition, HttpStatus.OK);
    }
}
