package me.WindBow.enchants;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class RareEvents implements Listener {
    CustomEnchants customEnchants = new CustomEnchants();

    //Random 1-1000
    public int getRandom() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }

    @EventHandler
    public void TreasureHunter(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.TREASURE_HUNTER))
            return;
        if (e.getPlayer().getInventory().firstEmpty() == -1) return;

        Player player = e.getPlayer();
        int chance = getRandom();

        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.TREASURE_HUNTER)) {
            switch (player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(customEnchants.TREASURE_HUNTER)) {
                case 1:
                    if (chance < 51) {
                        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.5f, 1f);
                        player.getInventory().addItem(new ItemStack(Material.COAL, 8));
                        player.getInventory().addItem(new ItemStack(Material.IRON_ORE, 1));
                        player.sendMessage(ChatColor.GRAY + "You gained " + ChatColor.YELLOW +
                                "8 coal, 1 iron ore " + ChatColor.GRAY + "from " + ChatColor.LIGHT_PURPLE + "Treasure Hunter");

                    }
                    break;
                case 2:
                    if (chance < 51) {
                        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.5f, 1f);
                        player.getInventory().addItem(new ItemStack(Material.COAL, 16));
                        player.getInventory().addItem(new ItemStack(Material.IRON_ORE, 2));
                        player.getInventory().addItem(new ItemStack(Material.GOLD_ORE, 1));
                        player.sendMessage(ChatColor.GRAY + "You gained " + ChatColor.YELLOW +
                                "16 coal, 2 iron ore, 1 gold ore " + ChatColor.GRAY + "from " + ChatColor.LIGHT_PURPLE + "Treasure Hunter");
                    }
                    break;
                case 3:
                    if (chance < 51) {
                        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.5f, 1f);
                        player.getInventory().addItem(new ItemStack(Material.COAL, 16));
                        player.getInventory().addItem(new ItemStack(Material.IRON_ORE, 4));
                        player.getInventory().addItem(new ItemStack(Material.GOLD_ORE, 4));
                        player.sendMessage(ChatColor.GRAY + "You gained " + ChatColor.YELLOW +
                                "16 coal, 4 iron ore, 4 gold ore " + ChatColor.GRAY + "from " + ChatColor.LIGHT_PURPLE + "Treasure Hunter");
                    }
                    break;
                case 4:
                    if (chance < 51) {
                        player.playSound(player.getLocation(), Sound.BLOCK_CHEST_OPEN, 0.5f, 1f);
                        player.getInventory().addItem(new ItemStack(Material.COAL, 16));
                        player.getInventory().addItem(new ItemStack(Material.IRON_ORE, 4));
                        player.getInventory().addItem(new ItemStack(Material.GOLD_ORE, 4));
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND, 1));
                        player.sendMessage(ChatColor.GRAY + "You gained " + ChatColor.YELLOW +
                                "16 coal, 4 iron ore, 4 gold ore, 1 diamond " + ChatColor.GRAY + "from " + ChatColor.LIGHT_PURPLE + "Treasure Hunter");
                    }
                    break;
            }
        }
    }
}
