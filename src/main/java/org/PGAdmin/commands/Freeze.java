package org.PGAdmin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

import static org.PGAdmin.Main.frozenPlayers;


public class Freeze implements CommandExecutor {
    private final JavaPlugin plugin;
    public Freeze(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if((sender instanceof Player && sender.isOp()) || sender instanceof ConsoleCommandSender) {
            try {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(ChatColor.RED + "Игрока с таким ником нет на сервере" + ChatColor.RESET);
                } else if (frozenPlayers.isPlayerFrozen(args[0])) {
                    sender.sendMessage(ChatColor.RED + "Данный игрок уже заморожен" + ChatColor.RESET);
                } else if (sender instanceof Player || sender instanceof ConsoleCommandSender) {
                    frozenPlayers.addFrozenPlayer(args[0]);
                    sender.sendMessage(ChatColor.GREEN + "Вы успешно заморозили игрока " + args[0] + ChatColor.RESET);
                }
            } catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Ошибка: убедитесь правильно ли вы ввели команду: ");
                sender.sendMessage(ChatColor.RED + "/freeze <ник игрока>");
            }
        } else sender.sendMessage(ChatColor.RED + "У вас нет прав на выполнение данной команды");
        return true;
    }
}
