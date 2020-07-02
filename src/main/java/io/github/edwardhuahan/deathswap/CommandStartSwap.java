package io.github.edwardhuahan.deathswap;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandStartSwap implements CommandExecutor {

    private final DeathSwap plugin;

    public CommandStartSwap(DeathSwap plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        // Allow use with another player
        if (commandSender instanceof Player && command.getName().equalsIgnoreCase("startswap")) {
            Server server = plugin.getServer();

            ArrayList<Player> players = new ArrayList<Player>();
            players.add((Player) commandSender);
            for (int i = 1; i < args.length; i++) {
                players.add(server.getPlayer(args[i-1]));
            }

            for (Player p : players) {
                p.getInventory().clear();
                p.teleport(RandomLocation.getRandomLocation(p));
                p.setHealth(20);
                p.setFoodLevel(20);
            }


            server.broadcastMessage(ChatColor.BLUE + "Starting deathswap");

            plugin.getGame().startGame(players);
        }
        return true;
    }
}
