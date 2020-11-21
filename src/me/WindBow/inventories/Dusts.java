package me.WindBow.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class Dusts {
    public static Dusts instance = new Dusts();
    public static Dusts getInstance() {
        return instance;
    }

    private int rarity;
    private int percent;

    public int getRandRarity() {
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    public int getRandPercent() {
        Random random = new Random();
        return random.nextInt(20) + 1;
    }

    public String setDisplayLore() {
        rarity = getRandRarity();
        String displayname = "";
        if (1 <= rarity && rarity <= 40) {
            displayname = ChatColor.WHITE + "Enchanting Dust";
        }
        if (41 <= rarity && rarity <= 70) {
            displayname = ChatColor.GOLD + "Enchanting Dust";
        }
        if (71 <= rarity && rarity <= 90) {
            displayname = ChatColor.AQUA + "Enchanting Dust";
        }
        if (91 <= rarity && rarity <= 100) {
            displayname = ChatColor.LIGHT_PURPLE + "Enchanting Dust";
        }
        return displayname;
    }

    public String setRarityLore() {
        String raritylore = "";

        if (1 <= rarity && rarity <= 40) {
            raritylore = (ChatColor.GRAY + "Rarity:") + " " + (ChatColor.WHITE + "Common");
        }
        if (41 <= rarity && rarity <= 70) {
            raritylore = (ChatColor.GRAY + "Rarity:") + " " + (ChatColor.GOLD + "Rare");
        }
        if (71 <= rarity && rarity <= 90) {
            raritylore = (ChatColor.GRAY + "Rarity:") + " " + (ChatColor.AQUA + "Epic");
        }
        if (91 <= rarity && rarity <= 100) {
            raritylore = (ChatColor.GRAY + "Rarity:") + " " + (ChatColor.LIGHT_PURPLE + "Legendary");
        }

        return raritylore;
    }

    public String setDragEnchantLore() {
        String dragelore = "";
        if (1 <= rarity && rarity <= 40) {
            dragelore = (ChatColor.WHITE + "Common ") + (ChatColor.GRAY + "Token to increase");
        }
        if (41 <= rarity && rarity <= 70) {
            dragelore = (ChatColor.GOLD + "Rare ") + (ChatColor.GRAY + "Token to increase");
        }
        if (71 <= rarity && rarity <= 90) {
            dragelore = (ChatColor.AQUA + "Epic ") + (ChatColor.GRAY + "Token to increase");
        }
        if (91 <= rarity && rarity <= 100) {
            dragelore = (ChatColor.LIGHT_PURPLE + "Legendary ") + (ChatColor.GRAY + "Token to increase");
        }

        return dragelore;
    }

    public String setPercent() {
        percent = getRandPercent();
        String percentdisplay = "";
            percentdisplay = (ChatColor.GRAY + "Success Chance Increase: ") + (ChatColor.GREEN + ("" + percent + "%"));
        return percentdisplay;
    }

    public ItemStack getDust() {
        ItemStack dust = new ItemStack(Material.SUGAR);
        ItemMeta dustmeta = dust.getItemMeta();

        ArrayList<String> dustlore = new ArrayList<>();

        assert dustmeta != null;
        dustmeta.setDisplayName(setDisplayLore());

        dustlore.add(setRarityLore());
        dustlore.add(setPercent());
        dustlore.add(ChatColor.DARK_GRAY + "--------------------------");
        dustlore.add(ChatColor.GRAY + "Drag and cick onto an ");
        dustlore.add(setDragEnchantLore());
        dustlore.add(ChatColor.GRAY + "its success chance.");
        dustmeta.addEnchant(Enchantment.LUCK, percent, true);
        dustmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        dustmeta.setLore(dustlore);

        dust.setItemMeta(dustmeta);

        return dust;
    }

    public ItemStack getDust(String setDisplayLore, String setRarityLore, String setPercent,String setDragEnchantLore, int luck) {
        ItemStack dust = new ItemStack(Material.SUGAR);
        ItemMeta dustmeta = dust.getItemMeta();

        ArrayList<String> dustlore = new ArrayList<>();

        assert dustmeta != null;
        dustmeta.setDisplayName(setDisplayLore);

        dustlore.add(setRarityLore);
        dustlore.add(setPercent);
        dustlore.add(ChatColor.DARK_GRAY + "--------------------------");
        dustlore.add(ChatColor.GRAY + "Drag and cick onto an ");
        dustlore.add(setDragEnchantLore);
        dustlore.add(ChatColor.GRAY + "its success chance.");
        dustmeta.addEnchant(Enchantment.LUCK, luck, true);
        dustmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        dustmeta.setLore(dustlore);

        dust.setItemMeta(dustmeta);

        return dust;
    }

}
