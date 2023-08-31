package de.CypDasHuhn.Build.main;

import de.CypDasHuhn.Build.commands.Command;
import de.CypDasHuhn.Build.commands.TabComp;
import de.CypDasHuhn.Build.world.SetWorld;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.structure.StructureManager;

public class Main extends JavaPlugin {
    private static Main plugin;
    private static StructureManager structureManager;

    public void onEnable(){
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        plugin = this;
        structureManager = getServer().getStructureManager();

        String[] commands = {"test","setstructure","buildsave","switchworld","buildload"};
        for (String a : commands) {
            getCommand(a).setExecutor(new Command());
            getCommand(a).setTabCompleter(new TabComp());
        }

        if (!SetWorld.generated()) SetWorld.generate();
    }

    public static Main getPlugin(){
        return plugin;
    }
    public static StructureManager getStructureManager(){
        return structureManager;
    }

}
