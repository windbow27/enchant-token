package me.WindBow.events;

import me.WindBow.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ApplyDust implements Listener {
    public ItemStack clonedust;

    @EventHandler
    public void onRightClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();
            ItemStack dust = e.getCursor();
            ItemStack token = e.getCurrentItem();

            if (dust != null && token != null) {
                if (dust.getType() == Material.SUGAR && (token.getType() == Material.MAGMA_CREAM)) {
                    if (dust.hasItemMeta() && token.hasItemMeta()) {
                        if (dust.containsEnchantment(Enchantment.LUCK) && token.containsEnchantment(Enchantment.LUCK)) {
                            if (token.getItemMeta().getEnchantLevel(Enchantment.LUCK) < 100) {
                                if (token.getItemMeta().getLore().contains((ChatColor.WHITE + "Common ") + (ChatColor.GRAY + "enchant."))
                                        && dust.getItemMeta().getLore().contains((ChatColor.WHITE + "Common ") + (ChatColor.GRAY + "Token to increase"))) {
                                    clonedust = dust.clone();
                                    dust.setAmount(dust.getAmount() - 1);
                                    onDust(token, player);
                                } else {
                                    if (token.getItemMeta().getLore().contains((ChatColor.GOLD + "Rare ") + (ChatColor.GRAY + "enchant."))
                                            && dust.getItemMeta().getLore().contains((ChatColor.GOLD + "Rare ") + (ChatColor.GRAY + "Token to increase"))) {
                                        clonedust = dust.clone();
                                        dust.setAmount(dust.getAmount() - 1);
                                        onDust(token, player);
                                    } else {
                                        if (token.getItemMeta().getLore().contains((ChatColor.AQUA + "Epic ") + (ChatColor.GRAY + "enchant."))
                                                && dust.getItemMeta().getLore().contains((ChatColor.AQUA + "Epic ") + (ChatColor.GRAY + "Token to increase"))) {
                                            clonedust = dust.clone();
                                            dust.setAmount(dust.getAmount() - 1);
                                            onDust(token, player);
                                        } else {
                                            if (token.getItemMeta().getLore().contains((ChatColor.LIGHT_PURPLE + "Legendary ") + (ChatColor.GRAY + "enchant."))
                                                    && dust.getItemMeta().getLore().contains((ChatColor.LIGHT_PURPLE + "Legendary ") + (ChatColor.GRAY + "Token to increase"))) {
                                                clonedust = dust.clone();
                                                dust.setAmount(dust.getAmount() - 1);
                                                onDust(token, player);
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

    public void onDust(ItemStack token, Player player) {
        new BukkitRunnable() {

            @Override
            public void run() {
                player.updateInventory();
                player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                cancel();
            }
        }.runTaskLater(Main.getInstance(), 2);

        ItemMeta tokenmeta = token.getItemMeta();

        int sum = tokenmeta.getEnchantLevel(Enchantment.LUCK) + clonedust.getItemMeta().getEnchantLevel(Enchantment.LUCK);

        //Bukkit.broadcastMessage("test " + sum);

        if (sum < 100) {
            tokenmeta.addEnchant(Enchantment.LUCK, sum, true);
        } else {
            tokenmeta.addEnchant(Enchantment.LUCK, 100, true);
        }

        //Bukkit.broadcastMessage("test 2 " + tokenmeta.getEnchantLevel(Enchantment.LUCK));
        List<String> lore = tokenmeta.getLore();

        if (1 <= sum && sum <= 20) {
            lore.set(1, (ChatColor.GRAY + "Success Chance: ") + (ChatColor.DARK_RED + ("" + sum + "%")));
        } else {
            if (21 <= sum && sum <= 40) {
                lore.set(1, (ChatColor.GRAY + "Success Chance: ") + (ChatColor.RED + ("" + sum + "%")));
            } else {
                if (41 <= sum && sum <= 60) {
                    lore.set(1, (ChatColor.GRAY + "Success Chance: ") + (ChatColor.GOLD + ("" + sum + "%")));
                } else {
                    if (61 <= sum && sum <= 80) {
                        lore.set(1, (ChatColor.GRAY + "Success Chance: ") + (ChatColor.GREEN + ("" + sum + "%")));
                    } else {
                        if (81 <= sum && sum < 100) {
                            lore.set(1, (ChatColor.GRAY + "Success Chance: ") + (ChatColor.DARK_GREEN + ("" + sum + "%")));
                        } else {
                            lore.set(1, (ChatColor.GRAY + "Success Chance: ") + (ChatColor.DARK_GREEN + ("" + 100 + "%")));
                        }
                    }
                }
            }
        }

        //tokenmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        tokenmeta.setLore(lore);
       // Bukkit.broadcastMessage("lore" + lore.get(1).toString());
        token.setItemMeta(tokenmeta);
    }
}