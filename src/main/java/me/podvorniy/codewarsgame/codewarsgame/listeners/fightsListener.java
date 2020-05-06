package me.podvorniy.codewarsgame.codewarsgame.listeners;

import me.podvorniy.codewarsgame.codewarsgame.CodewarsGame;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class fightsListener implements Listener {
    private CodewarsGame plugin;
    public fightsListener(CodewarsGame plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onDeath(EntityDamageEvent e) {
        if (!plugin.isGameStarted()) {
            if (!(e.getEntity() instanceof Player)) {
                return;
            }

            Player player = (Player) e.getEntity();
            plugin.respawnPlayer(player);
            e.setCancelled(true);
        }
        else {
            //
        }
    }
}
