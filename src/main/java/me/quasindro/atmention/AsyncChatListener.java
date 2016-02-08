package me.quasindro.atmention;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class AsyncChatListener implements Listener {

    private AtMention plugin;

    public AsyncChatListener(AtMention plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMentionChat(AsyncPlayerChatEvent e) {

        if (!(e.getMessage().contains("@"))) {
            return;
        }

        String message = e.getMessage();
        String front = message.substring(0, message.indexOf("@"));
        String name = message.substring(message.indexOf("@") + 1, message.length());

        int mentionedNameSpaceIndex = name.contains(" ") ? name.indexOf(" ") : name.length();
        String back = name.substring(mentionedNameSpaceIndex, name.length());
        name = name.substring(0, mentionedNameSpaceIndex);

        String[] mentionedArray = name.split("(?=[^a-zA-Z0-9])");

        Player player = Bukkit.getPlayer(mentionedArray[0]);

        if (player == null) {
            return;
        }

        player.playSound(player.getLocation(), Settings.PLING_SOUND, 10.0F, 1.0F);

        e.setCancelled(true);

        StringBuilder extras = new StringBuilder();
        for (String s : mentionedArray) {
            if (s.equals(mentionedArray[0])) {
                continue;
            }
            extras.append(s);
        }

        name = player.getName();
        back = extras + back;

        BaseComponent[] mentionedBase = new ComponentBuilder("@" + name)
                .color(Settings.CHAT_COLOR)
                .bold(Settings.BOLD)
                .underlined(Settings.UNDERLINE)
                .italic(Settings.ITALIC)
                .create();
        TextComponent mentionedName = new TextComponent(mentionedBase);

        if (Settings.SUGGEST_COMMAND) {
            mentionedName.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "@" + name));
            mentionedName.setHoverEvent(
                    new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                    new ComponentBuilder(Settings.HOVER_DESCRIPTION.replace("%mention%", name)).create())
            );
        }

        String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getChat().getPlayerPrefix(e.getPlayer()));

        TextComponent format = new TextComponent(e.getFormat().substring(0, e.getFormat().indexOf("%2$s")).replace("%1$s", prefix + e.getPlayer().getDisplayName()));

        TextComponent finalMessage = new TextComponent(
                format,
                // TODO SETUP HOVER/CLICK EVENT FOR NAME at least if I want people to use my plugin as an ultimate chat tool or sth
                new TextComponent(TextComponent.fromLegacyText(front)),
                mentionedName,
                new TextComponent(TextComponent.fromLegacyText(back))
        );

        for (Player pl : e.getRecipients()) {
            pl.spigot().sendMessage(finalMessage);
        }
        System.out.println(finalMessage.toPlainText());
    }
}
