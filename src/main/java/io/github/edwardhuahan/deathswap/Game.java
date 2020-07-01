package io.github.edwardhuahan.deathswap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class Game {

    private final JavaPlugin plugin;

    private static int timer;
    private static int delay;
    private static int warntime;

    private static boolean running;
    private static boolean firstRun;

    public Game(JavaPlugin plugin) {
        this.plugin = plugin;
        firstRun = true;
    }

    public void startGame(Player[] players) {
        players[0].getLocation();
        timer = 0;
        warntime = 10;
        delay = 300;
        running = true;

        if (firstRun) {
            new BukkitRunnable() {

                @Override
                public void run() {
                    timer++;

                    if (running) {
                        if ((delay-timer)<=warntime) {
                            plugin.getServer().broadcastMessage(ChatColor.BLUE + "Swapping in " + (delay-timer) + " seconds");
                        }
                        if (delay-timer<=0) {
                            Location[] locations = {players[0].getLocation(), players[1].getLocation()};

                            timer = 0;
                            teleport(players, locations);

                        }
                    }

                }
            }.runTaskTimerAsynchronously(plugin, 0, 20);
            firstRun = false;
        }
    }

    public void stopGame() {
        running = false;
        plugin.getServer().broadcastMessage("Stopped");
    }

    public void teleport(Player[] players, Location[] locs){
        new BukkitRunnable(){
            @Override
            public void run(){
                players[0].teleport(locs[1]);
                players[1].teleport(locs[0]);
            }
        }.runTaskLater(plugin, 1);
    }
}
