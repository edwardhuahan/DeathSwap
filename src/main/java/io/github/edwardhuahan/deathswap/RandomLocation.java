package io.github.edwardhuahan.deathswap;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

public class RandomLocation {

    private static int radius = 400;
    public static Location getRandomLocation(Player player) {
        World world = player.getWorld();
        Location center = world.getSpawnLocation().add(0.5,0,0.5);

        Random rand = new Random();
        Location randLocation = center.add(rand.nextInt(radius*2)-radius, 0, rand.nextInt(radius*2)-radius);
        randLocation.setY(world.getHighestBlockAt(randLocation).getY());

        return randLocation;
    }
}
