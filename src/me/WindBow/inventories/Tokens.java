package me.WindBow.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class Tokens {
    public static Tokens instance = new Tokens();

    public static Tokens getInstance() {
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
            displayname = ChatColor.WHITE + "" + ChatColor.BOLD + "Common Token";
        }
        if (41 <= rarity && rarity <= 70) {
            displayname = ChatColor.GOLD + "" + ChatColor.BOLD + "Rare Token";
        }
        if (71 <= rarity && rarity <= 90) {
            displayname = ChatColor.AQUA + "" + ChatColor.BOLD + "Epic Token";
        }
        if (91 <= rarity && rarity <= 100) {
            displayname = ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "Legendary Token";
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
            dragelore = (ChatColor.WHITE + "Common ") + (ChatColor.GRAY + "enchant.");
        }
        if (41 <= rarity && rarity <= 70) {
            dragelore = (ChatColor.GOLD + "Rare ") + (ChatColor.GRAY + "enchant.");
        }
        if (71 <= rarity && rarity <= 90) {
            dragelore = (ChatColor.AQUA + "Epic ") + (ChatColor.GRAY + "enchant.");
        }
        if (91 <= rarity && rarity <= 100) {
            dragelore = (ChatColor.LIGHT_PURPLE + "Legendary ") + (ChatColor.GRAY + "enchant.");
        }

        return dragelore;
    }

    public String setPercent() {
        percent = getRandPercent();
        String percentdisplay = "";

        if (1 <= percent && percent <= 20) {
            percentdisplay = (ChatColor.GRAY + "Success Chance: ") + (ChatColor.DARK_RED + ("" + percent + "%"));
        }
        if (21 <= percent && percent <= 40) {
            percentdisplay = (ChatColor.GRAY + "Success Chance: ") + (ChatColor.RED + ("" + percent + "%"));
        }
        if (41 <= percent && percent <= 60) {
            percentdisplay = (ChatColor.GRAY + "Success Chance: ") + (ChatColor.GOLD + ("" + percent + "%"));
        }
        if (61 <= percent && percent <= 80) {
            percentdisplay = (ChatColor.GRAY + "Success Chance: ") + (ChatColor.GREEN + ("" + percent + "%"));
        }
        if (81 <= percent && percent <= 100) {
            percentdisplay = (ChatColor.GRAY + "Success Chance: ") + (ChatColor.DARK_GREEN + ("" + percent + "%"));

        }
        return percentdisplay;
    }

    public ItemStack getToken() {
        ItemStack token = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta tokenmeta = token.getItemMeta();

        ArrayList<String> tokenlore = new ArrayList<>();

        assert tokenmeta != null;
        tokenmeta.setDisplayName(setDisplayLore());

        tokenlore.add(setRarityLore());
        tokenlore.add(setPercent());
        tokenlore.add(ChatColor.DARK_GRAY + "----------------------");
        tokenlore.add(ChatColor.GRAY + "Drag Token onto an item");
        tokenlore.add(ChatColor.GRAY + "for a chance to apply a");
        tokenlore.add(setDragEnchantLore());
        
        tokenmeta.addEnchant(Enchantment.LUCK, percent, true);
        tokenmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        tokenmeta.setLore(tokenlore);

        token.setItemMeta(tokenmeta);

        return token;
    }

}

