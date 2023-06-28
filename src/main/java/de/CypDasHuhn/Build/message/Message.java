package de.CypDasHuhn.Build.message;

import org.bukkit.entity.Player;

import java.util.ResourceBundle;

public class Message {
    public static void send(Player player, String message) {
        ResourceBundle messagesBundle = ResourceBundle.getBundle("messages", Language.getLocale(player));
        String localizedMessage = messagesBundle.getString(message);
        player.sendMessage(localizedMessage);
    }
}
