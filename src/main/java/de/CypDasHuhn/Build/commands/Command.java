package de.CypDasHuhn.Build.commands;

import de.CypDasHuhn.Build.structure.LoadStructure;
import de.CypDasHuhn.Build.structure.RegisterStructure;
import de.CypDasHuhn.Build.structure.SaveStructure;
import de.CypDasHuhn.Build.world.SetStructure;
import de.CypDasHuhn.Build.world.SetWorld;
import de.CypDasHuhn.Build.world.SwitchWorld;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static java.lang.Integer.parseInt;

public class Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
        switch (label) {
            case "buildsave":
                if (!LoadStructure.structureRegistered(args[0])) {

                }
                break;
            case "sw":
            case "switchworld":
                SwitchWorld.switchWorld((Player) sender);
                break;
            case "setstructure":
                RegisterStructure.register(args);
                SaveStructure.saveStructure(args[0],new Location(Bukkit.getWorld("world"),parseInt(args[4]),parseInt(args[5]),parseInt(args[6])));
                break;
            default:
                Bukkit.broadcastMessage(label);
                break;
        }
        return false;
    }
}
