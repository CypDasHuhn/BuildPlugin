package de.CypDasHuhn.Build.world;

import de.CypDasHuhn.Build.CustomFiles;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Objects;

public class SetWorld {
    public static String worldName = "StructureSpace";
    public static void setWorld() {
        WorldCreator worldCreator = new WorldCreator(worldName).type(WorldType.FLAT);
        worldCreator.generateStructures(false);
        Bukkit.createWorld(worldCreator);
        World world = Objects.requireNonNull(Bukkit.getWorld(worldName));
        world.setGameRule(GameRule.DO_MOB_SPAWNING, false);
    }

    public static boolean worldGenerated() {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration config = customFiles[0].gfc("Data","");
        if (!config.getBoolean(worldName+"Generated")) {
            config.set(worldName+"Generated","true");
            customFiles[0].save();
            return false;
        } return true;
    }
}
