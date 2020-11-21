package me.WindBow.enchants;

import me.WindBow.Main;
import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class LegendaryEvents implements Listener {
    CustomEnchants customEnchants = new CustomEnchants();

    //Random 1-1000
    public int getRandom() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }

    @EventHandler
    public void SuperBreaker(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.EXPLOSIVE))
            return;
        if (e.getPlayer().getPotionEffect(PotionEffectType.FAST_DIGGING) != null) return;

        Player player = e.getPlayer();
        int chance = getRandom();

        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.SUPER_BREAKER)) {
            switch (player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(customEnchants.SUPER_BREAKER)) {
                case 1:
                    if (chance < 31) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 60, 100));
                        player.sendMessage(ChatColor.GREEN + "Super Breaker activated");
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.sendMessage(ChatColor.RED + "Super Breaker deactivated");
                            }
                        }.runTaskLater(Main.getInstance(), 60);
                    }
                    break;
                case 2:
                    if (chance < 36) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 60, 100));
                        player.sendMessage(ChatColor.GREEN + "Super Breaker activated");
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.sendMessage(ChatColor.RED + "Super Breaker deactivated");
                            }
                        }.runTaskLater(Main.getInstance(), 60);
                    }
                    break;
                case 3:
                    if (chance < 41) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 80, 100));
                        player.sendMessage(ChatColor.GREEN + "Super Breaker activated");
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.sendMessage(ChatColor.RED + "Super Breaker deactivated");
                            }
                        }.runTaskLater(Main.getInstance(), 80);
                    }
                    break;
                case 4:
                    if (chance < 51) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 80, 100));
                        player.sendMessage(ChatColor.GREEN + "Super Breaker activated");
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                player.sendMessage(ChatColor.RED + "Super Breaker deactivated");
                            }
                        }.runTaskLater(Main.getInstance(), 80);
                    }
                    break;
            }

        }
    }

    @EventHandler
    public void Explosive(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.EXPLOSIVE)) return;

        Player player = e.getPlayer();
        Block block = e.getBlock();
        int chance = getRandom();

        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.EXPLOSIVE)) {
            switch (player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(customEnchants.EXPLOSIVE)) {
                case 1:
                    if (chance < 31) {
                        player.getWorld().createExplosion(block.getLocation(), 4f, false);
                    }
                    break;
                case 2:
                    if (chance < 36) {
                        player.getWorld().createExplosion(block.getLocation(), 5f, false);
                    }
                    break;
                case 3:
                    if (chance < 41) {
                        player.getWorld().createExplosion(block.getLocation(), 6f, false);
                    }
                    break;
                case 4:
                    if (chance < 51) {
                        player.getWorld().createExplosion(block.getLocation(), 6f, false);
                    }
                    break;
            }
        }
    }

    //Get block radius for vein miner, need research
//    public List<Block> getBlocks(Block start, int radius) {
//        if (radius < 0) {
//            return new ArrayList<Block>(0);
//        }
//        int iterations = (radius * 2) + 1;
//        List<Block> blocks = new ArrayList<Block>(iterations * iterations * iterations);
//        for (int x = -radius; x <= radius; x++) {
//            for (int y = -radius; y <= radius; y++) {
//                for (int z = -radius; z <= radius; z++) {
//                    blocks.add(start.getRelative(x, y, z));
//                }
//            }
//        }
//        return blocks;
//    }
}
