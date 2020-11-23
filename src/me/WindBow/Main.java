package me.WindBow;

import me.WindBow.commands.EnchantTest;
import me.WindBow.enchants.*;
import me.WindBow.events.*;
import me.WindBow.inventories.SpinInv;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static Main instance;

    public static Main getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        CustomEnchants customEnchants = new CustomEnchants();
        customEnchants.register();
        instance = this;
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Enabled");
        getServer().getPluginManager().registerEvents(new WorldEvent(), this);
        getServer().getPluginManager().registerEvents(new ScrapperEvent(), this);
        getServer().getPluginManager().registerEvents(new GiveTokenGhast(), this);
        getServer().getPluginManager().registerEvents(new ApplyToken(), this);
        getServer().getPluginManager().registerEvents(new ApplyCleanser(), this);
        getServer().getPluginManager().registerEvents(new ApplyDust(), this);
        getServer().getPluginManager().registerEvents(new ApplyScroll(), this);
        getServer().getPluginManager().registerEvents(new CommonEvents(), this);
        getServer().getPluginManager().registerEvents(new RareEvents(), this);
        getServer().getPluginManager().registerEvents(new EpicEvents(), this);
        getServer().getPluginManager().registerEvents(new LegendaryEvents(), this);
        getServer().getPluginManager().registerEvents(new SpinInv(), this);
        getCommand("explosive").setExecutor(new EnchantTest());

    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "Disabled");

    }
}
