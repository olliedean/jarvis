package com.eu.habbo.jarvis.eventloader;
import com.eu.habbo.Emulator;
import com.eu.habbo.jarvis.Jarvis2;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.emulator.EmulatorLoadedEvent;
import java.util.Scanner;

public class InstallJarvis implements EventListener {
    public static void onFirstStart() {
        Emulator.getPluginManager().registerEvents(Jarvis2.INSTANCE, new InstallJarvis());
    }

    @EventHandler
    public static void InstallJarvisSystem(EmulatorLoadedEvent event) {
        System.out.println("[ PLUGIN ] " + "This is your first time running Jarvis 2! Press Enter to install Jarvis!");
        Scanner scanner = new Scanner(System.in);
        String readString = scanner.nextLine();
        if (readString.equals("")) {
            // Register Texts
            Emulator.getTexts().register("discord.logging.emustartstop.text.1", "Arcturus Morningstar was just started on your server at **%time%** and Jarvis 2 is enabled!");
            Emulator.getTexts().register("discord.logging.emustartstop.text.2", "Arcturus Morningstar was just shutdown at ***%time%*** if you did not authorize this shutdown, investigate it asap. Goodbye!");
            Emulator.getTexts().register("discord.logging.userloginandout.text.1", "**%username%** has logged into the client, their IP address is: **%ip%** and they logged in at **%time%**");
            Emulator.getTexts().register("discord.logging.userloginandout.text.2", "**%username%** has logged out of the client at **%time%**");
            Emulator.getTexts().register("discord.logging.staffcommands.text.1", "%rank% **%username%** just issued this command:\n");
            Emulator.getTexts().register("discord.logging.userscommands.text.1", "%rank% **%username%** just issued this command:\n");
            Emulator.getTexts().register("discord.logging.userbuyscatalogue.text.1", "***%username%*** just purchased %amount% of %item% from the %page% page in the catalogue.");
            Emulator.getTexts().register("discord.logging.usertakesphoto.text.1", "***%username%*** just purchased a photo!");
            Emulator.getTexts().register("discord.logging.usertakesphoto.text.2", "What a beautiful photo!");
            Emulator.getTexts().register("discord.logging.usertakesphoto.text.3", "Photo Information:");
            Emulator.getTexts().register("discord.logging.usertakesphoto.text.4", "Photo Taken In: %roomname%");
            Emulator.getTexts().register("", "");

            //Register The Webhook configs
            Emulator.getConfig().register("discord.logging.startstopurl", "ADD YOUR DISCORD WEBHOOK URL HERE!");
            Emulator.getConfig().register("discord.logging.userloginandouturl", "ADD YOUR DISCORD WEBHOOK URL HERE!");
            Emulator.getConfig().register("discord.logging.usercommandsurl", "ADD YOUR DISCORD WEBHOOK URL HERE!");
            Emulator.getConfig().register("discord.logging.staffcommandsurl", "ADD YOUR DISCORD WEBHOOK URL HERE!");
            Emulator.getConfig().register("discord.logging.userphotourl", "ADD YOUR DISCORD WEBHOOK URL HERE!");
            Emulator.getConfig().register("discord.logging.userbuyscatalogueurl", "ADD YOUR DISCORD WEBHOOK URL HERE!");

            // Register the configs for if a log is enabled. Default is 1.
            Emulator.getConfig().register("discord.logging.emustartstop", "1");
            Emulator.getConfig().register("discord.logging.usercommands", "1");
            Emulator.getConfig().register("discord.logging.staffcommands", "1");
            Emulator.getConfig().register("discord.logging.loginandout", "1");
            Emulator.getConfig().register("discord.logging.userbuyscatalogue", "1");
            Emulator.getConfig().register("discord.logging.usertakesphotos", "1");

            // Set it as installed.
            Emulator.getConfig().register("jarvis_installed", "1");
            System.out.println("[ PLUGIN ] " + "Okay! The Database columns exist! Do you need help with configuring Jarvis and setting up your webhooks? If so type yes! Otherwise just press enter...");
            String input = scanner.nextLine();
            if ("yes".equals(input)) {
                JarvisWebHookSetup.go();

            }
            }
        }
}



