package me.WindBow.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class Cleansers {
    public static Cleansers instance = new Cleansers();
    private int percent;

    public static Cleansers getInstance() { return instance; }

    public int getRandPercent() {
        Random random = new Random();
        return random.nextInt(100) + 1;
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

    public ItemStack getCleanser() {
        ItemStack cleanser = new ItemStack(Material.REDSTONE);
        ItemMeta cleansermeta = cleanser.getItemMeta();

        ArrayList<String> cleanserlore = new ArrayList<>();

        assert cleansermeta != null;
        cleansermeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Enchant Cleanser");

        cleanserlore.add(setPercent());
        cleanserlore.add(ChatColor.DARK_GRAY + "----------------------");
        cleanserlore.add(ChatColor.GRAY + "Drag Cleanser onto an item");
        cleanserlore.add(ChatColor.GRAY + "to remove an enchant.");
        cleanserlore.add(ChatColor.GRAY + "If it failed, remove a");
        cleanserlore.add(ChatColor.GRAY + "random enchant.");

        cleansermeta.addEnchant(Enchantment.LUCK, percent, true);
        cleansermeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        cleansermeta.setLore(cleanserlore);

        cleanser.setItemMeta(cleansermeta);

        return cleanser;
    }
}
