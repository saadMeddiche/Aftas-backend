package com.clubs.aftas.controllers;

import com.clubs.aftas.dtos.ranking.requests.RankingAddRequest;

import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.services.RankingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/rankings")
@AllArgsConstructor
public class RankingController {

    private final RankingService rankingService;


    @GetMapping
    public List<Ranking> getRankings() {
        return rankingService.getAllRankings();
    }

    @GetMapping("/{rankingId}")
    public Ranking getRanking(@PathVariable Long rankingId) {
        return rankingService.getRankingById(rankingId);
    }
    @GetMapping("/pagination")
    public Page<Ranking> getRankingsWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return rankingService.getAllRankingsWithPagination(pageable);
    }

    @PostMapping()
    public ResponseEntity<?> createRanking(@Valid @RequestBody RankingAddRequest rankingRequest) {
        Ranking addedRanking = rankingService.registerAMemberInACompetition(rankingRequest);
        return new ResponseEntity<>(addedRanking, HttpStatus.CREATED);
    }
}
