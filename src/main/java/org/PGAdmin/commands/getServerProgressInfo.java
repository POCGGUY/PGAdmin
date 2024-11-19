package org.PGAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import static org.PGAdmin.Main.sdManager;
import org.PGAdmin.savedDataManager.savedDataKeys;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetServerProgressInfo implements CommandExecutor {
    private final JavaPlugin plugin;

    public GetServerProgressInfo(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player || sender instanceof ConsoleCommandSender) {
            sender.sendMessage(ChatColor.GREEN + "Текущий прогресс на сервере:");
            String NetherEnteredName = sdManager.getStringData(savedDataKeys.NetherWhoEnteredNameKey);
            Long NetherEnterTime = sdManager.getLongData(savedDataKeys.NetherEnteringTimeKey);
            String EndEnteredName = sdManager.getStringData(savedDataKeys.EndWhoEnteredNameKey);
            Long EndEnterTime = sdManager.getLongData(savedDataKeys.EndEnteringTimeKey);
            String DragonKillerName = sdManager.getStringData(savedDataKeys.EndDragonKillerNameKey);
            Long DragonKillTime = sdManager.getLongData(savedDataKeys.EndDragonDeathTimeKey);
            if(!sdManager.isDataExist(savedDataKeys.NetherWhoEnteredNameKey))
            {
                sender.sendMessage(ChatColor.YELLOW + "В Нижнем Мире ещё никого не было");
            }
            else if(NetherEnteredName != "EMPTY" && NetherEnterTime != 0){
                sender.sendMessage("§b" + "Первым в Нижний Мир попал: " + NetherEnteredName + " ["
                + new SimpleDateFormat("dd/MM/yyyy").format(new Date(NetherEnterTime)) + "]");
            }
            if(!sdManager.isDataExist(savedDataKeys.EndWhoEnteredNameKey)){
                sender.sendMessage(ChatColor.YELLOW + "В Краю ещё никого не было");
            }
            else if(EndEnteredName != "EMPTY" && EndEnterTime != 0){
                sender.sendMessage("§b" + "Первым в Край попал: " + EndEnteredName + " ["
                + new SimpleDateFormat("dd/MM/yyyy").format(new Date(EndEnterTime)) + "]");
            }
            if(!sdManager.isDataExist(savedDataKeys.EndDragonKillerNameKey)){
                sender.sendMessage(ChatColor.YELLOW + "Дракона Края ещё никто не убивал");
            }
            else if(DragonKillerName != "EMPTY" && DragonKillTime != 0){
                sender.sendMessage("§b" + "Первым кто убил Дракона Края был: " + DragonKillerName + " ["
                + new SimpleDateFormat("dd/MM/yyyy").format(new Date(DragonKillTime)) + "]");
            }

            return true;
        }
        return false;
    }
}
