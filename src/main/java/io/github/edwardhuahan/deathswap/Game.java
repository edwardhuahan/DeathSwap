package io.github.edwardhuahan.deathswap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Game {

    private final JavaPlugin plugin;

    private int timer;
    private int delay;
    private int delayRange;
    private int warntime;

    private boolean running;
    private boolean firstRun;

    private ArrayList<Player> players;
    private ArrayList<Location> locations;

    private Random rand;

    public Game(JavaPlugin plugin) {
        this.plugin = plugin;
        firstRun = true;
        delayRange = 10;
        rand = new Random();

        locations = new ArrayList<>();
    }

    public void reset() {
        timer = 0;
        warntime = 10;
        delay = 300 + rand.nextInt(delayRange*2)-delayRange;
        running = true;
        players = null;
    }

    public void startGame(ArrayList<Player> players) {
        reset();

        this.players = players;
        if (firstRun) {
            runGame();
        }
    }

    private void runGame() {
        new BukkitRunnable() {

            @Override
            public void run() {
                timer++;

                if (running) {
                    if ((delay-timer)<=warntime) {
                        plugin.getServer().broadcastMessage(ChatColor.BLUE + "Swapping in " + (delay-timer) + " seconds");
                    }
                    if (delay-timer<=0) {
                        locations.clear();

                        Collections.shuffle(players);

                        for (Player p : players) {
                            locations.add(p.getLocation());
                        }

                        reset();
                        teleport(players, locations);
                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 20);
        firstRun = false;
    }

    public void stopGame() {
        reset();
        running = false;
    }

    private void teleport(ArrayList<Player> players, ArrayList<Location> locs){
        new BukkitRunnable(){
            @Override
            public void run(){
                for (int i = 0; i < players.size(); i++) {
                    if (i >= players.size()-1) {
                        players.get(i).teleport(locs.get(0));
                    } else {
                        players.get(i).teleport(locs.get(i+1));
                    }
                }
            }
        }.runTaskLater(plugin, 1);
    }

    public void removePlayer(Player player) {
        if (players != null) {
            players.remove(player);
        }
    }

}
