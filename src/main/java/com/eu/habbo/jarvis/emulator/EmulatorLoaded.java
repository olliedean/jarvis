package com.eu.habbo.jarvis.emulator;

import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent;
import com.eu.habbo.plugin.events.users.UserCommandEvent;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.eu.habbo.Emulator.*;
import static com.eu.habbo.Emulator.version;
import static com.eu.habbo.jarvis.Jarvis2.*;


public class EmulatorLoaded implements EventListener {
    @EventHandler
    public static void onEmulatorLoaded(EmulatorLoadedEvent event) {
        String emuStartStopUrl = Emulator.getConfig().getValue("discord.logging.startstopurl");
        String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(getIntUnixTimestamp() * 1000L));

        if (Emulator.getConfig().getBoolean("jarvis_installed")) {
            LOGGER.info("Jarvis 2 Has successfully hooked to " + version); // Sends a message to the emulator saying it's loaded
        }
        INSTANCE.checkDatabase();
        if (Emulator.getConfig().getInt("discord.logging.emustartstop") == 1) { // if logging for starting and stopping the emulator is enabled
            TemmieWebhook temmie = new TemmieWebhook(emuStartStopUrl);
            DiscordMessage dm = DiscordMessage.builder()
                    .username(jarvisName)
                    .content(Emulator.getTexts().getValue("discord.logging.emustartstop.text.1").replace("%time%", date) + "")
                    .avatarUrl(jarvisLogo)
                    .build();
            temmie.sendMessage(dm);
        }
    }
}
