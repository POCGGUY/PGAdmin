package org.PGAdmin.eventHandlers;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.PGAdmin.savedDataManager.savedDataKeys;
import org.bukkit.event.player.PlayerChangedWorldEvent;

import java.time.Instant;


import static org.PGAdmin.Main.sdManager;

public class DimensionEnterHandler implements Listener{

    private final String NetherWhoEnteredNameKey;
    private final String NetherEnteringTimeKey;
    private final String EndWhoEnteredNameKey;
    private final String EndEnteringTimeKey;

    public DimensionEnterHandler(){
        NetherWhoEnteredNameKey = savedDataKeys.NetherWhoEnteredNameKey;
        NetherEnteringTimeKey = savedDataKeys.NetherEnteringTimeKey;
        EndWhoEnteredNameKey = savedDataKeys.EndWhoEnteredNameKey;
        EndEnteringTimeKey = savedDataKeys.EndEnteringTimeKey;
    }

    @EventHandler
    public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
        String worldName = event.getPlayer().getWorld().getName();
        String playerName = event.getPlayer().getName();
        if(worldName.equals("world_nether") && !sdManager.isDataExist(NetherWhoEnteredNameKey))
        {
            sdManager.saveStringData(NetherWhoEnteredNameKey, playerName);
            sdManager.saveLongData(NetherEnteringTimeKey, Instant.now().toEpochMilli());
        }
        else if(worldName.equals("world_the_end") && !sdManager.isDataExist(EndWhoEnteredNameKey))
        {
            sdManager.saveStringData(EndWhoEnteredNameKey, playerName);
            sdManager.saveLongData(EndEnteringTimeKey, Instant.now().toEpochMilli());
        }
    }
}