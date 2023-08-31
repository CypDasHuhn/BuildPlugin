package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import de.CypDasHuhn.Build.commands.Command;
import de.CypDasHuhn.Build.world.SetPlates;
import de.CypDasHuhn.Build.world.SetStructure;
import de.CypDasHuhn.Build.world.SetWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

import static java.lang.Integer.parseInt;

public class LoadStructure {
    public static boolean structureRegistered(String name) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration config = customFiles[0].gfc(name, "structures");
        return config.getString("type") != null;
    }

    public static String command(String[] args, CommandSender sender) {
        World world = Command.getWorld(sender);
        if (structureRegistered(args[0])) {
            if (args.length == 5) {
                load(args[0], parseInt(args[4]), new Location(world, parseInt(args[1]), parseInt(args[2]), parseInt(args[3])));
                return "load_bl";
            } else {
                AnimateStructure.animate(args[0], new Location(world, parseInt(args[1]), parseInt(args[2]), parseInt(args[3])));
                return "animate_bl";
            }
        }
        return "unregistered_bl";
    }

    public static void load(String name, int frame, Location targetLocation) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration sConfig = customFiles[0].gfc(name, "structures");

        int structureId = sConfig.getInt("ID");
        int sizeX = sConfig.getInt("size.x");
        int sizeY = sConfig.getInt("size.y");
        int sizeZ = sConfig.getInt("size.z");
        int[] size = {sizeX, sizeY, sizeZ};

        int xCoord = structureId * 16;
        int yCoord = (frame % 22) * 17 + 1 - 64;
        int zCoord = (int) (Math.floor(frame / 22) * 16);

        Location originLocation = new Location(Bukkit.getWorld(SetWorld.worldName), xCoord, yCoord, zCoord);
        SetStructure.set(targetLocation, originLocation, size);
    }

    public static int nextFrame(String name) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration sConfig = customFiles[0].gfc(name, "structures");
        return sConfig.getInt("frame") + 1;
    }

    public static int[] getSize(String name) {
        return null;
    }
}
