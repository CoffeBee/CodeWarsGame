package me.podvorniy.codewarsgame.codewarsgame;

import me.podvorniy.codewarsgame.codewarsgame.game.Game;
import me.podvorniy.codewarsgame.codewarsgame.listeners.joinListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class CodewarsGame extends JavaPlugin {
    private Game game;
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new joinListener(this), this);
        game = new Game(this);
    }
    @Override
    public void onDisable() {

    }

    public void addPlayer(Player player) {
        if (!game.addPLayer(player)) {
            player.kickPlayer("Sorry, no place for you :(");
        }
        player.sendMessage(ChatColor.GREEN + "You entire to codeWars game!");
    }
    public boolean changePlayerTeam(Player player, int teamNumber) {
        return game.changePlayerTeam(player, teamNumber);
    }
    public boolean isGameStarted() {
        return game.isGameStarted();
    }
}
