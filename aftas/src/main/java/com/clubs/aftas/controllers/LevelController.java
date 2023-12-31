package com.clubs.aftas.controllers;

import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.dtos.level.requests.LevelRequest;
import com.clubs.aftas.entities.Level;
import com.clubs.aftas.entities.Ranking;
import com.clubs.aftas.services.LevelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/levels")
@AllArgsConstructor
public class LevelController {

    private final LevelService levelService;

    @GetMapping
    public List<Level> getLevels() {
        return levelService.getAllLevels();
    }

    @GetMapping("/{levelId}")
    public Level getLevel(@PathVariable Long levelId) {
        return levelService.getLevelById(levelId);
    }
    @GetMapping("/pagination")
    public Page<Level> getLevelsWithPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return levelService.getAllLevelsWithPagination(pageable);
    }

    @PostMapping()
    public ResponseEntity<?> createLevel(@Valid @RequestBody LevelRequest levelRequest) {
        Level addedLevel = levelService.createLevel(levelRequest);
        return new ResponseEntity<>(addedLevel, HttpStatus.CREATED);
    }

    @PutMapping("/{levelId}")
    public ResponseEntity<?> updateLevel(@PathVariable Long levelId,@Valid @RequestBody LevelRequest levelRequest) {
        Level updatedLevel = levelService.updateLevel(levelRequest, levelId);
        return new ResponseEntity<>(updatedLevel, HttpStatus.OK);
    }

    @DeleteMapping("/{levelId}")
    public ResponseEntity<?> deleteLevel(@PathVariable Long levelId) {
       levelService.deleteLevel(levelId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/searchByCriteria")
    public ResponseEntity<?> searchLevelsByCriteria(@RequestBody List<FilterDTO> filters) {
        List<Level> levels = levelService.searchLevelsByCriteria(filters);
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }

    @GetMapping("/search/{value}")
    public ResponseEntity<?> searchLevels(@PathVariable String value) {
        List<Level> levels = levelService.searchLevels(value);
        return new ResponseEntity<>(levels, HttpStatus.OK);
    }
}
