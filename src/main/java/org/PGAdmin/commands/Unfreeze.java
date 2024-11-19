package org.PGAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import static org.PGAdmin.Main.frozenPlayers;

public class Unfreeze implements CommandExecutor {
    private final JavaPlugin plugin;

    public Unfreeze(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if((sender instanceof Player && sender.isOp()) || sender instanceof ConsoleCommandSender) {
            try {
                if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
                    if (frozenPlayers.deleteFrozenPlayer(args[0])) {
                        sender.sendMessage(ChatColor.GREEN + "Вы успешно разморозили игрока: " + args[0] + ChatColor.RESET);
                    } else {
                        sender.sendMessage(ChatColor.RED + "Игрок с таким ником не заморожен" + ChatColor.RESET);
                    }
                }
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Ошибка: убедитесь правильно ли вы ввели команду: ");
                sender.sendMessage(ChatColor.RED + "/unfreeze <ник игрока>");
            }
        } else sender.sendMessage(ChatColor.RED + "У вас нет прав на выполнение данной команды");
        return true;
    }
}
