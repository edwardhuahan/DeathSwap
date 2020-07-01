package io.github.edwardhuahan.deathswap;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandStopSwap implements CommandExecutor {

    private final DeathSwapPlugin plugin;

    public CommandStopSwap(DeathSwapPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("startswap")) {
            plugin.getServer().broadcastMessage(ChatColor.RED + "Starting deathswap");

            plugin.getGame().stopGame();
        }
        return true;
    }
}
