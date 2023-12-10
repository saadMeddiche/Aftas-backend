package com.clubs.aftas.services;

import com.clubs.aftas.entities.Level;

import java.util.List;

public interface LevelService {

        List<Level> getAllLevels();

        Level getLevelById(Long id);

        Level addLevel(Level level);

        void createDefaultLevels();

}
