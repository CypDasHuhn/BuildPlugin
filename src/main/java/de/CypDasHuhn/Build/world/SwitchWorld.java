package de.CypDasHuhn.Build.world;

import de.CypDasHuhn.Build.message.Message;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SwitchWorld {
    public static void switchWorld(Player player) {
        Message.send(player,"welcome_message");
        Location location = player.getLocation();
        if (location.getWorld().getName().equals(SetWorld.worldName)) {
            location.setWorld(Bukkit.getWorld("world"));
        } else {
            location.setWorld(Bukkit.getWorld(SetWorld.worldName));
        }

        player.teleport(location);
    }
}
