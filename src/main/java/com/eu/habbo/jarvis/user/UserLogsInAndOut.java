package com.eu.habbo.jarvis.user;

import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
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


public class UserLogsInAndOut implements EventListener {
    private static String userLogsInandOutURL = Emulator.getConfig().getValue("discord.logging.userloginandouturl");
    private static String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(getIntUnixTimestamp() * 1000L));
    @EventHandler
    public static void onUserLoginEvent(UserLoginEvent event) throws IOException {
        if (Emulator.getConfig().getInt("discord.logging.loginandout") == 1) {
            TemmieWebhook temmie = new TemmieWebhook(userLogsInandOutURL);
            DiscordMessage dm = DiscordMessage.builder()
                    .username("Jarvis 2")
                    .content(Emulator.getTexts().getValue("discord.logging.userloginandout.text.1").replace("%username%", event.habbo.getHabboInfo().getUsername()).replace("%ip%", event.habbo.getHabboInfo().getIpLogin()).replace("%time%", date))
                    .avatarUrl("https://vignette.wikia.nocookie.net/marvelcinematicuniverse/images/b/b0/JuARaVeInSy.png/revision/latest?cb=20120722164138")
                    .build();
            temmie.sendMessage(dm);
        }
    }

    @EventHandler
    public static void onUserLogoutEvent(UserDisconnectEvent event) throws IOException {
        if (Emulator.getConfig().getInt("discord.logging.loginandout") == 1) {
            TemmieWebhook temmie = new TemmieWebhook(userLogsInandOutURL);
            DiscordMessage dm = DiscordMessage.builder()
                    .username(jarvisName)
                    .content(Emulator.getTexts().getValue("discord.logging.userloginandout.text.2").replace("%username%", event.habbo.getHabboInfo().getUsername()).replace("%ip%", event.habbo.getHabboInfo().getIpLogin()).replace("%time%", date))
                    .avatarUrl(jarvisLogo)
                    .build();
            temmie.sendMessage(dm);
        }
    }
}
