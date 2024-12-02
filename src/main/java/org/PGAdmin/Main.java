package org.PGAdmin;

import org.PGAdmin.commands.*;
import org.PGAdmin.eventHandlers.*;
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
        getCommand("feed").setExecutor(new Feed(this));
        getCommand("heal").setExecutor(new Heal(this));
        getServer().getPluginManager().registerEvents(new FrozenPlayerMoveHandler(), this);
        getServer().getPluginManager().registerEvents(new FrozenPlayerAttackHandler(), this);
        getServer().getPluginManager().registerEvents(new FrozenPlayerInteractHandler(), this);
        getServer().getPluginManager().registerEvents(new FrozenPlayerPlaceBlockHandler(), this);
        getServer().getPluginManager().registerEvents(new FrozenPlayerBreakBlockHandler(), this);
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