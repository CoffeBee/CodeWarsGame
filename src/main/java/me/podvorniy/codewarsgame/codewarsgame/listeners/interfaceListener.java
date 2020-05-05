package me.podvorniy.codewarsgame.codewarsgame.listeners;

import me.podvorniy.codewarsgame.codewarsgame.CodewarsGame;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class interfaceListener implements Listener {
    private CodewarsGame plugin;
    public interfaceListener(CodewarsGame plugin) {
        this.plugin = plugin;
    }
    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase("Team select")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null){
                return;
            }else if (e.getCurrentItem().getType().equals(Material.RED_WOOL)){
                if (plugin.changePlayerTeam(p, 0)) {
                    p.closeInventory();
                }
            }else if (e.getCurrentItem().getType().equals(Material.BLUE_WOOL)){
                if (plugin.changePlayerTeam(p, 1)) {
                    p.closeInventory();
                }
            } else if (e.getCurrentItem().getType().equals(Material.GREEN_WOOL)){
                if (plugin.changePlayerTeam(p, 2)) {
                    p.closeInventory();
                }
            } else if (e.getCurrentItem().getType().equals(Material.YELLOW_WOOL)){
                if (plugin.changePlayerTeam(p, 3)) {
                    p.closeInventory();
                }
            }
            plugin.getLogger().warning("Some thing went wrong user have element which cannot have!");
        }
    }
    @EventHandler(priority= EventPriority.LOW)
    public void onPlayerUse(PlayerInteractEvent event){
        if (!plugin.isGameStarted()) {
            Player p = event.getPlayer();
            if (p.getInventory().getItemInMainHand().getType() == Material.PAPER){
                p.sendMessage("Here will be rules)))!");
            }
            else if (p.getInventory().getItemInMainHand().getType() == Material.COMPASS) {
                // Here GUI will open)
            }
        }
    }
}
