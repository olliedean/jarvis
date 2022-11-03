package com.eu.habbo.jarvis.staff;

import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.users.UserCommandEvent;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.eu.habbo.Emulator.getIntUnixTimestamp;
import static com.eu.habbo.jarvis.Jarvis2.jarvisLogo;
import static com.eu.habbo.jarvis.Jarvis2.jarvisName;


public class StaffEntersRoom implements EventListener {
    private static String userStaffCommandsUrl = Emulator.getConfig().getValue("discord.logging.staffcommandsurl");
    private static String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(getIntUnixTimestamp() * 1000L));
    @EventHandler
    public static void onStaffCommands(UserCommandEvent event) {
        if (Emulator.getConfig().getInt("discord.logging.staffcommands") == 1) {
            if (event.succes && event.habbo.hasPermission("acc_supporttool")) {

                String message = Emulator.getTexts().getValue("discord.logging.staffcommands.text.1").replace("%username%", event.habbo.getHabboInfo().getUsername()).replace("%rank%", event.habbo.getHabboInfo().getRank().getName()) + "" + ":";
                for (String s : event.args) {
                    message += s + " ";
                }
                TemmieWebhook temmie = new TemmieWebhook(userStaffCommandsUrl);
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
