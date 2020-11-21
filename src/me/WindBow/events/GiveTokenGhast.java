package me.WindBow.events;

import me.WindBow.inventories.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;


public class GiveTokenGhast implements Listener {

    @EventHandler
    public void test2(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action rightclick = e.getAction();

        if (rightclick == Action.RIGHT_CLICK_AIR || rightclick == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
                player.getInventory().addItem(Dusts.getInstance().getDust());
            }
        }
    }

    @EventHandler
    public void test(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action rightclick = e.getAction();

        if (rightclick == Action.RIGHT_CLICK_AIR || rightclick == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.GHAST_TEAR) {
                player.getInventory().addItem(Tokens.getInstance().getToken());
            }
        }
    }

    @EventHandler
    public void test3(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action rightclick = e.getAction();

        if (rightclick == Action.RIGHT_CLICK_AIR || rightclick == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.ANVIL) {
                player.getInventory().addItem(RepairScrolls.getInstance().getScroll());
            }
        }
    }

    @EventHandler
    public void test4(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action rightclick = e.getAction();

        if (rightclick == Action.RIGHT_CLICK_AIR || rightclick == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.GUNPOWDER) {
                Scrapper.getInstance().createScrapper(player);
            }
        }
    }

    @EventHandler
    public void test5(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action rightclick = e.getAction();

        if (rightclick == Action.RIGHT_CLICK_AIR || rightclick == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.COAL) {
                player.getInventory().addItem(Cleansers.getInstance().getCleanser());
            }
        }
    }

    @EventHandler
    public void test6(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action rightclick = e.getAction();

        if (rightclick == Action.RIGHT_CLICK_AIR || rightclick == Action.RIGHT_CLICK_BLOCK) {
            if (player.getInventory().getItemInMainHand().getType() == Material.BOOK) {
                player.getInventory().getItemInOffHand().removeEnchantment(Enchantment.DIG_SPEED);
                Bukkit.broadcastMessage("test");
            }
        }
    }
}
