package me.podvorniy.codewarsgame.codewarsgame.game.GUI;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.podvorniy.codewarsgame.codewarsgame.CodewarsGame;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TeamSelect implements InventoryProvider {

    public static SmartInventory getInventory(CodewarsGame plugin) {
        return SmartInventory.builder()
                .id("teamSelect")
                .provider(new TeamSelect(plugin))
                .size(3, 9)
                .title(ChatColor.BLUE + "Team select")
                .build();
    }

    private final Random random = new Random();
    private List<Material>  colors = new ArrayList<>();
    private CodewarsGame plugin;

    public TeamSelect(CodewarsGame plugin) {
        this.plugin = plugin;
    }
    @Override
    public void init(Player player, InventoryContents contents) {
        List<ItemStack> its = plugin.getBlocksForSelect();
        contents.fillBorders(ClickableItem.empty(new ItemStack(Material.BLACK_STAINED_GLASS)));

        contents.set(1, 1, ClickableItem.of(its.get(0), e -> {
            plugin.changePlayerTeam(player, 0);
            TeamSelect.getInventory(plugin).open(player);
        }));

        contents.set(1, 2, ClickableItem.of(its.get(1), e -> {
            plugin.changePlayerTeam(player, 1);
            TeamSelect.getInventory(plugin).open(player);
        }));

        contents.set(1, 3, ClickableItem.of(its.get(2), e -> {
            plugin.changePlayerTeam(player, 2);
            TeamSelect.getInventory(plugin).open(player);
        }));

        contents.set(1, 4, ClickableItem.of(its.get(3), e -> {
            plugin.changePlayerTeam(player, 3);
            TeamSelect.getInventory(plugin).open(player);
        }));
        colors = Arrays.asList(new Material[]{Material.WHITE_STAINED_GLASS_PANE, Material.RED_STAINED_GLASS_PANE, Material.BLUE_STAINED_GLASS_PANE,});
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        if(state % 10 != 0)
            return;

        ItemStack glass = new ItemStack(colors.get(state / 10 % 3), 1);
        contents.fillBorders(ClickableItem.empty(glass));
    }

}
