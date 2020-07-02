package io.github.edwardhuahan.deathswap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DeathListener implements Listener {

    private Game game;

    public DeathListener(Game game) {
        this.game = game;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        game.removePlayer(event.getEntity());
    }
}
