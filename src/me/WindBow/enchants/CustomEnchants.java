package me.WindBow.enchants;

import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomEnchants {

    //Ideas: Creeper Hug && Miner's Treat

    //Common
    public Enchantment NIGHT_VISION = new EnchantMaker("night_vision", "night_vision", 3);
    public Enchantment JUMP_BOOST = new EnchantMaker("jump_boost", "jump_boost", 3);
    public Enchantment ALCHEMY = new EnchantMaker("alchemy", "alchemy", 4);
    public Enchantment SPEED_BOOST = new EnchantMaker("speed_boost", "speed_boost", 3);
    public Enchantment FREEZE = new EnchantMaker("freeze", "freeze", 3);

    //Rare
    public Enchantment DRILL = new EnchantMaker("drill", "drill", 4);
    public Enchantment SPARE_CHANGE = new EnchantMaker("spare_change", "spare_change", 4);
    public Enchantment REGENERATE = new EnchantMaker("regenerate", "regenerate", 4);
    public Enchantment MIDAS_TOUCH = new EnchantMaker("midas_touch", "midas_touch", 4);
    public Enchantment DYNAMITE_RAIN = new EnchantMaker("dynamite_rain", "dynamite_rain", 4);
    public Enchantment TREASURE_HUNTER = new EnchantMaker("treasure_hunter", "treasure_hunter", 4);

    //Epic
    public Enchantment HASTE = new EnchantMaker("haste", "haste", 3);
    public Enchantment SMELTING = new EnchantMaker("smelting", "smelting", 3);
    public Enchantment MINER_TREAT = new EnchantMaker("miner_treat", "miner_treat", 3);
    public Enchantment HUG_ME = new EnchantMaker("hug_me", "hug_me", 4);
    public Enchantment REPAIR = new EnchantMaker("repair", "repair", 4);
    public Enchantment SLOT = new EnchantMaker("slot", "slot", 4);

    //Legendary
    public Enchantment EXPLOSIVE = new EnchantMaker("explosive", "explosive", 4);
    public Enchantment SUPER_BREAKER = new EnchantMaker("super_breaker", "super_breaker", 4);
    public Enchantment VEIN_MINER = new EnchantMaker("vein_miner", "vein_miner", 4);

    public void register() {

        //Learn how to arraylist this motherfucker
        List<Enchantment> check = Arrays.stream(Enchantment.values()).collect(Collectors.toList());

        if (!check.contains(NIGHT_VISION)) {
            registerEnchant(NIGHT_VISION);
        }
        if (!check.contains(JUMP_BOOST)) {
            registerEnchant(JUMP_BOOST);
        }
        if (!check.contains(ALCHEMY)) {
            registerEnchant(ALCHEMY);
        }
        if (!check.contains(SPEED_BOOST)) {
            registerEnchant(SPEED_BOOST);
        }
        if (!check.contains(FREEZE)) {
            registerEnchant(FREEZE);
        }

        if (!check.contains(DRILL)) {
            registerEnchant(DRILL);
        }
        if (!check.contains(SPARE_CHANGE)) {
            registerEnchant(SPARE_CHANGE);
        }
        if (!check.contains(REGENERATE)) {
            registerEnchant(REGENERATE);
        }
        if (!check.contains(MIDAS_TOUCH)) {
            registerEnchant(MIDAS_TOUCH);
        }
        if (!check.contains(DYNAMITE_RAIN)) {
            registerEnchant(DYNAMITE_RAIN);
        }
        if (!check.contains(TREASURE_HUNTER)) {
            registerEnchant(TREASURE_HUNTER);
        }

        if (!check.contains(HASTE)) {
            registerEnchant(HASTE);
        }
        if (!check.contains(SMELTING)) {
            registerEnchant(SMELTING);
        }
        if (!check.contains(MINER_TREAT)) {
            registerEnchant(MINER_TREAT);
        }
        if (!check.contains(HUG_ME)) {
            registerEnchant(HUG_ME);
        }
        if (!check.contains(REPAIR)) {
            registerEnchant(REPAIR);
        }
        if (!check.contains(SLOT)) {
            registerEnchant(SLOT);
        }

        if (!check.contains(EXPLOSIVE)) {
            registerEnchant(EXPLOSIVE);
        }
        if (!check.contains(SUPER_BREAKER)) {
            registerEnchant(SUPER_BREAKER);
        }
        if (!check.contains(VEIN_MINER)) {
            registerEnchant(VEIN_MINER);
        }

    }

    public void registerEnchant(Enchantment enchantment) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(enchantment);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }
    }
}

