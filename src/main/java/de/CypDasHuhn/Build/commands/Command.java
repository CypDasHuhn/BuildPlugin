package de.CypDasHuhn.Build.commands;

import de.CypDasHuhn.Build.CustomFiles;
import de.CypDasHuhn.Build.structure.LoadStructure;
import de.CypDasHuhn.Build.structure.RegisterStructure;
import de.CypDasHuhn.Build.structure.SaveStructure;
import de.CypDasHuhn.Build.world.SetStructure;
import de.CypDasHuhn.Build.world.SetWorld;
import de.CypDasHuhn.Build.world.SwitchWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static java.lang.Integer.parseInt;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        World world = getWorld(sender);
        switch (label) {
            case "bs":
            case "buildsave":
                SaveStructure.command(args,world);
                break;
            case "bl":
            case "buildload":
                LoadStructure.command(args, world);
                break;
            case "sw":
            case "switchworld":
                SwitchWorld.switchWorld((Player) sender);
                break;
            case "setstructure":

                break;
            case "test":
                CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
                FileConfiguration config = customFiles[0].gfc("Test", "test/weiterTest");
                config.set("Hallo","Lol");
                customFiles[0].save();

                break;
            default:
                Bukkit.broadcastMessage(label);
                break;
        }
        return false;
    }

    public static World getWorld(CommandSender sender) {
        if (sender instanceof Player) return ((Player) sender).getWorld();
        else if (sender instanceof BlockCommandSender) return ((BlockCommandSender) sender).getBlock().getWorld();
        return null;
    }
}
