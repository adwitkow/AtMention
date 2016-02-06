package me.quasindro.atmention;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class AtMention extends JavaPlugin {

    @Override
    public void onEnable() {
        setupConfig();
        Bukkit.getPluginManager().registerEvents(new AsyncChatListener(), this);
    }

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("AtMention");
    }

    private void setupConfig() {
        saveDefaultConfig();

        getConfig().addDefault("mention-color", "AQUA");
        getConfig().addDefault("mention-bold", false);
        getConfig().addDefault("mention-italic", true);
        getConfig().addDefault("mention-underline", false);
        getConfig().addDefault("mention-suggest-command", true);
        getConfig().addDefault("mention-hover-description", "&7Click here to &b@Mention &e%mention%&7!");
        getConfig().addDefault("mention-sound", "NOTE_PLING");
    }
}
