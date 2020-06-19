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

public class ShopInterface implements InventoryProvider {

    public static SmartInventory getInventory(CodewarsGame plugin) {
        return SmartInventory.builder()
                .id("shopInterface")
                .provider(new ShopInterface(plugin))
                .size(5, 9)
                .title(ChatColor.BLUE + "SHOP")
                .build();
    }

    private final Random random = new Random();
    private List<Material>  colors = new ArrayList<>();
    private CodewarsGame plugin;

    public ShopInterface(CodewarsGame plugin) {
        this.plugin = plugin;
    }
    @Override
    public void init(Player player, InventoryContents contents) {

        contents.set(2, 2, ClickableItem.of(new ItemStack(Material.GRAY_GLAZED_TERRACOTTA), e -> {
            player.sendMessage("Blocks");
        }));
        contents.set(2, 3, ClickableItem.of(new ItemStack(Material.IRON_SWORD), e -> {
            player.sendMessage("Sword");
        }));
        contents.set(2, 4, ClickableItem.of(new ItemStack(Material.LEATHER_CHESTPLATE), e -> {
            player.sendMessage("Armor");
        }));
        contents.set(2, 5, ClickableItem.of(new ItemStack(Material.BOW), e -> {
            player.sendMessage("Bows");
        }));
        contents.set(2, 6, ClickableItem.of(new ItemStack(Material.APPLE), e -> {
            player.sendMessage("Food");
        }));
        contents.set(1, 4, ClickableItem.of(new ItemStack(Material.EMERALD), e -> {
            player.sendMessage("Specials");
        }));

        colors = Arrays.asList(new Material[]{Material.WHITE_STAINED_GLASS_PANE, Material.RED_STAINED_GLASS_PANE, Material.BLUE_STAINED_GLASS_PANE,});
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }

}
