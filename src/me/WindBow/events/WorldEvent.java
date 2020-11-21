package me.WindBow.events;

import me.WindBow.inventories.RepairScrolls;
import me.WindBow.inventories.Tokens;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.concurrent.ThreadLocalRandom;

public class WorldEvent implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().firstEmpty() == -1) return;
        if (e.getPlayer().getGameMode() == GameMode.CREATIVE || e.getPlayer().getGameMode() == GameMode.SPECTATOR)
            return;

        Player player = e.getPlayer();
        Block block = e.getBlock();

//        e.setDropItems(false);
//
//        //give block
//        Collection<ItemStack> drops = block.getDrops(player.getInventory().getItemInMainHand());
//        if (drops.isEmpty()) return;
//        player.getInventory().addItem(drops.iterator().next());

        //give Scroll
        int check = ThreadLocalRandom.current().nextInt((1500 + 1));
        if (check == 27) {
            player.sendTitle(ChatColor.GREEN + "You found a REPAIR Scroll!", "", 1, 30, 1);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
            player.getInventory().addItem(RepairScrolls.instance.getScroll());
        }

        //give Token
        int check2 = ThreadLocalRandom.current().nextInt((500) + 1);
        if (check2 == 27) {
            player.sendTitle(ChatColor.GREEN + "You found a Token!", "", 1, 30, 1);
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f);
            player.getInventory().addItem(Tokens.getInstance().getToken());
        }
    }

    //Cancel all damage
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player)
            e.setCancelled(true);
    }

}
