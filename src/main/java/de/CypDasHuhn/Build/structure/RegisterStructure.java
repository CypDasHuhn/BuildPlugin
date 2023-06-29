package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import static java.lang.Integer.parseInt;

public class RegisterStructure {
    public static String register(String[] args) {
        String name = args[0];
        int sizeX = Math.abs(parseInt(args[1]) - parseInt(args[2]));
        int sizeY = Math.abs(parseInt(args[3]) - parseInt(args[5]));
        int sizeZ = Math.abs(parseInt(args[5]) - parseInt(args[6]));
        if (sizeX > 16 || sizeY > 16 || sizeZ > 16) return "to_large_bs";

        CustomFiles[] customFiles = CustomFiles.getCustomFiles(2);
        FileConfiguration sConfig = customFiles[0].gfc(name, "structures");
        FileConfiguration dConfig = customFiles[1].gfc("StructureData", "");

        int amount = dConfig.getInt("amount");

        dConfig.set("amount", amount + 1);
        dConfig.set("list." + amount, name);

        sConfig.set("ID", amount + 1);
        sConfig.set("type", "standing");
        sConfig.set("size.x", sizeX);
        sConfig.set("size.y", sizeY);
        sConfig.set("size.z", sizeZ);

        CustomFiles.saveArray(customFiles);
        return null;
    }
}
