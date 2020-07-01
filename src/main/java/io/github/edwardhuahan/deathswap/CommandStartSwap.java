package io.github.edwardhuahan.deathswap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandStartSwap implements CommandExecutor {

    private final DeathSwapPlugin plugin;

    public CommandStartSwap(DeathSwapPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        // Allow use with another player
        if (commandSender instanceof Player && command.getName().equalsIgnoreCase("startswap")) {
            Server server = plugin.getServer();
            Player[] players = new Player[2];
            players[0] = (Player) commandSender;
            players[1] = server.getPlayer(args[0]);

            server.broadcastMessage(ChatColor.RED + "Starting deathswap");

            plugin.getGame().startGame(players);
        }
        return true;
    }
}
