package com.clubs.aftas.controllers;

import com.clubs.aftas.dtos.competition.requests.CompetitionAddRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.services.CompetitionService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/competitions")
@AllArgsConstructor
public class CompetitionController {

    private final CompetitionService competitionService;

    @PostMapping()
    public ResponseEntity<?> createCompetition(@Valid @RequestBody CompetitionAddRequest competitionAddRequest) {
        Competition addedCompetition = competitionService.createCompetition(competitionAddRequest);
        return new ResponseEntity<>(addedCompetition, HttpStatus.CREATED);
    }
}
