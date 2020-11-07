package com.johnymuffin.jcmd.beta;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JCMDBlock extends JavaPlugin implements Listener {
    //Basic Plugin Info
    private static JCMDBlock plugin;
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
        Bukkit.getServer().getPluginManager().registerEvents(plugin, plugin);
    }

    @Override
    public void onDisable() {

    }

    public void logger(Level level, String message) {
        Bukkit.getLogger().log(level, "[" + pluginName + "] " + message);
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (event.getMessage().equalsIgnoreCase("/town unclaim rect") || event.getMessage().equalsIgnoreCase("/town unclaim circle") || event.getMessage().equalsIgnoreCase("/town claim auto") || event.getMessage().startsWith("/ptml")) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "Command disabled");
        }
    }
}
