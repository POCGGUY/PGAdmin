package org.PGAdmin.eventHandlers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.ChatColor;

import static org.PGAdmin.Main.frozenPlayers;

public class FrozenPlayerAttackHandler implements Listener{

    public FrozenPlayerAttackHandler(){}
    @EventHandler
    public void onPlayerAttack(EntityDamageByEntityEvent event){
        if(event.getDamager() instanceof Player){
            Player attacker = (Player) event.getDamager();
            if(frozenPlayers.isPlayerFrozen(attacker.getName())) {
                event.setCancelled(true);
                attacker.sendMessage(ChatColor.RED + "Вы заморожены!");
            }
        }
    }
}
