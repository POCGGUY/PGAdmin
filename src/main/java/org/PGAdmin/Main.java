package org.PGAdmin;

import org.PGAdmin.commands.*;
import org.PGAdmin.eventHandlers.DimensionEnterHandler;
import org.PGAdmin.eventHandlers.DragonDeathHandler;
import org.PGAdmin.eventHandlers.MovementHandler;
import org.PGAdmin.globalVariables.FrozenPlayers;
import org.PGAdmin.savedDataManager.savedDataManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    public static savedDataManager sdManager;
    public static FrozenPlayers frozenPlayers;
    @Override
    public void onEnable() {
        sdManager = new savedDataManager(this);
        frozenPlayers = new FrozenPlayers();
        getCommand("getServerTechInfo").setExecutor(new GetServerTechInfo(this));
        getCommand("setFlySpeed").setExecutor(new SetFlySpeed(this));
        getCommand("setWalkSpeed").setExecutor(new SetWalkSpeed(this));
        getCommand("freeze").setExecutor(new Freeze(this));
        getCommand("unfreeze").setExecutor(new Unfreeze(this));
        getCommand("getPlayerCoordinates").setExecutor(new GetPlayerCoordinates(this));
        getCommand("getPlayerBedCoordinates").setExecutor(new GetPlayerBedCoordinates(this));
        getCommand("getAllPlayers").setExecutor(new GetAllPlayers(this));
        getCommand("getServerProgressInfo").setExecutor(new GetServerProgressInfo(this));
        getCommand("getPlayerInventory").setExecutor(new GetPlayerInventory(this));
        getServer().getPluginManager().registerEvents(new MovementHandler(), this);
        getServer().getPluginManager().registerEvents(new DragonDeathHandler(), this);
        getServer().getPluginManager().registerEvents(new DimensionEnterHandler(), this);
        getLogger().info("PGAdmin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("PGAdmin has been disabled!");
    }
    public static void main(String[] args){
    }

}