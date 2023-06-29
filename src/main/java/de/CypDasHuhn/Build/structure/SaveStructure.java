package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import de.CypDasHuhn.Build.commands.Command;
import de.CypDasHuhn.Build.message.Message;
import de.CypDasHuhn.Build.world.SetPlates;
import de.CypDasHuhn.Build.world.SetStructure;
import de.CypDasHuhn.Build.world.SetWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static java.lang.Integer.parseInt;

public class SaveStructure {
    public static String command(String[] args, CommandSender sender) {
        World world = Command.getWorld(sender);
        if (args.length < 4) return "short_argument_bs";
        if (!LoadStructure.structureRegistered(args[0])) {
            if (args.length < 7) return "long_argument_bs";
            Message.send(sender, RegisterStructure.register(args));
        }
        int frame = 0;
        if (args.length == 5) {
            if (args[4].equals("+")) args[4] = String.valueOf(LoadStructure.nextFrame(args[0]));
            frame = parseInt(args[4]);
        }
        Message.send(sender,SaveStructure.save(args[0], frame, new Location(world, parseInt(args[1]), parseInt(args[2]), parseInt(args[3])),sender));
        return null;
    }

    public static String save(String name, int frame, Location originLocation, CommandSender sender) {
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
        return "succesfull_bs";
    }
}
