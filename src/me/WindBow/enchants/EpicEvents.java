package me.WindBow.enchants;

import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EpicEvents implements Listener {
    CustomEnchants customEnchants = new CustomEnchants();

    //Random 1-1000
    public int getRandom() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }

    @EventHandler
    public void MinerTreat(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.MINER_TREAT))
            return;

        Player player = e.getPlayer();
        int chance = getRandom();

        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.MINER_TREAT)) {
            switch (player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(customEnchants.MINER_TREAT)) {
                case 1:
                    if (chance < 31) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20, 1));
                        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 1f);
                        player.sendMessage(ChatColor.YELLOW + "Yummy!");
                    }
                    break;
                case 2:
                    if (chance < 36) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20, 1));
                        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 1f);
                        player.sendMessage(ChatColor.YELLOW + "Yummy!");
                    }
                    break;
                case 3:
                    if (chance < 41) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20, 1));
                        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 1f);
                        player.sendMessage(ChatColor.YELLOW + "Yummy!");
                    }
                    break;
                case 4:
                    if (chance < 51) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20, 1));
                        player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EAT, 0.5f, 1f);
                        player.sendMessage(ChatColor.YELLOW + "Yummy!");
                    }
                    break;
            }
        }
    }

    @EventHandler
    public void Haste(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.HASTE))
            return;
        if (e.getPlayer().getPotionEffect(PotionEffectType.FAST_DIGGING) != null) return;

        Player player = e.getPlayer();
        Block block = e.getBlock();
        int chance = getRandom();

        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.HASTE)) {
            switch (player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(customEnchants.HASTE)) {
                case 1:
                    if (chance < 51) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 8, 1));
                    }
                    break;
                case 2:
                    if (chance < 51) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 12, 1));
                    }
                    break;
                case 3:
                    if (chance < 51) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 20 * 12, 2));
                    }
                    break;
            }
        }
    }

    @EventHandler
    public void HugMe(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.MINER_TREAT))
            return;

        Player player = e.getPlayer();
        Block block = e.getBlock();
        int chance = getRandom();

        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.MINER_TREAT)) {
            switch (player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(customEnchants.MINER_TREAT)) {
                case 1:
                    if (chance < 31) {
                        Entity creeper = player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                        creeper.setCustomName(ChatColor.LIGHT_PURPLE + "UwU");
                        player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_HURT, 0.5f, 1f);
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "UwU");
                    }
                    break;
                case 2:
                    if (chance < 36) {
                        Entity creeper = player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                        creeper.setCustomName(ChatColor.LIGHT_PURPLE + "UwU");
                        player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_HURT, 0.5f, 1f);
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "UwU");
                    }
                    break;
                case 3:
                    if (chance < 41) {
                        Entity creeper = player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                        creeper.setCustomName(ChatColor.LIGHT_PURPLE + "UwU");
                        player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_HURT, 0.5f, 1f);
                        player.sendMessage(ChatColor.LIGHT_PURPLE + "UwU");
                    }
                    break;
                case 4:
                    if (chance < 51) {
                        double chance2 = ThreadLocalRandom.current().nextDouble();
                        if (chance2 <= 0.8) {
                            Entity creeper = player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                            creeper.setCustomName(ChatColor.LIGHT_PURPLE + "UwU");
                            player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_HURT, 0.5f, 1f);
                            player.sendMessage(ChatColor.LIGHT_PURPLE + "UwU");
                        } else {
                            //need fix
                            Creeper creeper = (Creeper) player.getWorld().spawnEntity(player.getLocation(), EntityType.CREEPER);
                            creeper.setCustomName(ChatColor.LIGHT_PURPLE + "OwO");
                            creeper.setPowered(true);
                            player.playSound(player.getLocation(), Sound.ENTITY_CREEPER_HURT, 0.5f, 1f);
                            player.sendMessage(ChatColor.LIGHT_PURPLE + "OwO");
                        }
                    }
                    break;
            }
        }
    }

    @EventHandler
    public void Repair(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.REPAIR))
            return;

        Player player = e.getPlayer();
        Block block = e.getBlock();
        int chance = getRandom();

        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.REPAIR)) {
            switch (player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(customEnchants.REPAIR)) {
                case 1:
                    if (chance < 51) {
                        ItemStack ritem = player.getInventory().getItemInMainHand();
                        Damageable ri = (Damageable) ritem.getItemMeta();
                        if (ri.getDamage() <= 2) {
                            ri.setDamage(0);
                        } else {
                            ri.setDamage(ri.getDamage() - 2);
                            ritem.setItemMeta((ItemMeta) ri);
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5f, 1f);
                            player.sendMessage(ChatColor.GREEN + "Your tool has been fixed!");
                        }
                    }
                    break;
                case 2:
                    if (chance < 41) {
                        ItemStack ritem = player.getInventory().getItemInMainHand();
                        Damageable ri = (Damageable) ritem.getItemMeta();
                        if (ri.getDamage() <= 4) {
                            ri.setDamage(0);
                        } else {
                            ri.setDamage(ri.getDamage() - 4);
                            ritem.setItemMeta((ItemMeta) ri);
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5f, 1f);
                            player.sendMessage(ChatColor.GREEN + "Your tool has been fixed!");
                        }
                    }
                    break;
                case 3:
                    if (chance < 41) {
                        ItemStack ritem = player.getInventory().getItemInMainHand();
                        Damageable ri = (Damageable) ritem.getItemMeta();
                        if (ri.getDamage() <= 6) {
                            ri.setDamage(0);
                        } else {
                            ri.setDamage(ri.getDamage() - 6);
                            ritem.setItemMeta((ItemMeta) ri);
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5f, 1f);
                            player.sendMessage(ChatColor.GREEN + "Your tool has been fixed!");
                        }
                    }
                    break;
                case 4:
                    if (chance < 41) {
                        ItemStack ritem = player.getInventory().getItemInMainHand();
                        Damageable ri = (Damageable) ritem.getItemMeta();
                        if (ri.getDamage() <= 8) {
                            ri.setDamage(0);
                        } else {
                            ri.setDamage(ri.getDamage() - 8);
                            ritem.setItemMeta((ItemMeta) ri);
                            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 0.5f, 1f);
                            player.sendMessage(ChatColor.GREEN + "Your tool has been fixed!");
                        }
                    }
                    break;
            }
        }
    }
}

