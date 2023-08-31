package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import de.CypDasHuhn.Build.commands.Command;
import de.CypDasHuhn.Build.main.Main;
import de.CypDasHuhn.Build.message.Message;
import de.CypDasHuhn.Build.world.SetPlates;
import de.CypDasHuhn.Build.world.SetStructure;
import de.CypDasHuhn.Build.world.SetWorld;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.StructureBlock;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.structure.Structure;
import org.bukkit.structure.StructureManager;

import static java.lang.Integer.parseInt;

public class SaveStructure {
    public static void save(String name, int frame, Location cornerA, CommandSender sender, Location cornerB) {
        if (cornerB == null) cornerB = LoadStructure.getCorner(name);
        // Get the StructureManager instance
        StructureManager structureManager = Bukkit.getServer().getStructureManager();
        NamespacedKey key = new NamespacedKey(Main.getPlugin,"BuildPlugin_"+name+"_"+frame)

        // Create a new structure
        Structure structure = structureManager.createStructure();
        structure.fill(cornerA, cornerB, false);
        // STRUCTURE BLOCK SETTING
        structureManager.RegisterStructure(key, structure);
        structureManager.SaveStructure(key, structure);
    
    }
}
