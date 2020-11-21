package me.WindBow.inventories;

import me.WindBow.events.ScrapperEvent;
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

public class Scrapper {
    public static Scrapper instance = new Scrapper();
    public static Scrapper getInstance() {
        return instance;
    }

    List<Inventory> invs = new ArrayList<Inventory>();

    public void createScrapper(Player player) {
        Inventory inv = Bukkit.createInventory(null, 54, ChatColor.GRAY + "Scrapper");
        invs.add(inv);
        player.openInventory(inv);
        ScrapperEvent.hadInvS.add(player.getUniqueId());

        for (int i = 4; i < 41; i+=9) {
            inv.setItem(i, setGlass() );
        }
        inv.setItem(49, setCrystal());

    }

    public ItemStack setGlass() {
        ItemStack enchant = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta encmeta = enchant.getItemMeta();
        assert encmeta != null;
        encmeta.setDisplayName(ChatColor.GREEN + "");
        enchant.setItemMeta(encmeta);
        return enchant;
    }

    public ItemStack setCrystal() {
        ItemStack enchant = new ItemStack(Material.END_CRYSTAL);
        ItemMeta encmeta = enchant.getItemMeta();
        assert encmeta != null;

        ArrayList<String> lore = new ArrayList<>();

        encmeta.setDisplayName(ChatColor.RED + "Cancel");
        lore.add(ChatColor.GRAY + "Click to cancel ");
        lore.add(ChatColor.GRAY + "scrapping tokens.");
        encmeta.addEnchant(Enchantment.LUCK,1,true);
        encmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        encmeta.setLore(lore);
        enchant.setItemMeta(encmeta);
        return enchant;
    }
}