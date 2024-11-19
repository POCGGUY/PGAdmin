package org.PGAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class GetPlayerCoordinates implements CommandExecutor {
    private final JavaPlugin plugin;
    public GetPlayerCoordinates(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if((sender instanceof Player && sender.isOp()) || sender instanceof ConsoleCommandSender) {
            try {
                Player player = plugin.getServer().getPlayer(args[0]);
                if (player == null) {
                    sender.sendMessage(ChatColor.RED + "Игрока с таким ником нет на сервере");
                } else {
                    int x = (int)player.getLocation().getX();
                    int y = (int)player.getLocation().getY();
                    int z = (int)player.getLocation().getZ();
                    TextComponent message = new TextComponent("Координаты игрока " + "§b" + args[0] + ChatColor.RESET + ": ");
                    TextComponent coords = new TextComponent(ChatColor.GREEN + "[" + x + " " + y + " " + z + "]");
                    coords.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tp " + x + " " + y + " " + z));
                    message.addExtra(coords);
                    sender.spigot().sendMessage(message);

                }
            }catch (Exception e) {
                sender.sendMessage(ChatColor.RED + "Ошибка: убедитесь правильно ли вы ввели команду: ");
                sender.sendMessage(ChatColor.RED + "/getPlayerCoordinates <ник игрока>");
            }
        } else sender.sendMessage(ChatColor.RED + "У вас нет прав на выполнение данной команды");
        return true;
    }

}
