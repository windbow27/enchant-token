package me.WindBow.inventories;

import com.mojang.datafixers.kinds.App;
import me.WindBow.enchants.CustomEnchants;
import me.WindBow.events.ApplyToken;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class EnchantItem {
    CustomEnchants ce = new CustomEnchants();
    List<String> elore = new ArrayList<String>();

    public void checkMeta(ItemStack tool) {
        ItemMeta toolmeta = tool.getItemMeta();
        toolmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        List<String> lore = new ArrayList<String>();
        if (toolmeta.getEnchantLevel(Enchantment.DIG_SPEED) != 0) {
            lore.add(ChatColor.LIGHT_PURPLE + "Efficiency " + toolmeta.getEnchantLevel(Enchantment.DIG_SPEED));
        }
        if (toolmeta.getEnchantLevel(Enchantment.DURABILITY) != 0) {
            lore.add(ChatColor.LIGHT_PURPLE + "Unbreaking " + toolmeta.getEnchantLevel(Enchantment.DURABILITY));
        }
        if (toolmeta.getEnchantLevel(ce.EXPLOSIVE) != 0) {
            lore.add(ChatColor.LIGHT_PURPLE + "Explosive " + toolmeta.getEnchantLevel(ce.EXPLOSIVE));
        }
        if (toolmeta.getEnchantLevel(ce.VEIN_MINER) != 0) {
            lore.add(ChatColor.LIGHT_PURPLE + "Vein Miner " + toolmeta.getEnchantLevel(ce.VEIN_MINER));
        }
        if (toolmeta.getEnchantLevel(ce.SUPER_BREAKER) != 0) {
            lore.add(ChatColor.LIGHT_PURPLE + "Super Breaker " + toolmeta.getEnchantLevel(ce.SUPER_BREAKER));
        }

        if (toolmeta.getEnchantLevel(ce.HASTE) != 0) {
            lore.add(ChatColor.AQUA + "Haste " + toolmeta.getEnchantLevel(ce.HASTE));
        }
        if (toolmeta.getEnchantLevel(ce.SMELTING) != 0) {
            lore.add(ChatColor.AQUA + "Smelting " + toolmeta.getEnchantLevel(ce.SMELTING));
        }
        if (toolmeta.getEnchantLevel(ce.MINER_TREAT) != 0) {
            lore.add(ChatColor.AQUA + "Miner's Treat " + toolmeta.getEnchantLevel(ce.MINER_TREAT));
        }
        if (toolmeta.getEnchantLevel(ce.HUG_ME) != 0) {
            lore.add(ChatColor.AQUA + "Hug Me " + toolmeta.getEnchantLevel(ce.HUG_ME));
        }
        if (toolmeta.getEnchantLevel(ce.REPAIR) != 0) {
            lore.add(ChatColor.AQUA + "Repair " + toolmeta.getEnchantLevel(ce.REPAIR));
        }
        if (toolmeta.getEnchantLevel(ce.SLOT) != 0) {
            lore.add(ChatColor.AQUA + "Slot " + toolmeta.getEnchantLevel(ce.SLOT));
        }

        if (toolmeta.getEnchantLevel(ce.DRILL) != 0) {
            lore.add(ChatColor.GOLD + "Drill " + toolmeta.getEnchantLevel(ce.DRILL));
        }
        if (toolmeta.getEnchantLevel(ce.SPARE_CHANGE) != 0) {
            lore.add(ChatColor.GOLD + "Spare Change " + toolmeta.getEnchantLevel(ce.SPARE_CHANGE));
        }
        if (toolmeta.getEnchantLevel(ce.MIDAS_TOUCH) != 0) {
            lore.add(ChatColor.GOLD + "Midas Touch " + toolmeta.getEnchantLevel(ce.MIDAS_TOUCH));
        }
        if (toolmeta.getEnchantLevel(ce.REGENERATE) != 0) {
            lore.add(ChatColor.GOLD + "Regenerate " + toolmeta.getEnchantLevel(ce.REGENERATE));
        }
        if (toolmeta.getEnchantLevel(ce.DYNAMITE_RAIN) != 0) {
            lore.add(ChatColor.GOLD + "Dynamite Rain " + toolmeta.getEnchantLevel(ce.DYNAMITE_RAIN));
        }
        if (toolmeta.getEnchantLevel(ce.TREASURE_HUNTER) != 0) {
            lore.add(ChatColor.GOLD + "Treasure Hunter " + toolmeta.getEnchantLevel(ce.TREASURE_HUNTER));
        }

        if (toolmeta.getEnchantLevel(ce.NIGHT_VISION) != 0) {
            lore.add(ChatColor.WHITE + "Night Vision " + toolmeta.getEnchantLevel(ce.NIGHT_VISION));
        }
        if (toolmeta.getEnchantLevel(ce.JUMP_BOOST) != 0) {
            lore.add(ChatColor.WHITE + "Jump Boost " + toolmeta.getEnchantLevel(ce.JUMP_BOOST));
        }
        if (toolmeta.getEnchantLevel(ce.ALCHEMY) != 0) {
            lore.add(ChatColor.WHITE + "Alchemy " + toolmeta.getEnchantLevel(ce.ALCHEMY));
        }
        if (toolmeta.getEnchantLevel(ce.SPEED_BOOST) != 0) {
            lore.add(ChatColor.WHITE + "Speed Boost " + toolmeta.getEnchantLevel(ce.SPEED_BOOST));
        }
        if (toolmeta.getEnchantLevel(Enchantment.SILK_TOUCH) != 0) {
            lore.add(ChatColor.WHITE + "Silk Touch 1");
        }
        if (toolmeta.getEnchantLevel(ce.FREEZE) != 0) {
            lore.add(ChatColor.WHITE + "Freeze " + toolmeta.getEnchantLevel(ce.FREEZE));
        }

        toolmeta.setLore(lore);
        tool.setItemMeta(toolmeta);
    }

    public void enchantItem(Player player) {
        if (ApplyToken.tool.getItemMeta().hasLore()) {
            elore = ApplyToken.tool.getItemMeta().getLore();
        }
        switch (SpinInv.spinitem.getType()) {
//Common
            case ENDER_EYE:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.NIGHT_VISION)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.NIGHT_VISION, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 3:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.NIGHT_VISION, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.NIGHT_VISION) + 1);
                        break;
                }
                break;
            case RABBIT_FOOT:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.JUMP_BOOST)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.JUMP_BOOST, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 3:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.JUMP_BOOST, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.JUMP_BOOST) + 1);
                        break;
                }
                break;
            case LINGERING_POTION:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.ALCHEMY)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.ALCHEMY, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.ALCHEMY, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.ALCHEMY) + 1);
                        break;
                }
                break;
            case SUGAR:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.SPEED_BOOST)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.SPEED_BOOST, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 3:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.SPEED_BOOST, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.SPEED_BOOST) + 1);
                        break;
                }
                break;
            case DIAMOND_ORE:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(Enchantment.SILK_TOUCH)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    default:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                }
                break;
            case PACKED_ICE:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.FREEZE)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.FREEZE, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 3:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.FREEZE, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.FREEZE) + 1);
                        break;
                }
                break;
//Rare
            case HOPPER:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.DRILL)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.DRILL, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.DRILL, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.DRILL) + 1);
                        break;
                }
                break;
            case GOLD_NUGGET:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.SPARE_CHANGE)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.SPARE_CHANGE, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.SPARE_CHANGE, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.SPARE_CHANGE) + 1);
                        break;
                }
                break;
            case COMPARATOR:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.REGENERATE)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.REGENERATE, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.REGENERATE, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.REGENERATE) + 1);
                        break;
                }
                break;
            case GOLD_BLOCK:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.MIDAS_TOUCH)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.MIDAS_TOUCH, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.MIDAS_TOUCH, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.MIDAS_TOUCH) + 1);
                        break;
                }
                break;
            case REDSTONE_TORCH:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.DYNAMITE_RAIN)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.DYNAMITE_RAIN, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.DYNAMITE_RAIN, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.DYNAMITE_RAIN) + 1);
                        break;
                }
                break;
            case CHEST:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.TREASURE_HUNTER)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.TREASURE_HUNTER, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.TREASURE_HUNTER, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.TREASURE_HUNTER) + 1);
                        break;
                }
                break;
//Epic
            case GOLDEN_PICKAXE:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.HASTE)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.HASTE, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 3:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.HASTE, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.HASTE) + 1);
                        break;
                }
                break;
            case FURNACE:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.SMELTING)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.SMELTING, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 3:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.SMELTING, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.SMELTING) + 1);
                        break;
                }
                break;
            case COOKIE:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.MINER_TREAT)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.MINER_TREAT, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 3:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.MINER_TREAT, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.MINER_TREAT) + 1);
                        break;
                }
                break;
            case CREEPER_HEAD:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.HUG_ME)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.HUG_ME, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.HUG_ME, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.HUG_ME) + 1);
                        break;
                }
                break;
            case ANVIL:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.REPAIR)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.REPAIR, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.REPAIR, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.REPAIR) + 1);
                        break;
                }
                break;
            case EMERALD:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.SLOT)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.SLOT, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.SLOT, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.SLOT) + 1);
                        break;
                }
                break;
//Legendary
            case GOLDEN_SHOVEL:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(Enchantment.DIG_SPEED)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(Enchantment.DIG_SPEED, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(Enchantment.DIG_SPEED, ApplyToken.tool.getItemMeta().getEnchantLevel(Enchantment.DIG_SPEED) + 1);
                        break;
                }
                break;
            case TNT:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.EXPLOSIVE)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.EXPLOSIVE, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.EXPLOSIVE, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.EXPLOSIVE) + 1);
                        break;
                }
                break;
            case VINE:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.VEIN_MINER)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.VEIN_MINER, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.VEIN_MINER, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.VEIN_MINER) + 1);
                        break;
                }
                break;
            case DIAMOND_PICKAXE:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(ce.SUPER_BREAKER)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(ce.SUPER_BREAKER, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 4:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(ce.SUPER_BREAKER, ApplyToken.tool.getItemMeta().getEnchantLevel(ce.SUPER_BREAKER) + 1);
                        break;
                }
                break;
            case DIAMOND_CHESTPLATE:
                switch (ApplyToken.tool.getItemMeta().getEnchantLevel(Enchantment.DURABILITY)) {
                    case 0:
                        if (elore.size() < 7) {
                            ApplyToken.tool.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
                        } else {
                            player.sendMessage(ChatColor.RED + "You can only have 7 enchants");
                            player.getInventory().addItem(ApplyToken.savetoken);
                        }
                        break;
                    case 3:
                        player.getInventory().addItem(ApplyToken.savetoken);
                        break;
                    default:
                        ApplyToken.tool.addUnsafeEnchantment(Enchantment.DURABILITY, ApplyToken.tool.getItemMeta().getEnchantLevel(Enchantment.DURABILITY) + 1);
                        break;
                }
                break;
        }
        checkMeta(ApplyToken.tool);
        player.getInventory().addItem(ApplyToken.tool);
    }
}
