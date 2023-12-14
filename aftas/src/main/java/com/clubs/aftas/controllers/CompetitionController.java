package com.clubs.aftas.controllers;

import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.dtos.competition.Top;
import com.clubs.aftas.dtos.competition.requests.CompetitionRequest;
import com.clubs.aftas.entities.Competition;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.handlingExceptions.costumExceptions.ValidationException;
import com.clubs.aftas.services.CompetitionService;
import com.clubs.aftas.services.implementations.CompetitionServiceImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping("/results/{competitionId}")
    public ResponseEntity<?> getResults(@PathVariable Long competitionId) {
         competitionService.results(competitionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/topthree/{competitionId}")
    public ResponseEntity<?> getTopThree(@PathVariable Long competitionId) {

        Map<Integer, List<Top>> topThree = competitionService.getTopThree(competitionId);
        return new ResponseEntity<>(topThree, HttpStatus.OK);
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

    @PostMapping("/searchByCriteria")
    public ResponseEntity<?> searchCompetitionsByCriteria(@RequestBody List<FilterDTO> filters) {
        List<Competition> competitions = competitionService.searchCompetitionsByCriteria(filters);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @GetMapping("/search/{value}")
    public ResponseEntity<?> searchCompetitions(@PathVariable String value ,@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        Page<Competition> competitions = competitionService.searchCompetitions(value , pageable);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }

    @GetMapping(value =  {"/search" , "/search/"})
    public ResponseEntity<?> searchCompetitionsDefault( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

        // check if page or size is null or negative
        if (page < 0 || size < 0) throw new ValidationException("page and size must be greater than 0");

        PageRequest pageable = PageRequest.of(page, size);
        Page<Competition> competitions = competitionService.searchCompetitions("" , pageable);
        return new ResponseEntity<>(competitions, HttpStatus.OK);
    }
}
