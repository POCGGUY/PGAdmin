package org.PGAdmin.eventHandlers;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.PGAdmin.savedDataManager.savedDataKeys;

import java.time.Instant;
import java.time.LocalDateTime;

import static org.PGAdmin.Main.sdManager;

public class DragonDeathHandler implements Listener{

    private final String killerNameKey;
    private final String DeathTimeKey;

    public DragonDeathHandler(){
        killerNameKey = savedDataKeys.EndDragonKillerNameKey;
        DeathTimeKey = savedDataKeys.EndDragonDeathTimeKey;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if(event.getEntity().getType() == EntityType.ENDER_DRAGON){
            String killerName = event.getEntity().getKiller().getName();
            if(!sdManager.isDataExist(killerNameKey)) {
                if (killerName != null) {
                    sdManager.saveStringData(killerNameKey, killerName);
                } else sdManager.saveStringData(killerNameKey, "NULL");
            }
            sdManager.saveLongData(DeathTimeKey, Instant.now().toEpochMilli());;
        }
    }
}