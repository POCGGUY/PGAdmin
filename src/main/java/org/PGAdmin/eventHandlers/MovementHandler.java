package org.PGAdmin.eventHandlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import static org.PGAdmin.Main.frozenPlayers;

public class MovementHandler implements Listener{

    public MovementHandler(){}
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if((event.getFrom().getY() < event.getTo().getY() || event.getFrom().getY() > event.getTo().getY()
               || event.getFrom().getX() < event.getTo().getX() || event.getFrom().getX() > event.getTo().getX()
               || event.getFrom().getZ() > event.getTo().getZ() || event.getFrom().getZ() < event.getTo().getZ())
               && frozenPlayers.isPlayerFrozen(player.getName()))
        {
              event.setCancelled(true);
              player.sendMessage(ChatColor.RED + "Вы заморожены!");
        }
    }
}