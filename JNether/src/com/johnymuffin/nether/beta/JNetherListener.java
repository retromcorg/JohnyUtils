package com.johnymuffin.nether.beta;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class JNetherListener implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (event.getPlayer().getWorld().getEnvironment() == World.Environment.NETHER) {
            if (event.getPlayer().hasPermission("johnyutils.nether.bypass") || event.getPlayer().isOp()) {
                return;
            }

            if (event.getFrom().getY() > 127.0D) {
                event.setCancelled(true);
                event.getPlayer().teleport(Bukkit.getServer().getWorlds().get(0).getSpawnLocation());
                event.getPlayer().sendMessage(ChatColor.RED + "Sorry, players aren't allowed on the nether roof.");
                return;
            }

        }
    }


}
