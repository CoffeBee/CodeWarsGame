package me.podvorniy.codewarsgame.codewarsgame.listeners;


import me.podvorniy.codewarsgame.codewarsgame.CodewarsGame;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class joinListener implements Listener
{
    private CodewarsGame plugin;
    public joinListener(CodewarsGame plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        plugin.addPlayer(event.getPlayer());
    }
}