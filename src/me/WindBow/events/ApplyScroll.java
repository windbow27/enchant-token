package me.WindBow.events;

import me.WindBow.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class ApplyScroll implements Listener {

    @EventHandler
    public void repair(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();
            ItemStack scroll = e.getCursor();
            ItemStack item = e.getCurrentItem();

            if (scroll != null && item != null) {
                if (scroll.getType() == Material.PAPER && (item.getType().getMaxDurability() > 5)) {
                    if (scroll.hasItemMeta()) {
                        Damageable ri = (Damageable) item.getItemMeta();
                        if (ri.getDamage() > 0) {
                            new BukkitRunnable() {

                                @Override
                                public void run() {
                                    player.updateInventory();
                                    player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5f, 1f);
                                    player.sendMessage(ChatColor.GREEN + "Your tool has been fixed!");
                                    cancel();
                                }
                            }.runTaskLater(Main.getInstance(), 2);

                            if (scroll.getItemMeta().getLore().contains((ChatColor.WHITE + "500 ") + (ChatColor.GRAY + "durability."))) {
                                scroll.setAmount(scroll.getAmount() - 1);
                                if (ri.getDamage() <= 500) {
                                    ri.setDamage(0);
                                } else {
                                    ri.setDamage(ri.getDamage() - 500);
                                }
                                item.setItemMeta((ItemMeta) ri);

                            } else {
                                if (scroll.getItemMeta().getLore().contains((ChatColor.GOLD + "750 ") + (ChatColor.GRAY + "durability."))) {
                                    scroll.setAmount(scroll.getAmount() - 1);
                                    if (ri.getDamage() <= 750) {
                                        ri.setDamage(0);
                                    } else {
                                        ri.setDamage(ri.getDamage() - 750);
                                    }
                                    item.setItemMeta((ItemMeta) ri);

                                } else {
                                    if (scroll.getItemMeta().getLore().contains((ChatColor.AQUA + "1000 ") + (ChatColor.GRAY + "durability."))) {
                                        scroll.setAmount(scroll.getAmount() - 1);
                                        if (ri.getDamage() <= 1000) {
                                            ri.setDamage(0);
                                        } else {
                                            ri.setDamage(ri.getDamage() - 1000);
                                        }
                                        item.setItemMeta((ItemMeta) ri);

                                    } else {
                                        if (scroll.getItemMeta().getLore().contains((ChatColor.LIGHT_PURPLE + "1500 ") + (ChatColor.GRAY + "durability."))) {
                                            scroll.setAmount(scroll.getAmount() - 1);
                                            if (ri.getDamage() <= 1500) {
                                                ri.setDamage(0);
                                            } else {
                                                ri.setDamage(ri.getDamage() - 1500);
                                            }
                                            item.setItemMeta((ItemMeta) ri);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}