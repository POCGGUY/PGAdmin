package org.PGAdmin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Feed implements CommandExecutor {
    private final JavaPlugin plugin;

    public Feed(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp()) {
            try {
                String playerName = args[0];
                Player targetPlayer = Bukkit.getPlayer(playerName);
                if (targetPlayer == null) {
                    sender.sendMessage("Игрок с таким ником не найден.");
                    return false;
                }
                targetPlayer.setFoodLevel(20);
                targetPlayer.setSaturation(20);
                sender.sendMessage(ChatColor.GREEN + "Вы успешно насытили игрока: " + targetPlayer.getName());
            }catch (Exception ee) {
                sender.sendMessage(ChatColor.RED + "Ошибка: убедитесь правильно ли вы ввели команду, например: ");
                sender.sendMessage(ChatColor.RED + "/feed <ник игрока>");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "У вас нет прав на использование данной команды");
        }

        return true;
    }
}