package com.clubs.aftas.services;

import com.clubs.aftas.dtos.FilterDTO;
import com.clubs.aftas.dtos.level.requests.LevelRequest;
import com.clubs.aftas.dtos.level.requests.LevelRequest;
import com.clubs.aftas.entities.Level;
import com.clubs.aftas.entities.Level;
import com.clubs.aftas.entities.Member;
import com.clubs.aftas.entities.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LevelService {

        public List<Level> getAllLevels();

        public Page<Level> getAllLevelsWithPagination(Pageable pageable);

        public Level getLevelById(Long id);

        public Level createLevel(LevelRequest levelRequest);

        public Level updateLevel(LevelRequest level , Long levelId);

        void createDefaultLevels();

        public void deleteLevel(Long id);

        public List<Level> searchLevelsByCriteria(List<FilterDTO> filters);

        public List<Level> searchLevels(String value);

}
