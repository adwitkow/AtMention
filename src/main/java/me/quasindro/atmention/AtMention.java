package me.quasindro.atmention;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class AtMention extends JavaPlugin {

    private Chat chat = null;

    @Override
    public void onEnable() {
        setupConfig();

        if (!setupVault() ) {
            getLogger().severe("No Vault dependency found! Using default settings.");
            Settings.USES_VAULT = false;
        }

        Bukkit.getPluginManager().registerEvents(new AsyncChatListener(this), this);
    }

    public static Plugin getPlugin() {
        return Bukkit.getPluginManager().getPlugin("AtMention");
    }

    public Chat getChat() {
        return chat;
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

    private boolean setupVault() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
}
