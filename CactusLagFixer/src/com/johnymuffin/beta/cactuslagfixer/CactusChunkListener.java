package com.johnymuffin.beta.cactuslagfixer;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import java.util.Arrays;
import java.util.List;

public class CactusChunkListener implements Listener {

    private CactusLagFixer plugin;

    public CactusChunkListener(CactusLagFixer plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        if (event.isNewChunk()) {
            return;
        }
        List<Entity> entityList = Arrays.asList(event.getChunk().getEntities());
        int count = 0;
        for (Entity e : entityList) {
            if (e instanceof Item) {
                Item item = (Item) e;
                if (item.getItemStack().getType() == Material.CACTUS) {
                    count = count + 1;
                    e.remove();
                }
            }
        }
    }
}
