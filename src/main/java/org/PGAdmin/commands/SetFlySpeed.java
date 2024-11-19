package org.PGAdmin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class SetFlySpeed implements CommandExecutor {
    private final JavaPlugin plugin;
    public SetFlySpeed(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if((sender instanceof Player && sender.isOp()) || sender instanceof ConsoleCommandSender) {
            try {
                if (args.length == 2 && Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(ChatColor.RED + "Игрока с таким ником нет на сервере" + ChatColor.RESET);
                } else if (sender instanceof Player && args.length == 1) {
                    ((Player) sender).setFlySpeed(Float.parseFloat(args[0]) / 10);
                    sender.sendMessage(ChatColor.GREEN + "Вы успешно установили себе скорость полёта: " + args[0] + ChatColor.RESET);
                } else if (sender instanceof Player || sender instanceof ConsoleCommandSender && args.length == 2) {
                    plugin.getServer().getPlayer(args[0]).setFlySpeed(Float.parseFloat(args[1]) / 10);
                    sender.sendMessage(ChatColor.GREEN + "Вы успешно установили для " + args[0] + " скорость полёта: " + args[1] + ChatColor.RESET);
                }
            } catch (Exception e) {
                try {
                    if ((args.length == 2 && (Float.parseFloat(args[1]) > 10)) || (args.length == 2 && (Float.parseFloat(args[1]) < -10))) {
                        sender.sendMessage(ChatColor.RED + "Ошибка: допустимы лишь значения в диапозоне от -10 до 10" + ChatColor.RESET);
                    } else if ((args.length == 1 && (Float.parseFloat(args[0]) > 10)) || (args.length == 1 && (Float.parseFloat(args[0]) < -10))) {
                        sender.sendMessage(ChatColor.RED + "Ошибка: допустимы лишь значения в диапозоне от -10 до 10" + ChatColor.RESET);
                    }
                } catch (Exception ee) {
                    sender.sendMessage(ChatColor.RED + "Ошибка: убедитесь правильно ли вы ввели команду, например: ");
                    sender.sendMessage(ChatColor.RED + "/setflyspeed <ник игрока> <скорость полёта> - если вы хотите установить скорость конкретному игроку ");
                    sender.sendMessage(ChatColor.RED + "/setflyspeed <скорость полёта> - если вы хотите установить скорость себе (консоль не может ставить скорость себе) ");
                }
            }
        } else sender.sendMessage(ChatColor.RED + "У вас нет прав на выполнение данной команды");
        return true;
    }
}
