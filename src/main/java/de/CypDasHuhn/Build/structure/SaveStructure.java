package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import de.CypDasHuhn.Build.world.SetWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;

public class SaveStructure {
    public static void saveStructure(String name, Location location1) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration sConfig = customFiles[0].gfc(name, "");

        int structureId = sConfig.getInt("ID");
        int sizeX = sConfig.getInt("size.x");
        int sizeY = sConfig.getInt("size.y");
        int sizeZ = sConfig.getInt("size.z");

        Location location2 = new Location(location1.getWorld(), location1.getX() + sizeX, location1.getY() + sizeY, location1.getZ() + sizeZ);

        World targetWorld = Bukkit.getWorld(SetWorld.worldName);
        int xCoord = structureId * 16;
        int yCoord = -60;
        int zCoord = 0;

        for (int x = 0; x <= sizeX; x++) {
            for (int y = 0; y <= sizeY; y++) {
                for (int z = 0; z <= sizeZ; z++) {
                    Location targetLocation = new Location(targetWorld, xCoord + x, yCoord + y, zCoord + z);
                    System.out.println(targetLocation);
                    targetLocation.getBlock().setType(Material.RED_STAINED_GLASS);
                    Block targetBlock = location1.getWorld().getBlockAt(location1.getBlockX() + x, location1.getBlockY() + y, location1.getBlockZ() + z);
                    setBlock(targetLocation, targetBlock);
                }
            }
        }
    }




    public static void setBlock(Location location, Block targetBlock) {
        location.getBlock().setType(targetBlock.getType());
        location.getBlock().setBlockData(targetBlock.getBlockData());
    }
}
