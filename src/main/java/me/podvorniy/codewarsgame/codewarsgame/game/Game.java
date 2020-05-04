package me.podvorniy.codewarsgame.codewarsgame.game;

import me.podvorniy.codewarsgame.codewarsgame.CodewarsGame;
import me.podvorniy.codewarsgame.codewarsgame.utils.TitleAPI;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game {
    private CodewarsGame plugin;

    private GameState gameState = GameState.LOBBY;

    private Location lobbyPoint;
    private Integer minPLayerNumber;
    private Integer playersPerTeam;
    private Integer teamNumber;
    private List<Location> spawns;
    private List<Generator> generators;

    private List<Player> players = new ArrayList<>();
    private List<List<Player>> teams;
    private List<Integer> score;
    private Map<Generator, Integer> generatorsTaskId = new HashMap<>();


    public Game(CodewarsGame plugin) {
        this.plugin = plugin;
        this.initGenerators();;
        this.initLobby();
        this.initNumbers();
        this.initSpawns();
        teams = new ArrayList<>();
        for (int i = 0; i < teamNumber; i++) {
            teams.add(new ArrayList<>());
        }
        score = new ArrayList<>();
        for (int i = 0; i < teamNumber; i++) {
            score.add(0);
        }
    }

    public boolean addPLayer(Player player) {
        if (gameState != GameState.LOBBY) {
            return false;
        }
        if (teamNumber * playersPerTeam == players.size()) {
            return false;
        }
        players.add(player);
        player.teleport(lobbyPoint);
        player.setGameMode(GameMode.ADVENTURE);
        if (players.size() == minPLayerNumber) {
            BukkitScheduler scheduler = plugin.getServer().getScheduler();
            scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    activateGame();
                }
            }, 20 * 20L);
        }
        for (int i = 0; i < teamNumber; i++) {
            if (teams.get(i).size() != playersPerTeam) {
                teams.get(i).add(player);
                return true;
            }
        }
        plugin.getLogger().warning("!!!!!!PLayer didn't added");
        return false;
    }


    public enum GameState {
        LOBBY, ACTIVE
    }
    private void runSpawners() {
        BukkitScheduler scheduler = plugin.getServer().getScheduler();
        for (int i = 0; i < generators.size(); i++) {
            Integer spawnId =  scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    activateGame();
                }
            }, 20L);
            generatorsTaskId.put(generators.get(i), spawnId);
        }
    }
    private void activateGame() {
        runSpawners();
        // Telepor players to spawns and writing them message
        for (int i = 0; i < teamNumber; i++) {
            for (int j = 0; j < teams.get(teamNumber).size(); j++) {
                teams.get(i).get(j).teleport(spawns.get(i));
                TitleAPI.sendTitle(teams.get(i).get(j), 20, 40, 20, "CodeWars game has been started", "Good game and good taks!");
            }
        }

    }
    private void initNumbers() {

    }
    private void initSpawns() {

    }
    private void initLobby() {

    }
    private void initGenerators() {

    }
}
