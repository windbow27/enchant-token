package me.WindBow.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class RepairScrolls {
    public static RepairScrolls instance = new RepairScrolls();

    public static RepairScrolls getInstance() {
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
        return random.nextInt(100) + 1;
    }

    public String setDisplayLore() {
        rarity = getRandRarity();
        String displayname = "";
        if (1 <= rarity && rarity <= 40) {
            displayname = ChatColor.WHITE + "" + ChatColor.BOLD + "Common REPAIR Scroll";
        }
        if (41 <= rarity && rarity <= 70) {
            displayname = ChatColor.GOLD + "" + ChatColor.BOLD + "Rare REPAIR Scroll";
        }
        if (71 <= rarity && rarity <= 90) {
            displayname = ChatColor.AQUA + "" + ChatColor.BOLD + "Epic REPAIR Scroll";
        }
        if (91 <= rarity && rarity <= 100) {
            displayname = ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Legendary REPAIR Scroll";
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
            dragelore = (ChatColor.WHITE + "500 ") + (ChatColor.GRAY + "durability.");
        }
        if (41 <= rarity && rarity <= 70) {
            dragelore = (ChatColor.GOLD + "750 ") + (ChatColor.GRAY + "durability.");
        }
        if (71 <= rarity && rarity <= 90) {
            dragelore = (ChatColor.AQUA + "1000 ") + (ChatColor.GRAY + "durability.");
        }
        if (91 <= rarity && rarity <= 100) {
            dragelore = (ChatColor.LIGHT_PURPLE + "1500 ") + (ChatColor.GRAY + "durability.");
        }

        return dragelore;
    }

    public ItemStack getScroll() {
        ItemStack scroll = new ItemStack(Material.PAPER);
        ItemMeta scrollmeta = scroll.getItemMeta();

        ArrayList<String> scrolllore = new ArrayList<>();

        assert scrollmeta != null;
        scrollmeta.setDisplayName(setDisplayLore());

        scrolllore.add(setRarityLore());
        scrolllore.add(ChatColor.DARK_GRAY + "--------------------------");
        scrolllore.add(ChatColor.GRAY + "Drag and click onto an item");
        scrolllore.add(ChatColor.GRAY + "to fix the item for");
        scrolllore.add(setDragEnchantLore());

        scrollmeta.setLore(scrolllore);
        scroll.setItemMeta(scrollmeta);

        return scroll;
    }
}
