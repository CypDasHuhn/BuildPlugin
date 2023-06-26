package de.CypDasHuhn.Build.world;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;

import java.util.Collections;

import static java.lang.Integer.parseInt;

public class SetStructure {
    public static void setStructure(Player player, String[] args) {
        Block targetBlock = player.getTargetBlock(null, 5);
        Location targetLocation = targetBlock.getLocation();
        World targetWorld = targetLocation.getWorld();

        int xM = parseInt(args[0]);
        int yM = parseInt(args[1]);
        int zM = parseInt(args[2]);

        for (int x = 0; x <= xM; x++) {
            for (int y = 0; y <= yM; y++) {
                for (int z = 0; z <= zM; z++) {
                    Location location = new Location(targetWorld,
                            targetLocation.getX() + x,
                            targetLocation.getY() + y,
                            targetLocation.getZ() + z);

                    Block block = location.getBlock();
                    setBlock(block, Bukkit.getWorld(SetWorld.worldName));
                }
            }
        }

    }

    public static void setBlock(Block targetBlock, World world) {
        Location location = targetBlock.getLocation();
        location.setWorld(world);
        location.getBlock().setType(targetBlock.getType());
        location.getBlock().setBlockData(targetBlock.getBlockData());
    }
}
