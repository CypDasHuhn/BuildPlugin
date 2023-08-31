package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import de.CypDasHuhn.Build.commands.Command;
import de.CypDasHuhn.Build.message.Message;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import static java.lang.Integer.parseInt;

public class RegisterStructure {
    public static void register(String[] args, CommandSender sender) {
        String name = args[0];

        World world = Command.getWorld(sender);

        Location cornerA = new Location(world, parseInt(args[1]), parseInt(args[2]), parseInt(args[3]));
        Location cornerB = new Location(world, parseInt(args[4]), parseInt(args[5]), parseInt(args[6]));

        if (illegalSize(cornerA,cornerB)) {
            Message.send(sender, "to_large");
        }

        CustomFiles[] customFiles = CustomFiles.getCustomFiles(2);
        FileConfiguration sConfig = customFiles[0].gfc(name, "structures");
        FileConfiguration dConfig = customFiles[1].gfc("StructureData", "");

        int amount = dConfig.getInt("amount");

        dConfig.set("amount", amount + 1);
        dConfig.set("list." + amount, name);

        sConfig.set("ID", amount + 1);
        sConfig.set("type", "standing");

        CustomFiles.saveArray(customFiles);

        SaveStructure.save(name, 0, cornerA, sender, cornerB);
    }

    public static boolean illegalSize(Location cornerA, Location cornerB) {
        return (
                Math.abs(cornerA.getBlockX()-cornerB.getBlockX()) > 48 ||
                        Math.abs(cornerA.getBlockY()-cornerB.getBlockY()) > 48 ||
                        Math.abs(cornerA.getBlockZ()-cornerB.getBlockZ()) > 48
        );
    }
}

