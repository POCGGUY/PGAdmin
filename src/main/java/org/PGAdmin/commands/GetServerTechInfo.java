package org.PGAdmin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class GetServerTechInfo implements CommandExecutor {
    private final JavaPlugin plugin;

    public GetServerTechInfo(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if((sender instanceof Player && sender.isOp()) || sender instanceof ConsoleCommandSender) {
                Runtime runtime = Runtime.getRuntime();
                int onlinePlayers = plugin.getServer().getOnlinePlayers().size();
                long maxMemory = runtime.maxMemory() / (1024 * 1024);
                long allocatedMemory = runtime.totalMemory() / (1024 * 1024);
                long availableMemory = maxMemory - allocatedMemory;
                double tps = plugin.getServer().getTPS()[0];
                ChatColor availableMemoryColor;
                if(((double) availableMemory / (double) maxMemory) <= 0.5){
                    availableMemoryColor = ChatColor.YELLOW;
                }
                else if(((double) availableMemory / (double) maxMemory) <= 0.2){
                    availableMemoryColor = ChatColor.RED;
                }
                else availableMemoryColor = ChatColor.GREEN;

                sender.sendMessage("§aТехническая информация о сервере:");
                sender.sendMessage("§bДоступное ОЗУ: §f" + availableMemoryColor + availableMemory + ChatColor.RESET + " MB");
                sender.sendMessage("§bМаксимальное ОЗУ: §f" + maxMemory + " MB");
                sender.sendMessage("§bТики в секунду (TPS): §f" + String.format("%.2f", tps));
                sender.sendMessage("§bКоличество игроков онлайн: ");

                return true;
        }
        else sender.sendMessage(ChatColor.RED + "У вас нет прав на выполнение данной команды");
        return true;
    }
}