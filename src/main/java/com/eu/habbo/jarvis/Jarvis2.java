package com.eu.habbo.jarvis;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.users.Habbo;
import com.eu.habbo.jarvis.eventloader.InstallJarvis;
import com.eu.habbo.jarvis.eventloader.LoadEvents;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.HabboPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.eu.habbo.Emulator.*;

/* Jarvis 2
   The Discord logging tool for all of your needs!
   Created by Harmonic exclusively for Krews.org
   Fixed by olliedean
 */
public class Jarvis2 extends HabboPlugin implements EventListener {

    public static final Logger LOGGER = LoggerFactory.getLogger(Jarvis2.class);
    public static Jarvis2 INSTANCE = null;
    public static String jarvisLogo  = Emulator.getConfig().getValue("discord.logging.jarvis.logourl"); // Jarvis Logo URL
    public static String jarvisName  = Emulator.getConfig().getValue("discord.logging.jarvis.botusername"); // Jarvis Bot Username

    @Override
    public void onEnable() {
        INSTANCE = this;
        Emulator.getPluginManager().registerEvents(this, this);
        Emulator.getPluginManager().registerEvents(this, new LoadEvents()); // Loads the events from the LoadEvents Class
        LoadEvents.loadEvents();

        // checks if jarvis is installed, if not calls the installer system.
        if (!Emulator.getConfig().getBoolean("jarvis_installed")) {
            InstallJarvis.onFirstStart();
        }

        // Idiot check that checks people have actually setup their webhooks.
        if (Emulator.getConfig().getValue("discord.logging.startstopurl").equals("ADD YOUR DISCORD WEBHOOK URL HERE!")) {
            LOGGER.error("JARVIS HAS DETECTED THAT YOU HAVE NOT CONFIGURED YOUR WEBHOOKS CORRECTLY. PLEASE RE-CHECK YOUR EMULATOR_CONFIG TABLE!");
        }

        if (Emulator.isReady) {
            this.checkDatabase();
        }
    }


    @Override
    public void onDisable() {
    }

    @Override
    public boolean hasPermission(Habbo habbo, String s) {
        return false;
    }

    public void checkDatabase() {
        boolean reloadPermissions = false;
    }

    public static void main(String[] args)
    {
        System.out.println("Don't run this separately");
    }
}