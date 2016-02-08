package me.quasindro.atmention;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

public class Settings {

    private static FileConfiguration config = AtMention.getPlugin().getConfig();

    public static boolean USES_VAULT = true;

    public static final ChatColor CHAT_COLOR = chatColor();
    public static final boolean BOLD = config.getBoolean("mention-bold");
    public static final boolean ITALIC = config.getBoolean("mention-italic");
    public static final boolean UNDERLINE = config.getBoolean("mention-underline");

    public static final boolean SUGGEST_COMMAND = config.getBoolean("mention-suggest-command");
    public static final String HOVER_DESCRIPTION = fancify(config.getString("mention-hover-description"));//"&7Click here to &b@Mention &e%mention%&7!");

    public static final Sound PLING_SOUND = sound();

    private static String fancify(String original) {
        return ChatColor.translateAlternateColorCodes('&', original);
    }

    private static ChatColor chatColor() {
        ChatColor cc;
        try {
            cc = ChatColor.valueOf(config.getString("mention-color"));
        } catch (IllegalArgumentException exception) {
            AtMention.getPlugin().getLogger().severe("Config.yml is configured improperly! Defaulting to AQUA color.");
            cc = ChatColor.AQUA;
        }
        return cc;
    }

    private static Sound sound() {
        Sound sound;
        try {
            sound = Sound.valueOf(config.getString("mention-sound"));
        } catch (IllegalArgumentException exception) {
            AtMention.getPlugin().getLogger().severe("Config.yml is configured improperly! Defaulting to NOTE_PLING sound.");
            sound = Sound.NOTE_PLING;
        }
        return sound;
    }
}
