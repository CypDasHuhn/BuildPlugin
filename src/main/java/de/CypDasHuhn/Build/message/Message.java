package de.CypDasHuhn.Build.message;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ResourceBundle;

public class Message {
    public static void send(Player player, String message) {
        if (message == null) return;
        ResourceBundle messagesBundle = ResourceBundle.getBundle("messages", Language.getLocale(player));
        String localizedMessage = messagesBundle.getString(message);
        player.sendMessage(localizedMessage);
    }

    public static void send(CommandSender sender, String message) {
        if ((!(sender instanceof Player)) || message == null) return;
        Player player = (Player) sender;
        ResourceBundle messagesBundle = ResourceBundle.getBundle("messages", Language.getLocale(player));
        String localizedMessage = messagesBundle.getString(message);
        player.sendMessage(localizedMessage);
    }
}
