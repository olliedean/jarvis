package com.eu.habbo.jarvis.emulator;

import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent;
import com.eu.habbo.plugin.events.emulator.EmulatorStoppedEvent;
import com.eu.habbo.plugin.events.users.UserCommandEvent;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.eu.habbo.Emulator.*;
import static com.eu.habbo.Emulator.version;
import static com.eu.habbo.jarvis.Jarvis2.*;


public class EmulatorShutdown implements EventListener {
    @EventHandler
    public static void onEmulatorShutdown(EmulatorStoppedEvent event) {
        String emuStartStopUrl = Emulator.getConfig().getValue("discord.logging.startstopurl");
        String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(getIntUnixTimestamp() * 1000L));

        LOGGER.info("Goodbye from Jarvis 2!");
        INSTANCE.checkDatabase();
        if (Emulator.getConfig().getInt("discord.logging.emustartstop") == 1) {
            TemmieWebhook temmie = new TemmieWebhook(emuStartStopUrl);
            DiscordMessage dm = DiscordMessage.builder()
                    .username(jarvisName)
                    .content(Emulator.getTexts().getValue("discord.logging.emustartstop.text.2").replace("%time%", date) + "")
                    .avatarUrl(jarvisLogo)
                    .build();
            temmie.sendMessage(dm);
        }
    }
}
