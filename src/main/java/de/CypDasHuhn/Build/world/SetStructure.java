package de.CypDasHuhn.Build.world;

import org.bukkit.Location;
import org.bukkit.block.Block;

public class SetStructure {
    public static void set(Location targetLocation, Location originLocation, int[] size) {
        for (int x = 0; x <= size[0]; x++) {
            for (int y = 0; y <= size[1]; y++) {
                for (int z = 0; z <= size[2]; z++) {
                    Block currentTargetBlock = targetLocation.clone().add(x, y, z).getBlock();
                    Block currentOriginBlock = originLocation.clone().add(x, y, z).getBlock();
                    currentTargetBlock.setType(currentOriginBlock.getType());
                    currentTargetBlock.setBlockData(currentOriginBlock.getBlockData());
                }
            }
        }
    }
}
