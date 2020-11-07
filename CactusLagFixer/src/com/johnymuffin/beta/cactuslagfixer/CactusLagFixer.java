package com.johnymuffin.beta.cactuslagfixer;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class CactusLagFixer extends JavaPlugin {
    private CactusLagFixer plugin;
    private Logger log;
    private String pluginName;
    private PluginDescriptionFile pdf;
    private int taskId = 0;

    @Override
    public void onEnable() {
        plugin = this;
        log = this.getServer().getLogger();
        pdf = this.getDescription();
        pluginName = pdf.getName();
        //Listeners
        final CactusChunkListener CLF = new CactusChunkListener(plugin);
        getServer().getPluginManager().registerEvents(CLF, plugin);

        //Ground Cactus Clearer
        taskId = Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(plugin, () -> Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            int count = 0;
            for (World world : Bukkit.getServer().getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    if (entity instanceof Item) {
                        //Check if cactus
                        Item item = (Item) entity;
                        if (item.getItemStack().getType() == Material.CACTUS) {
                            count = count + 1;
                            entity.remove();
                        }
                    }
                }
                ;
            }
        }, 0L), 0L, 20 * 60 * 2);


        log.info("[" + pluginName + "] has Been Loaded, Version: " + pdf.getVersion());
    }

    @Override
    public void onDisable() {
        log.info("[" + pdf.getName() + "] Has Been Disabled");
        Bukkit.getServer().getScheduler().cancelTask(taskId);
    }

    public void LogMessage(String s) {
        log.info("[" + pdf.getName() + "] " + s);
    }
}
