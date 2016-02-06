package me.quasindro.atmention;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public class Settings {

    private static FileConfiguration config = AtMention.getPlugin().getConfig();

    public static final ChatColor CHAT_COLOR = ChatColor.valueOf(config.getString("mention-color"));
    public static final boolean BOLD = config.getBoolean("mention-bold");
    public static final boolean ITALIC = config.getBoolean("mention-italic");
    public static final boolean UNDERLINE = config.getBoolean("mention-underline");

    public static final boolean SUGGEST_COMMAND = config.getBoolean("mention-suggest-command");
    public static final String HOVER_DESCRIPTION = fancify(config.getString("mention-hover-description"));//"&7Click here to &b@Mention &e%mention%&7!");

    public static final Sound PLING_SOUND = Sound.valueOf(config.getString("mention-sound"));

    private static String fancify(String original) {
        return ChatColor.translateAlternateColorCodes('&', original);
    }
}
