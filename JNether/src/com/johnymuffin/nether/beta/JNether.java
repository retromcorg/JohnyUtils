package com.johnymuffin.nether.beta;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JNether extends JavaPlugin {
    //Basic Plugin Info
    private static JNether plugin;
    private Logger log;
    private String pluginName;
    private PluginDescriptionFile pdf;

    @Override
    public void onEnable() {
        plugin = this;
        log = this.getServer().getLogger();
        pdf = this.getDescription();
        pluginName = pdf.getName();
        log.info("[" + pluginName + "] Is Loading, Version: " + pdf.getVersion());

        JNetherListener jNetherListener = new JNetherListener();
        Bukkit.getServer().getPluginManager().registerEvents(jNetherListener, plugin);

    }

    @Override
    public void onDisable() {

    }

    public void logger(Level level, String message) {
        Bukkit.getLogger().log(level, "[" + pluginName + "] " + message);
    }

}
