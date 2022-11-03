package com.eu.habbo.jarvis.user;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.users.UserCommandEvent;
import com.eu.habbo.plugin.events.users.UserDisconnectEvent;
import com.eu.habbo.plugin.events.users.UserLoginEvent;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.eu.habbo.Emulator.getIntUnixTimestamp;
import static com.eu.habbo.jarvis.Jarvis2.jarvisLogo;
import static com.eu.habbo.jarvis.Jarvis2.jarvisName;


public class UserUsesCommands implements EventListener {
    private static String userCommandsUrl = Emulator.getConfig().getValue("discord.logging.usercommandsurl");
    private static String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(getIntUnixTimestamp() * 1000L));
    @EventHandler
    public static void onUserCommands(UserCommandEvent event) {
        if (Emulator.getConfig().getInt("discord.logging.staffcommands") == 1) {
            if (event.succes && !event.habbo.hasPermission("acc_supporttool")) {

                String message = Emulator.getTexts().getValue("discord.logging.userscommands.text.1").replace("%username%", event.habbo.getHabboInfo().getUsername()).replace("%rank%", event.habbo.getHabboInfo().getRank().getName()) + "" + ":";
                for (String s : event.args) {
                    message += s + " ";
                }
                TemmieWebhook temmie = new TemmieWebhook(userCommandsUrl);
                DiscordMessage dm = DiscordMessage.builder()
                        .username(jarvisName)
                        .content(message)
                        .avatarUrl(jarvisLogo)
                        .build();
                temmie.sendMessage(dm);
            }
        }
    }
}
