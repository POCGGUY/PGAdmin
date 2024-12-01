package org.PGAdmin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

public class GetPlayerInventory implements CommandExecutor {
    private final JavaPlugin plugin;

    public GetPlayerInventory(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player && sender.isOp()) {
            try {
                String playerName = args[0];
                Player targetPlayer = Bukkit.getPlayer(playerName);
                if (targetPlayer == null) {
                    sender.sendMessage("Игрок с таким ником не найден.");
                    return false;
                }
                Inventory inventory = targetPlayer.getInventory();
                Player viewer = (Player) sender;
                viewer.openInventory(inventory);
            }catch (Exception ee) {
                sender.sendMessage(ChatColor.RED + "Ошибка: убедитесь правильно ли вы ввели команду, например: ");
                sender.sendMessage(ChatColor.RED + "/getplayerinventory <ник игрока>");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "У вас нет прав на выполнение данной команды, либо вы пытаетесь использовать её с консоли");
        }

        return true;
    }
}