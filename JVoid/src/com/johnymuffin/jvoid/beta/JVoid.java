package com.johnymuffin.jvoid.beta;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JVoid extends JavaPlugin implements Listener {
    //Basic Plugin Info
    private static JVoid plugin;
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
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getTo().getY() < -6) {
            event.getPlayer().teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
            event.getPlayer().sendMessage(ChatColor.RED + "You have been teleported to spawn as you fell into the void.");
        }
    }

}
