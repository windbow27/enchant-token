package me.WindBow.inventories;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CleanserInv {
    public static CleanserInv instance = new CleanserInv();
    public static CleanserInv getInstance() {
        return instance;
    }

    List<Inventory> invs = new ArrayList<Inventory>();

    public void createCleanser(Player player, Inventory inv) {
        invs.add(inv);
        player.openInventory(inv);

        for (int i = 0; i < 54; i++) {
            inv.setItem(i, setGlass() );
        }
        inv.setItem(4,setBook());
    }

    public ItemStack setGlass() {
        ItemStack enchant = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta encmeta = enchant.getItemMeta();
        assert encmeta != null;
        encmeta.setDisplayName(ChatColor.GREEN + "");
        enchant.setItemMeta(encmeta);
        return enchant;
    }

    public ItemStack setBook() {
        ItemStack enchant = new ItemStack(Material.BOOK);
        ItemMeta encmeta = enchant.getItemMeta();
        assert encmeta != null;

        ArrayList<String> lore = new ArrayList<>();

        encmeta.setDisplayName(ChatColor.GRAY + "Select an enchant to remove:");
        encmeta.addEnchant(Enchantment.LUCK,1,true);
        encmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        encmeta.setLore(lore);
        enchant.setItemMeta(encmeta);
        return enchant;
    }
}
