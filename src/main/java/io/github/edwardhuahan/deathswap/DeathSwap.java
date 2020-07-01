package io.github.edwardhuahan.deathswap;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathSwap extends JavaPlugin {

    private DeathSwap plugin;
    private Game mainRunner;

    public void onEnable() {
        plugin = this;
        mainRunner = new Game(plugin);
        this.getCommand("startswap").setExecutor(new CommandStartSwap(plugin));
        this.getCommand("stopswap").setExecutor(new CommandStopSwap(plugin));
        getLogger().info("Deathswap is working");
    }

    public void onDisable() {
        getLogger().info("Deathswap has been stopped!");
    }

    public Game getGame() {
        return mainRunner;
    }
}
