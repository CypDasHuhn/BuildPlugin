package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import static java.lang.Integer.parseInt;

public class RegisterStructure {
    public static void register(String[] args) {
        String name = args[0];
        int x1 = parseInt(args[1]);
        int y1 = parseInt(args[2]);
        int z1 = parseInt(args[3]);
        int x2 = parseInt(args[4]);
        int y2 = parseInt(args[5]);
        int z2 = parseInt(args[6]);

        CustomFiles[] customFiles = CustomFiles.getCustomFiles(2);
        FileConfiguration sConfig = customFiles[0].gfc(name,"structures");
        FileConfiguration dConfig = customFiles[1].gfc("StructureData","");

        int amount = dConfig.getInt("amount");

        dConfig.set("amount",amount+1);
        dConfig.set("list."+amount,name);

        sConfig.set("ID",amount+1);
        sConfig.set("type","standing");
        sConfig.set("size.x",Math.abs(x1-x2));
        sConfig.set("size.y",Math.abs(y1-y2));
        sConfig.set("size.z",Math.abs(z1-z2));

        CustomFiles.saveArray(customFiles);
    }
}
