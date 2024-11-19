package org.PGAdmin.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

public class GetAllPlayers implements CommandExecutor {
    private JavaPlugin plugin;
    public GetAllPlayers(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if((sender instanceof Player && sender.isOp()) || sender instanceof ConsoleCommandSender) {
            OfflinePlayer[] offlinePlayers = Bukkit.getOfflinePlayers();
            sender.sendMessage(ChatColor.GREEN + "Список всех игроков когда-либо заходивших на сервер: ");
            for (int i = 0; i < offlinePlayers.length; i++) {
                sender.sendMessage((i + 1) + ". §b" + offlinePlayers[i].getName() + ChatColor.RESET + " "
                + new SimpleDateFormat("dd/MM/yyyy").format(new Date(offlinePlayers[i].getLastSeen())));
            }
        } else sender.sendMessage(ChatColor.RED + "У вас нет прав на выполнение данной команды");
        return true;
    }
}
