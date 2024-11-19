package org.PGAdmin.commands;

import net.kyori.adventure.text.ComponentLike;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class GetPlayerBedCoordinates implements CommandExecutor {
    private final JavaPlugin plugin;
    public GetPlayerBedCoordinates(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if((sender instanceof Player && sender.isOp()) || sender instanceof ConsoleCommandSender) {
            try {
                OfflinePlayer player = plugin.getServer().getOfflinePlayer(args[0]);
                if (!player.hasPlayedBefore()) {
                    sender.sendMessage(ChatColor.RED + "Игрок с ником " + args[0] + " никогда не заходил на сервер.");
                    return true;
                }
                if (player.getBedSpawnLocation() == null) {
                    sender.sendMessage(ChatColor.RED + "У игрока " + args[0] + " нет установленной точки возрождения.");
                    return true;
                }
                int x = (int)player.getBedSpawnLocation().getX();
                int y = (int)player.getBedSpawnLocation().getY();
                int z = (int)player.getBedSpawnLocation().getZ();
                TextComponent message = new TextComponent("Координаты кровати игрока " + "§b" + args[0] + ChatColor.RESET + ": ");
                TextComponent coords = new TextComponent(ChatColor.GREEN + "[" + x + " " + y + " " + z + "]");
                coords.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp " + x + " " + y + " " + z));
                message.addExtra(coords);
                sender.spigot().sendMessage(message);

            }catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Ошибка: убедитесь правильно ли вы ввели команду: ");
                sender.sendMessage(ChatColor.RED + "/getPlayerBedCoordinates <ник игрока>");
            }
        } else sender.sendMessage(ChatColor.RED + "У вас нет прав на выполнение данной команды");
        return true;
    }

}