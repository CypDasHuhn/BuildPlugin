package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import de.CypDasHuhn.Build.world.SetPlates;
import de.CypDasHuhn.Build.world.SetStructure;
import de.CypDasHuhn.Build.world.SetWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class SaveStructure {
    public static void save(String name, int frame, Location originLocation) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration sConfig = customFiles[0].gfc(name, "structures");

        int structureId = sConfig.getInt("ID");
        int sizeX = sConfig.getInt("size.x");
        int sizeY = sConfig.getInt("size.y");
        int sizeZ = sConfig.getInt("size.z");
        int[] size = {sizeX,sizeY,sizeZ};

        if (sConfig.getInt("frame") < frame) {
            for (int i = sConfig.getInt("frame")+1; i < frame; i++) {
                SetPlates.generate(name,i);
            }
            sConfig.set("frame",frame);
            customFiles[0].save();
        }

        int xCoord = structureId*16;
        int yCoord = (frame % 22) * 17 + 1;
        int zCoord = (int)(Math.floor(frame / 22) * 16);
        Location targetLocation = new Location(Bukkit.getWorld(SetWorld.worldName),xCoord,yCoord,zCoord);

        SetStructure.set(targetLocation,originLocation,size);
    }
}
