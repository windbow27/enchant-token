package me.WindBow.events;

import me.WindBow.Main;
import me.WindBow.inventories.EnchantItem;
import me.WindBow.inventories.Shuffle;
import me.WindBow.inventories.SpinInv;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ApplyToken implements Listener {
    public static Set<UUID> hadInv = new HashSet<>();
    public static ItemStack savetoken;
    public static ItemStack tool;

    SpinInv spin = new SpinInv();
    Shuffle shuffle = new Shuffle();
    EnchantItem enchant = new EnchantItem();

    @EventHandler
    public void onRightClick(InventoryClickEvent e) {
        if (e.getWhoClicked() instanceof Player) {
            Player player = (Player) e.getWhoClicked();
            ItemStack token = e.getCursor();
            ItemStack currenttool = e.getCurrentItem();

            if (token != null && currenttool != null) {
                if (token.getType() == Material.MAGMA_CREAM &&
                        (currenttool.getType() == Material.WOODEN_PICKAXE
                                || currenttool.getType() == Material.STONE_PICKAXE
                                || currenttool.getType() == Material.IRON_PICKAXE
                                || currenttool.getType() == Material.GOLDEN_PICKAXE
                                || currenttool.getType() == Material.DIAMOND_PICKAXE
                                || currenttool.getType() == Material.NETHERITE_PICKAXE

                                || currenttool.getType() == Material.WOODEN_SHOVEL
                                || currenttool.getType() == Material.STONE_SHOVEL
                                || currenttool.getType() == Material.IRON_SHOVEL
                                || currenttool.getType() == Material.GOLDEN_SHOVEL
                                || currenttool.getType() == Material.DIAMOND_SHOVEL
                                || currenttool.getType() == Material.NETHERITE_SHOVEL

                                || currenttool.getType() == Material.WOODEN_AXE
                                || currenttool.getType() == Material.STONE_AXE
                                || currenttool.getType() == Material.IRON_AXE
                                || currenttool.getType() == Material.GOLDEN_AXE
                                || currenttool.getType() == Material.DIAMOND_AXE
                                || currenttool.getType() == Material.NETHERITE_AXE)) {
                    if (token.hasItemMeta()) {
                        if (token.getItemMeta().hasEnchant(Enchantment.LUCK)) {
                            tool = currenttool.clone();
                            savetoken = token.clone();

                            if (token.getAmount() > 1) {
                                player.sendMessage(ChatColor.RED + "You can't use stacked Tokens");
                                return;
                            }

                            Random r = new Random();
                            int check = r.nextInt(100) + 1;
                            if (token.getEnchantmentLevel(Enchantment.LUCK) < check) {

                                //not bugged anymore, but how
                                token.setAmount(token.getAmount() - 1);
                                Inventory inv = Bukkit.createInventory(null, 9, "");
                                player.openInventory(inv);
                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        player.closeInventory();
                                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5f, 1f);
                                        player.sendTitle(ChatColor.RED + "Enchant Failed", "", 1, 30, 1);
                                        cancel();
                                    }
                                }.runTaskTimer(Main.getInstance(), 0, 20);
                                //

                            } else {
                                currenttool.setAmount(0);
                                Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GRAY + "Enchanting...");

                                if (token.getItemMeta().getLore().contains((ChatColor.WHITE + "Common ") + (ChatColor.GRAY + "enchant."))) {
                                    shuffle.shuffleCommon(inv);
                                    token.setAmount(token.getAmount() - 1);
                                    spin.spin(player, inv, shuffle.contentCommon, 6);
                                } else {
                                    if (token.getItemMeta().getLore().contains((ChatColor.GOLD + "Rare ") + (ChatColor.GRAY + "enchant."))) {
                                        shuffle.shuffleRare(inv);
                                        token.setAmount(token.getAmount() - 1);
                                        spin.spin(player, inv, shuffle.contentRare, 6);
                                    } else {
                                        if (token.getItemMeta().getLore().contains((ChatColor.AQUA + "Epic ") + (ChatColor.GRAY + "enchant."))) {
                                            shuffle.shuffleEpic(inv);
                                            token.setAmount(token.getAmount() - 1);
                                            spin.spin(player, inv, shuffle.contentEpic, 6);
                                        } else {
                                            if (token.getItemMeta().getLore().contains((ChatColor.LIGHT_PURPLE + "Legendary ") + (ChatColor.GRAY + "enchant."))) {
                                                shuffle.shuffleLegendary(inv);
                                                token.setAmount(token.getAmount() - 1);
                                                spin.spin(player, inv, shuffle.contentLegend, 5);
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

    //Cancel stuffs
    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if (!(hadInv.contains(e.getWhoClicked().getUniqueId()))) return;
        if (e.getClickedInventory() == null) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) return;
        if (e.getPlayer() == null) return;
        if (!(hadInv.contains(e.getPlayer().getUniqueId()))) return;
        if (SpinInv.task == null) return;
        Player player = (Player) e.getPlayer();
        Inventory inv = e.getInventory();
        SpinInv.task.cancel();
        ItemStack[] items;
        if (savetoken.getItemMeta().getLore().contains((ChatColor.WHITE + "Common ") + (ChatColor.GRAY + "enchant."))) {
            items = shuffle.contentCommon;
            SpinInv.spinitem = items[ThreadLocalRandom.current().nextInt(6)];
        } else {
            if (savetoken.getItemMeta().getLore().contains((ChatColor.GOLD + "Rare ") + (ChatColor.GRAY + "enchant."))) {
                items = shuffle.contentRare;
                SpinInv.spinitem = items[ThreadLocalRandom.current().nextInt(6)];
            } else {
                if (savetoken.getItemMeta().getLore().contains((ChatColor.AQUA + "Epic ") + (ChatColor.GRAY + "enchant."))) {
                    items = shuffle.contentEpic;
                    SpinInv.spinitem = items[ThreadLocalRandom.current().nextInt(6)];
                } else {
                    if (savetoken.getItemMeta().getLore().contains((ChatColor.LIGHT_PURPLE + "Legendary ") + (ChatColor.GRAY + "enchant."))) {
                        items = shuffle.contentLegend;
                        SpinInv.spinitem = items[ThreadLocalRandom.current().nextInt(5)];
                    }
                }
            }
        }
        enchant.enchantItem(player);
        hadInv.remove(player.getUniqueId());
    }

//    @EventHandler
//    public void onClose(InventoryCloseEvent e) {
//        if (!(e.getPlayer() instanceof Player)) return;
//        if (e.getPlayer() == null) return;
//        if (!(hadInv.contains(e.getPlayer().getUniqueId()))) return;
//        Player player = (Player) e.getPlayer();
//        Inventory inv = e.getInventory();
//        new BukkitRunnable() {
//
//            @Override
//            public void run() {
//                player.openInventory(inv);
//                cancel();
//            }
//        }.runTaskTimer(Main.getInstance(), 0, 10);
//    }

    @EventHandler
    public void onPickUp(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (!(hadInv.contains(e.getEntity().getUniqueId()))) return;
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
        }
    }
}

