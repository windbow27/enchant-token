package me.WindBow.events;

import me.WindBow.Main;
import me.WindBow.inventories.Dusts;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ScrapperEvent implements Listener {
    public static Set<UUID> hadInvS = new HashSet<>();

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        if (e.getClickedInventory() == null) return;
        if (e.getCurrentItem() == null) return;
        if (!(hadInvS.contains(e.getWhoClicked().getUniqueId()))) return;
        if (e.getView().getTopInventory().firstEmpty() == -1) {
            if (e.getClickedInventory().equals(e.getView().getBottomInventory())) {
                if ((e.getCurrentItem().getType() == Material.MAGMA_CREAM && e.getCurrentItem().hasItemMeta())) {
                    e.getWhoClicked().sendMessage(ChatColor.RED + "Scrapper is full!");
                    e.setCancelled(true);
                    return;
                }
            }
        }

        Player player = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        Inventory upper = e.getView().getTopInventory();
        Inventory lower = e.getView().getBottomInventory();

        new BukkitRunnable() {

            @Override
            public void run() {
                player.updateInventory();
                cancel();
            }
        }.runTaskLater(Main.getInstance(), 2);

            if (item.getType() == Material.END_CRYSTAL) {
                player.closeInventory();
                hadInvS.remove(player.getUniqueId());
            }

            if (!(item.getType() == Material.MAGMA_CREAM && e.getCurrentItem().hasItemMeta())) {
                e.setCancelled(true);
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        player.updateInventory();
                        cancel();
                    }
                }.runTaskLater(Main.getInstance(), 2);
            }

            if (e.getClickedInventory().getType() == InventoryType.PLAYER) {
                if ((item.getType() == Material.MAGMA_CREAM && e.getCurrentItem().hasItemMeta())) {
                    ItemStack clone = item.clone();
                    item.setAmount(0);
                    int empty = upper.firstEmpty();
                    upper.setItem(empty, clone);

                    int percent = Math.round((float) clone.getItemMeta().getEnchantLevel(Enchantment.LUCK) / 5);
                    if (clone.getItemMeta().getLore().contains((ChatColor.WHITE + "Common ") + (ChatColor.GRAY + "enchant."))) {

                        upper.setItem(empty + 5, Dusts.getInstance().getDust(
                                ChatColor.WHITE + "Enchanting Dust",
                                (ChatColor.GRAY + "Rarity:") + " " + (ChatColor.WHITE + "Common"),
                                (ChatColor.GRAY + "Success Chance Increase: ") + (ChatColor.GREEN + ("" + percent + "%")),
                                (ChatColor.WHITE + "Common ") + (ChatColor.GRAY + "Token to increase"), percent));
                        upper.getItem(empty + 5).setAmount(clone.getAmount());
                    } else {
                        if (clone.getItemMeta().getLore().contains((ChatColor.GOLD + "Rare ") + (ChatColor.GRAY + "enchant."))) {
                            upper.setItem(empty + 5, Dusts.getInstance().getDust(
                                    ChatColor.GOLD + "Enchanting Dust",
                                    (ChatColor.GRAY + "Rarity:") + " " + (ChatColor.GOLD + "Rare"),
                                    (ChatColor.GRAY + "Success Chance Increase: ") + (ChatColor.GREEN + ("" + percent + "%")),
                                    (ChatColor.GOLD + "Rare ") + (ChatColor.GRAY + "Token to increase"), percent));
                            upper.getItem(empty + 5).setAmount(clone.getAmount());
                        } else {
                            if (clone.getItemMeta().getLore().contains((ChatColor.AQUA + "Epic ") + (ChatColor.GRAY + "enchant."))) {
                                upper.setItem(empty + 5, Dusts.getInstance().getDust(
                                        ChatColor.AQUA + "Enchanting Dust",
                                        (ChatColor.GRAY + "Rarity:") + " " + (ChatColor.AQUA + "Epic"),
                                        (ChatColor.GRAY + "Success Chance Increase: ") + (ChatColor.GREEN + ("" + percent + "%")),
                                        (ChatColor.AQUA + "Epic ") + (ChatColor.GRAY + "Token to increase"), percent));
                                upper.getItem(empty + 5).setAmount(clone.getAmount());
                            } else {
                                if (clone.getItemMeta().getLore().contains((ChatColor.LIGHT_PURPLE + "Legendary ") + (ChatColor.GRAY + "enchant."))) {
                                    upper.setItem(empty + 5, Dusts.getInstance().getDust(
                                            ChatColor.LIGHT_PURPLE + "Enchanting Dust",
                                            (ChatColor.GRAY + "Rarity:") + " " + (ChatColor.LIGHT_PURPLE + "Legendary"),
                                            (ChatColor.GRAY + "Success Chance Increase: ") + (ChatColor.GREEN + ("" + percent + "%")),
                                            (ChatColor.LIGHT_PURPLE + "Legendary ") + (ChatColor.GRAY + "Token to increase"), percent));
                                    upper.getItem(empty + 5).setAmount(clone.getAmount());
                                }
                            }
                        }
                    }
                }
            }


            if (e.getClickedInventory().getType() != InventoryType.PLAYER) {
                if ((item.getType() == Material.MAGMA_CREAM && e.getCurrentItem().hasItemMeta())) {
                    ItemStack clone = item.clone();
                    item.setAmount(0);
                    int empty = lower.firstEmpty();
                    lower.setItem(empty, clone);
                    lower.getItem(empty).setAmount(clone.getAmount());
                    upper.setItem(e.getSlot() + 5, new ItemStack(Material.AIR));
                } else {
                    if ((item.getType() == Material.SUGAR && e.getCurrentItem().hasItemMeta())) {
                        ItemStack clone = item.clone();
                        int empty = lower.firstEmpty();
                        item.setAmount(0);
                        lower.setItem(empty, clone);
                        lower.getItem(empty).setAmount(clone.getAmount());
                        upper.setItem(e.getSlot() - 5, new ItemStack(Material.AIR));
                        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 1f);
                    }
                }
            }
        }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        if (!(e.getPlayer() instanceof Player)) return;
        if (!(hadInvS.contains(e.getPlayer().getUniqueId()))) return;
        Player player = (Player) e.getPlayer();
        Inventory inv = e.getView().getTopInventory();
            for (ItemStack item : inv.getContents()) {
                if (item != null) {
                    if (item.getType().equals(Material.MAGMA_CREAM)) {
                        player.getInventory().addItem(item);
                }
            }
        }
        hadInvS.remove(player.getUniqueId());
    }

//    @EventHandler
//    public void onPut(InventoryClickEvent e) {
//        if (e.getClickedInventory() == null) return;
//        if (e.getCurrentItem() == null) return;
//        Player player = (Player) e.getWhoClicked();
//        Inventory inv = e.getInventory();
//
//        if (hadInv.contains(player.getUniqueId())) {
//            if (e.getView().getTitle().equals(ChatColor.GRAY + "Scrapper")) {
//                for (int i = 1; i < 5; i++) {
//                    for (j = i - 1; j < 49; j += 9) {
//                        //inv.getItem(j).getType() == Material.MAGMA_CREAM) {
//                        inv.setItem(j, new ItemStack(Material.MAGMA_CREAM));
//                        inv.setItem(j + 5, new ItemStack(Material.SUGAR));
//                        Bukkit.broadcastMessage("i " + i + "j" + j);
//                    }
//                }
//            }
//        }
//    }
}
