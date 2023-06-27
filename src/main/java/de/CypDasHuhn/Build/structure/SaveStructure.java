package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import de.CypDasHuhn.Build.world.SetPlates;
import de.CypDasHuhn.Build.world.SetStructure;
import de.CypDasHuhn.Build.world.SetWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

import static java.lang.Integer.parseInt;

public class SaveStructure {
    public static void command(String[] args, World world) {
        if (args.length < 4) return;
        if (!LoadStructure.structureRegistered(args[0])) {
            if (args.length < 7) return;
            RegisterStructure.register(args);
        }
        int frame = 0;
        if (args.length == 5) {
            if (args[4].equals("+")) args[4] = String.valueOf(LoadStructure.nextFrame(args[0]));
            frame = parseInt(args[4]);
        }
        SaveStructure.save(args[0], frame, new Location(world, parseInt(args[1]), parseInt(args[2]), parseInt(args[3])));
    }

    public static void save(String name, int frame, Location originLocation) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration sConfig = customFiles[0].gfc(name, "structures");

        int structureId = sConfig.getInt("ID");
        int sizeX = sConfig.getInt("size.x");
        int sizeY = sConfig.getInt("size.y");
        int sizeZ = sConfig.getInt("size.z");
        int[] size = {sizeX, sizeY, sizeZ};

        if (sConfig.getInt("frame") < frame) {
            for (int i = sConfig.getInt("frame") + 1; i < frame; i++) {
                SetPlates.generate(name, i);
            }
            sConfig.set("frame", frame);
            if (sConfig.getString("type").equals("standing")) sConfig.set("type", "animated");
            customFiles[0].save();
        }

        int xCoord = structureId * 16; //16 = chunk width
        int yCoord = (frame % 22) * 17 - 63; //22 = amount of 17th's in a chunk, 17 = height of a structure, -63 = build height starting after bedrock
        int zCoord = (int) (Math.floor(frame / 22) * 16);
        Location targetLocation = new Location(Bukkit.getWorld(SetWorld.worldName), xCoord, yCoord, zCoord);

        SetStructure.set(targetLocation, originLocation, size);
    }
}
