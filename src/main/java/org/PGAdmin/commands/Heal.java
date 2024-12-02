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

public class Heal implements CommandExecutor {
    private final JavaPlugin plugin;

    public Heal(JavaPlugin plugin) {
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
                targetPlayer.setHealth(20);
                targetPlayer.getActivePotionEffects().forEach(effect -> targetPlayer.removePotionEffect(effect.getType()));
                sender.sendMessage(ChatColor.GREEN + "Вы успешно вылечили игрока: " + targetPlayer.getName());
            }catch (Exception ee) {
                sender.sendMessage(ChatColor.RED + "Ошибка: убедитесь правильно ли вы ввели команду, например: ");
                sender.sendMessage(ChatColor.RED + "/heal <ник игрока>");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "У вас нет прав на использование данной команды");
        }

        return true;
    }
}