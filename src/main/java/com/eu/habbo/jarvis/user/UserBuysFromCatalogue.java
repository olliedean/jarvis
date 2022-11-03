package com.eu.habbo.jarvis.user;

import com.eu.habbo.Emulator;
import com.eu.habbo.habbohotel.catalog.CatalogPage;
import com.eu.habbo.messages.incoming.catalog.CatalogBuyItemEvent;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.users.UserCommandEvent;
import com.eu.habbo.plugin.events.users.catalog.UserCatalogFurnitureBoughtEvent;
import com.eu.habbo.plugin.events.users.catalog.UserCatalogItemPurchasedEvent;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.eu.habbo.Emulator.getIntUnixTimestamp;
import static com.eu.habbo.jarvis.Jarvis2.jarvisLogo;
import static com.eu.habbo.jarvis.Jarvis2.jarvisName;


public class UserBuysFromCatalogue implements EventListener {
    private static String userBuyCatalogueUrl = Emulator.getConfig().getValue("discord.logging.userbuyscatalogueurl");
    private static String date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date(getIntUnixTimestamp() * 1000L));
    @EventHandler
    public static void onUserBuysFromCatalogue(UserCatalogFurnitureBoughtEvent event) {
        if (Emulator.getConfig().getInt("discord.logging.userbuyscatalogue") == 1) {
          //  if (event.habbo.getHabboInfo().getRank().getId() > 5) {
                CatalogPage page = Emulator.getGameEnvironment().getCatalogManager().getCatalogPage(event.catalogItem.getPageId());

               // if (page != null && page.getRank() > 5) {
                    TemmieWebhook temmie = new TemmieWebhook(userBuyCatalogueUrl);
                    DiscordMessage dm = DiscordMessage.builder()
                            .username(jarvisName)
                            .content(Emulator.getTexts().getValue("discord.logging.userbuyscatalogue.text.1").replace("%username%", event.habbo.getHabboInfo().getUsername()).replace("%item%", event.catalogItem.getName()).replace("%amount%", String.valueOf(event.furniture.size())).replace("%page%", page.getCaption()))
                            .avatarUrl(jarvisLogo)
                            .build();
                    temmie.sendMessage(dm);
                }
            }
        }
   // }
//}
