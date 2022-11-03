package com.eu.habbo.jarvis.eventloader;

import com.eu.habbo.Emulator;
import com.eu.habbo.jarvis.Jarvis2;
import com.eu.habbo.jarvis.emulator.EmulatorLoaded;
import com.eu.habbo.jarvis.emulator.EmulatorShutdown;
import com.eu.habbo.jarvis.staff.StaffUsesCommands;
import com.eu.habbo.jarvis.user.UserBuysFromCatalogue;
import com.eu.habbo.jarvis.user.UserLogsInAndOut;
import com.eu.habbo.jarvis.user.UserTakesPhoto;
import com.eu.habbo.jarvis.user.UserUsesCommands;
import com.eu.habbo.plugin.EventListener;



public class LoadEvents implements EventListener {
    public static void loadEvents() {
        //credits to Layne
        try {
            Emulator.getPluginManager().registerEvents(Jarvis2.INSTANCE, new EmulatorLoaded());
            Emulator.getPluginManager().registerEvents(Jarvis2.INSTANCE, new EmulatorShutdown());
            Emulator.getPluginManager().registerEvents(Jarvis2.INSTANCE, new UserLogsInAndOut());
            Emulator.getPluginManager().registerEvents(Jarvis2.INSTANCE, new UserUsesCommands());
            Emulator.getPluginManager().registerEvents(Jarvis2.INSTANCE, new StaffUsesCommands());
            Emulator.getPluginManager().registerEvents(Jarvis2.INSTANCE, new UserBuysFromCatalogue());
            Emulator.getPluginManager().registerEvents(Jarvis2.INSTANCE, new UserTakesPhoto());
        } catch (Exception ex) {
            System.out.println("[ PLUGIN ] " + "Jarvis 2 has failed to register all of it's events! Submit this as a bug report on our git!");
        }
    }


}