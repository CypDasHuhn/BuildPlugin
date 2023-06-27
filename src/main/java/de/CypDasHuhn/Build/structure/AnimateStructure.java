package de.CypDasHuhn.Build.structure;

import de.CypDasHuhn.Build.CustomFiles;
import de.CypDasHuhn.Build.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class AnimateStructure {
    private static String currentName;
    private static Location currentLocation;
    public static void animate(String name, Location targetLocation) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration sConfig = customFiles[0].gfc(name, "structures");

        String type = sConfig.getString("type");
        int frame = 0;

        switch (type) {
            case "standing":
                LoadStructure.load(name, 0, targetLocation);
                break;
            case "animated":
                currentName = name;
                currentLocation = targetLocation;
                frame = sConfig.getInt("frame");
                clock(frame,10);
                break;
            case "move":

                break;
            case "both":

                break;
        }
    }

    public static void clock(int frameAmount, int length) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable() {
            int frame = 0;
            @Override
            public void run() {
                LoadStructure.load(currentName, frame, currentLocation);
                if (frame == frameAmount) {
                    Bukkit.getScheduler().cancelTasks(Main.getPlugin());
                }
                frame++;
            }
        },0,length);
    }
}
