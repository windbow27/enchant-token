package me.WindBow.inventories;

import me.WindBow.Main;
import me.WindBow.events.ApplyToken;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SpinInv implements Listener {
    public static ItemStack spinitem;
    public static BukkitTask task;
    EnchantItem enchant = new EnchantItem();
    List<Inventory> invs = new ArrayList<Inventory>();

    public void spin(Player player, Inventory inv, ItemStack[] shuffle, int scount) {
        invs.add(inv);
        player.openInventory(inv);
        ApplyToken.hadInv.add(player.getUniqueId());

        task = new BukkitRunnable() {
            int count = 0;
            boolean done = false;

            @Override
            public void run() {
                if (done)
                    return;
                count++;

                for (int itemstacks = 9; itemstacks < 17; itemstacks++) {
                    inv.setItem(itemstacks, inv.getItem(itemstacks + 1));
                }

                inv.setItem(17, shuffle[ThreadLocalRandom.current().nextInt(scount)]);

                player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, 0.7f, 1f);

                if (count >= (18 + ThreadLocalRandom.current().nextInt(10))) {
                    done = true;

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            spinitem = inv.getItem(13);
                            //TokenEvent.tool.addUnsafeEnchantment(Enchantment.LUCK, 1);
                            enchant.enchantItem(player);

                            player.updateInventory();
                            ApplyToken.hadInv.remove(player.getUniqueId());
                            player.closeInventory();
                            player.playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1f, 1f);

                            invs.remove(inv);
                            cancel();
                        }
                    }.runTaskLater(Main.getInstance(), 50);
                    cancel();
                    player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);

                }
            }
        }.runTaskTimer(Main.getInstance(), 0, 3);
    }
}

