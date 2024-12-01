package org.PGAdmin.eventHandlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import static org.PGAdmin.Main.frozenPlayers;

public class FrozenPlayerPlaceBlockHandler implements Listener{

    public FrozenPlayerPlaceBlockHandler(){}
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        Player player = (Player) event.getPlayer();
        if(frozenPlayers.isPlayerFrozen(player.getName()))
        {
              event.setCancelled(true);
              player.sendMessage(ChatColor.RED + "Вы заморожены!");
        }
    }

}