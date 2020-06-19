package me.podvorniy.codewarsgame.codewarsgame.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Generator {

    public Integer generatorLevel;
    public GeneratorType generatorType;

    public Location location;
    public Generator(Integer level, GeneratorType generatorType, Location location) {
        this.generatorLevel = level;
        this.generatorType = generatorType;
        this.location = location;
    }
    public int getGeneraionAmount() {
        return 10;
    }
    public ItemStack getMaterial() {
        switch (generatorType) {
            case BOOK:
                return new ItemStack(Material.BOOK, 1);
            case IRON:
                return new ItemStack(Material.IRON_INGOT, 1);
            case GOLD:
                return new ItemStack(Material.GOLD_INGOT, 1);
            case DIAMOND:
                return new ItemStack(Material.DIAMOND, 1);
            case EMERALD:
                return new ItemStack(Material.EMERALD, 1);
            case TASK:
                return new ItemStack(Material.BOOK, 1);
            default:
                return new ItemStack(Material.BOOK, 1);
        }
    }
    public enum GeneratorType {
        TASK, BOOK, IRON, GOLD, DIAMOND, EMERALD;
    }

}
