package me.podvorniy.codewarsgame.codewarsgame.game;

import org.bukkit.Location;

public class Generator {

    public Integer generatorLevel;
    public GeneratorType generatorType;

    private Location location;
    public Generator(Integer level, GeneratorType generatorType, Location location) {
        this.generatorLevel = level;
        this.generatorType = generatorType;
        this.location = location;
    }

    public enum GeneratorType {
        TASKS, IRON, GOLD, DIAMOND;
    }

}
