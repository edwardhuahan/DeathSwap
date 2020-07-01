package io.github.edwardhuahan.deathswap;

import org.bukkit.plugin.java.JavaPlugin;

public class DeathSwap extends JavaPlugin {
    public void onEnable() {
        this.getCommand("startswap").setExecutor(new CommandStartSwap());
        this.getCommand("stopswap").setExecutor(new CommandStopSwap());
        getLogger().info("Deathswap is working");
    }

    public void onDisable() {
        getLogger().info("Deathswap has been stopped!");
    }
}
