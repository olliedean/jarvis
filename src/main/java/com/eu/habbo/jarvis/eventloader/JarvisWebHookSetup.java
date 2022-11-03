package com.eu.habbo.jarvis.eventloader;
import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.EventListener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class JarvisWebHookSetup implements EventListener {
    public static void  go() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("[ PLUGIN ] " + "---------------------------------");
        System.out.println("[ PLUGIN ] " + "Nice! Jarvis has installed to your database! Now it's time to add your discord webhooks! Make sure you've made all of the channels required on your discord, and have the webhook URL's to hand!");
        System.out.println("[ PLUGIN ] " + "---------------------------------");
        System.out.println("[ PLUGIN ] " + "Enter your Discord webhook URL for the Emulator Startup and Shutdown logs.");
        String startstopurl = scanner.nextLine();
        System.out.println("[ PLUGIN ] " + "Enter your Discord webhook URL for the User Login/ Logout Logs");
        String userloginandouturl = scanner.nextLine();
        System.out.println("[ PLUGIN ] " + "Enter your Discord webhook URL for the User Commands Logs");
        String usercommandsurl = scanner.nextLine();
        System.out.println("[ PLUGIN ] " + "Enter your Discord webhook URL for the Staff Commands Logs");
        String staffcommandsurl = scanner.nextLine();
        System.out.println("[ PLUGIN ] " + "Enter your Discord webhook URL for the User Catalogue Buying Logs");
        String userbuyscatalogueurl = scanner.nextLine();
        System.out.println("[ PLUGIN ] " + "Enter your Discord webhook URL for the Photograph Buying Logs");
        String userphotourl = scanner.nextLine();
        System.out.println("[ PLUGIN ] " + "Okay! All Done!");
        // it's 2am ill finish this bit tomorrow. night night.
          try (Connection connection = Emulator.getDatabase().getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE `emulator_settings` SET `value` = ? WHERE `key` = 'discord.logging.startstopurl'")) {
              statement.setString(1, startstopurl);
              statement.execute();
          } catch (SQLException e) {
             System.out.println(e);
         }
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE `emulator_settings` SET `value` = ? WHERE `key` = 'discord.logging.userloginandouturl'")) {
            statement.setString(1, userloginandouturl);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE `emulator_settings` SET `value` = ? WHERE `key` = 'discord.logging.usercommandsurl'")) {
            statement.setString(1, usercommandsurl);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE `emulator_settings` SET `value` = ? WHERE `key` = 'discord.logging.usercommandsurl'")) {
            statement.setString(1, usercommandsurl);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE `emulator_settings` SET `value` = ? WHERE `key` = 'discord.logging.staffcommandsurl'")) {
            statement.setString(1, staffcommandsurl);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE `emulator_settings` SET `value` = ? WHERE `key` = 'discord.logging.userbuyscatalogueurl'")) {
            statement.setString(1, userbuyscatalogueurl);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
        try (Connection connection = Emulator.getDatabase().getDataSource().getConnection(); PreparedStatement statement = connection.prepareStatement("UPDATE `emulator_settings` SET `value` = ? WHERE `key` = 'discord.logging.userphotourl'")) {
            statement.setString(1, userphotourl);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }

        Emulator.getConfig().reload();
    }
}