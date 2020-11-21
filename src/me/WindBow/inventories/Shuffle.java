package me.WindBow.inventories;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Random;

public class Shuffle {

    public ItemStack[] contentCommon;
    public ItemStack[] contentRare;
    public ItemStack[] contentEpic;
    public ItemStack[] contentLegend;

    public int getRandomShuffle(int count) {
        Random rshuffle = new Random();
        return rshuffle.nextInt(count);
    }

    public void shuffleCommon(Inventory inv) {
        if (contentCommon == null) {
            ItemStack[] items = new ItemStack[6];
            items[0] = setLore(Material.ENDER_EYE, ChatColor.WHITE + "Night Vision",
                    ChatColor.WHITE + "Common", "3", "batman");
            items[1] = setLore(Material.RABBIT_FOOT, ChatColor.WHITE + "Jump Boost",
                    ChatColor.WHITE + "Common", "3", "rabbit");
            items[2] = setLore(Material.LINGERING_POTION, ChatColor.WHITE + "Alchemy",
                    ChatColor.WHITE + "Common", "4", "uhh");
            items[3] = setLore(Material.SUGAR, ChatColor.WHITE + "Speed Boost",
                    ChatColor.WHITE + "Common", "3", "sonic");
            items[4] = setLore(Material.DIAMOND_ORE, ChatColor.WHITE + "Silk Touch",
                    ChatColor.WHITE + "Common", "1", "boring");
            items[5] = setLore(Material.PACKED_ICE, ChatColor.WHITE + "Freeze",
                    ChatColor.WHITE + "Common", "3", "perfecto FREEZEu");

            contentCommon = items;
        }

        for (int itemstacks = 9; itemstacks < 18; itemstacks++) {
            inv.setItem(itemstacks, contentCommon[getRandomShuffle(6)]);

        }

        ItemStack hopper = setGlass("|", Material.HOPPER);
        inv.setItem(4, hopper);
        ItemStack glass = setGlass("", Material.WHITE_STAINED_GLASS_PANE);
        for (int i = 0; i < 4; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 5; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 18; i < 27; i++) {
            inv.setItem(i, glass);
        }

    }

    public void shuffleRare(Inventory inv) {
        if (contentRare == null) {
            ItemStack[] items = new ItemStack[6];
            items[0] = setLore(Material.HOPPER, ChatColor.GOLD + "Drill",
                    ChatColor.GOLD + "Rare", "4", "noises");
            items[1] = setLore(Material.GOLD_NUGGET, ChatColor.GOLD + "Spare Change",
                    ChatColor.GOLD + "Rare", "4", "moni");
            items[2] = setLore(Material.COMPARATOR, ChatColor.GOLD + "Regenerate",
                    ChatColor.GOLD + "Rare", "4", "idk");
            items[3] = setLore(Material.GOLD_BLOCK, ChatColor.GOLD + "Midas Touch",
                    ChatColor.GOLD + "Rare", "4", "Rich");
            items[4] = setLore(Material.REDSTONE_TORCH, ChatColor.GOLD + "Dynamite Rain",
                    ChatColor.GOLD + "Rare", "4", "raining hard");
            items[5] = setLore(Material.CHEST, ChatColor.GOLD + "Treasure Hunter",
                    ChatColor.GOLD + "Rare", "4", "hunterhunter");

            contentRare = items;

        }

        for (int itemstacks = 9; itemstacks < 18; itemstacks++) {
            inv.setItem(itemstacks, contentRare[getRandomShuffle(6)]);

        }

        ItemStack hopper = setGlass("|", Material.HOPPER);
        inv.setItem(4, hopper);
        ItemStack glass = setGlass("", Material.YELLOW_STAINED_GLASS_PANE);
        for (int i = 0; i < 4; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 5; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 18; i < 27; i++) {
            inv.setItem(i, glass);
        }

    }

    public void shuffleEpic(Inventory inv) {
        if (contentEpic == null) {
            ItemStack[] items = new ItemStack[6];
            items[0] = setLore(Material.GOLDEN_PICKAXE, ChatColor.AQUA + "Haste",
                    ChatColor.AQUA + "Epic", "3", "Break faster");
            items[1] = setLore(Material.FURNACE, ChatColor.AQUA + "Smelting",
                    ChatColor.AQUA + "Epic", "3", "smelt");
            items[2] = setLore(Material.COOKIE, ChatColor.AQUA + "Miner's Treat",
                    ChatColor.AQUA + "Epic", "3", "yumyum");
            items[3] = setLore(Material.CREEPER_HEAD, ChatColor.AQUA + "Hug me",
                    ChatColor.AQUA + "Epic", "4", "shhhhhh");
            items[4] = setLore(Material.ANVIL, ChatColor.AQUA + "Repair",
                    ChatColor.AQUA + "Epic", "4", "ding ding");
            items[5] = setLore(Material.EMERALD, ChatColor.AQUA + "Slot",
                    ChatColor.AQUA + "Epic", "4", "money go brrr");

            contentEpic = items;

        }

        for (int itemstacks = 9; itemstacks < 18; itemstacks++) {
            inv.setItem(itemstacks, contentEpic[getRandomShuffle(6)]);

        }

        ItemStack hopper = setGlass("|", Material.HOPPER);
        inv.setItem(4, hopper);
        ItemStack glass = setGlass("", Material.LIGHT_BLUE_STAINED_GLASS_PANE);
        for (int i = 0; i < 4; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 5; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 18; i < 27; i++) {
            inv.setItem(i, glass);
        }

    }

    public void shuffleLegendary(Inventory inv) {
        if (contentLegend == null) {
            ItemStack[] items = new ItemStack[5];
            items[0] = setLore(Material.GOLDEN_SHOVEL, ChatColor.LIGHT_PURPLE + "Efficiency",
                    ChatColor.LIGHT_PURPLE + "Legendary", "4", "Break faster");
            items[1] = setLore(Material.TNT, ChatColor.LIGHT_PURPLE + "Explosive",
                    ChatColor.LIGHT_PURPLE + "Legendary", "4", "Boom");
            items[2] = setLore(Material.VINE, ChatColor.LIGHT_PURPLE + "Vein Miner",
                    ChatColor.LIGHT_PURPLE + "Legendary", "4", "uhhh");
            items[3] = setLore(Material.DIAMOND_PICKAXE, ChatColor.LIGHT_PURPLE + "Super Breaker",
                    ChatColor.LIGHT_PURPLE + "Legendary", "4", "Instant break");
            items[4] = setLore(Material.DIAMOND_CHESTPLATE, ChatColor.LIGHT_PURPLE + "Unbreaking",
                    ChatColor.LIGHT_PURPLE + "Legendary", "3", "Longer life");

            contentLegend = items;

        }

        for (int itemstacks = 9; itemstacks < 18; itemstacks++) {
            inv.setItem(itemstacks, contentLegend[getRandomShuffle(5)]);

        }

        ItemStack hopper = setGlass("|", Material.HOPPER);
        inv.setItem(4, hopper);
        ItemStack glass = setGlass("", Material.MAGENTA_STAINED_GLASS_PANE);
        for (int i = 0; i < 4; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 5; i < 9; i++) {
            inv.setItem(i, glass);
        }
        for (int i = 18; i < 27; i++) {
            inv.setItem(i, glass);
        }

    }

    public ItemStack setLore(Material mat, String name, String rarity, String level, String enclore) {
        ItemStack enchant = new ItemStack(mat);
        ItemMeta encmeta = enchant.getItemMeta();
        assert encmeta != null;
        ArrayList<String> lore = new ArrayList<>();

        encmeta.setDisplayName(name);
        lore.add(ChatColor.GRAY + "Rarity: " + rarity);
        lore.add((ChatColor.GRAY + "Max Level: ") + (ChatColor.YELLOW + level));
        lore.add("");
        lore.add(enclore);

        encmeta.setLore(lore);
        enchant.setItemMeta(encmeta);
        return enchant;
    }

    public ItemStack setGlass(String name, Material mat) {
        ItemStack enchant = new ItemStack(mat);
        ItemMeta encmeta = enchant.getItemMeta();
        assert encmeta != null;
        encmeta.setDisplayName(ChatColor.AQUA + "" + ChatColor.BOLD + name);
        encmeta.addEnchant(Enchantment.LUCK, 1, false);
        encmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        enchant.setItemMeta(encmeta);
        return enchant;
    }
}
