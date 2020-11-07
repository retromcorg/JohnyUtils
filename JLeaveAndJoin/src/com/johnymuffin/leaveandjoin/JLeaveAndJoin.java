package com.johnymuffin.leaveandjoin;

import com.johnymuffin.jperms.beta.JohnyPerms;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JLeaveAndJoin extends JavaPlugin implements Listener {
    //Basic Plugin Info
    private static JLeaveAndJoin plugin;
    private Logger log;
    private String pluginName;
    private PluginDescriptionFile pdf;
    private JLJConfig jljConfig;

    @Override
    public void onEnable() {
        plugin = this;
        log = this.getServer().getLogger();
        pdf = this.getDescription();
        pluginName = pdf.getName();
        log.info("[" + pluginName + "] Is Loading, Version: " + pdf.getVersion());
        jljConfig = new JLJConfig(plugin);
        Bukkit.getServer().getPluginManager().registerEvents(plugin, plugin);
    }

    @Override
    public void onDisable() {

    }

    public void logger(Level level, String message) {
        Bukkit.getLogger().log(level, "[" + pluginName + "] " + message);
    }


    public String getPrefix(Player player) {
        if (Bukkit.getPluginManager().isPluginEnabled("JPerms")) {
            if (JohnyPerms.getJPermsAPI().getUser(player.getUniqueId()).getGroup().getPrefix() != null) {
                return JohnyPerms.getJPermsAPI().getUser(player.getUniqueId()).getGroup().getPrefix();
            }
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PermissionsEx")) {
            PermissionManager permissionManager = PermissionsEx.getPermissionManager();
            if (permissionManager.getUser(player).getPrefix() != null) {
                return permissionManager.getUser(player).getPrefix();
            }
        }
        return "&f[&4Unknown&f]";


    }

    public String formatChat(String chat) {
        return chat.replace("&", "\u00a7");
    }


    public String insetGroupAndUsername(String string, Player player) {
        string = string.replace("%username%", player.getName());
        string = string.replace("%prefix%", getPrefix(player));
        return string;
    }


    @EventHandler(ignoreCancelled = true)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(formatChat(insetGroupAndUsername(jljConfig.getConfigString("join-message"), event.getPlayer())));
    }


    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage(formatChat(insetGroupAndUsername(jljConfig.getConfigString("leave-message"), event.getPlayer())));
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerKick(PlayerKickEvent event) {
        event.setLeaveMessage(formatChat(insetGroupAndUsername(jljConfig.getConfigString("kick-message"), event.getPlayer())));
    }
}
