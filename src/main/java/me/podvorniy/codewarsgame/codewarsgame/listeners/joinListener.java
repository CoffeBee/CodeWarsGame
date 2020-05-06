package me.podvorniy.codewarsgame.codewarsgame.listeners;


import me.podvorniy.codewarsgame.codewarsgame.CodewarsGame;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

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
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        plugin.removePlayer(event.getPlayer());
    }

}