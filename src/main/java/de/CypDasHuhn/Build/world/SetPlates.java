package de.CypDasHuhn.Build.world;

import de.CypDasHuhn.Build.CustomFiles;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class SetPlates {
    public static void generate(String name, int frame) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration sConfig = customFiles[0].gfc(name,"structures");

        int xCoord = sConfig.getInt("ID")*16;
        int yCoord = (frame % 22) * 17 + 1;
        int zCoord = (int)(Math.floor(frame / 22) * 16);

        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/fill "+xCoord+" "+yCoord+" "+zCoord+" "+(xCoord+16)+" "+yCoord+" "+(zCoord+16)+" bedrock");
    }
}
