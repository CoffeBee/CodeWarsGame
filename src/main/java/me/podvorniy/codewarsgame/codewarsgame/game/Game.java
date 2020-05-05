package me.podvorniy.codewarsgame.codewarsgame.game;

import me.podvorniy.codewarsgame.codewarsgame.CodewarsGame;
import me.podvorniy.codewarsgame.codewarsgame.utils.TitleAPI;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import java.util.*;

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

    private List<String> colorsName;
    private List<Material> colorsBlocks;
    private List<ChatColor> colorsCharColor;
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

        this.colorsName = Arrays.asList(new String[]{"Red", "Blue", "Green", "Yellow"});
        this.colorsBlocks = Arrays.asList(new Material[]{Material.RED_WOOL, Material.BLUE_WOOL, Material.GREEN_WOOL, Material.YELLOW_WOOL});
        this.colorsCharColor = Arrays.asList(new ChatColor[]{ChatColor.RED, ChatColor.BLUE, ChatColor.GREEN, ChatColor.YELLOW});

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
        player.getInventory().clear();

        ItemStack rulePapper = new ItemStack(Material.PAPER, 1);
        ItemMeta rulePapperMeta = rulePapper.getItemMeta();
        rulePapperMeta.setDisplayName(ChatColor.DARK_RED + "Magic paper with game rules");
        rulePapperMeta.setLore(Arrays.asList(ChatColor.RED + "Click to read codewars ruls"));
        rulePapper.setItemMeta(rulePapperMeta);
        player.getInventory().addItem(rulePapper);

        ItemStack teamSelector = new ItemStack(Material.COMPASS, 1);
        ItemMeta teamSelectorMeta = teamSelector.getItemMeta();
        teamSelectorMeta.setDisplayName(ChatColor.DARK_RED + "This compass can help with team selecting");
        teamSelectorMeta.setLore(Arrays.asList(ChatColor.RED + "Click to select tema"));
        teamSelector.setItemMeta(teamSelectorMeta);
        player.getInventory().addItem(teamSelector);



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

    public boolean changePlayerTeam(Player p, int teamNumber) {
        if (teams.get(teamNumber).size() == playersPerTeam) {
            p.sendMessage("Sorry, but that team is full");
            return false;
        }
        for (int i = 0; i < teamNumber; i++) {
            if (teams.get(i).contains(p)) {
                teams.get(i).remove(p);
                teams.get(teamNumber).add(p);
                return true;
            }
        }
        plugin.getLogger().warning("Some thing went wrong, player isn't concist in any team!! FIX IT!");
        return false;
    }
    public boolean isGameStarted() {
        return gameState == GameState.ACTIVE;
    }
    public enum GameState {
        LOBBY, ACTIVE
    }
    private void runSpawners() {

    }
    private void teleportAllPlayersToTheirSpawns() {
        for (int i = 0; i < teamNumber; i++) {
            for (int j = 0; j < teams.get(teamNumber).size(); j++) {
                teams.get(i).get(j).teleport(spawns.get(i));
                TitleAPI.sendTitle(teams.get(i).get(j), 20, 40, 20, "CodeWars game has been started", "Good game and good taks!");
            }
        }
    }
    private void activateGame() {
        runSpawners();
        teleportAllPlayersToTheirSpawns();
        gameState = GameState.ACTIVE;
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
