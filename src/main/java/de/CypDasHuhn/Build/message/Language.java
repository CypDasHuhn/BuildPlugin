package de.CypDasHuhn.Build.message;

import de.CypDasHuhn.Build.CustomFiles;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Locale;

public class Language {
    public static void set(Player player, String language) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration playerConfig = customFiles[0].gfc("PlayerData", "");
        playerConfig.set("Players." + player.getUniqueId() + ".language", language);
        customFiles[0].save();
    }

    public static String getLanguage(Player player) {
        CustomFiles[] customFiles = CustomFiles.getCustomFiles(1);
        FileConfiguration playerConfig = customFiles[0].gfc("PlayerData", "");
        String language = playerConfig.getString("Players." + player.getUniqueId() + ".language");
        if (language == null) {
            set(player, "en");
            language = "en";
        }
        return language;
    }

    public static Locale getLocale(Player player) {
        return Locale.forLanguageTag(getLanguage(player));
    }
}
