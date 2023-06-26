package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import org.bukkit.configuration.file.FileConfiguration;

public class LoadStructure {
    public static boolean structureRegistered(String name) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration config = customFiles[0].gfc(name,"Structures/"+name);
        return config.getString("Type") != null;
    }
}
