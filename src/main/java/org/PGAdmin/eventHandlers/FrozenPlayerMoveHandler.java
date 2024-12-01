package org.PGAdmin.eventHandlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import static org.PGAdmin.Main.frozenPlayers;

public class FrozenPlayerMoveHandler implements Listener{

    public FrozenPlayerMoveHandler(){}
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(frozenPlayers.isPlayerFrozen(player.getName()))
        {
              event.setCancelled(true);
              player.sendMessage(ChatColor.RED + "Вы заморожены!");
        }
    }

}