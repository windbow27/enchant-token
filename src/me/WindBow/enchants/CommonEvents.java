package me.WindBow.enchants;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Random;

public class CommonEvents implements Listener {
    CustomEnchants customEnchants = new CustomEnchants();

    //Random 1-1000
    public int getRandom() {
        Random random = new Random();
        return random.nextInt(1000) + 1;
    }

    @EventHandler
    public void SpeedBoost(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.SPEED_BOOST))
            return;
        if (e.getPlayer().getPotionEffect(PotionEffectType.SPEED) != null) return;

        Player player = e.getPlayer();

        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.SPEED_BOOST)) {

            switch (player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(customEnchants.SPEED_BOOST)) {
                case 1:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 20, 1));
                    break;
                case 2:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 20, 2));
                    break;
                case 3:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 20, 3));
                    break;
            }
        }
    }

    @EventHandler
    public void JumpBoost(BlockBreakEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() == null) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().hasItemMeta()) return;
        if (!e.getPlayer().getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.JUMP_BOOST))
            return;
        if (e.getPlayer().getPotionEffect(PotionEffectType.JUMP) != null) return;

        Player player = e.getPlayer();

        if (player.getInventory().getItemInMainHand().getItemMeta().hasEnchant(customEnchants.JUMP_BOOST)) {

            switch (player.getInventory().getItemInMainHand().getItemMeta().getEnchantLevel(customEnchants.JUMP_BOOST)) {
                case 1:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 20, 1));
                    break;
                case 2:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 20, 2));
                    break;
                case 3:
                    player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 20 * 20, 3));
                    break;
            }
        }
    }
}
