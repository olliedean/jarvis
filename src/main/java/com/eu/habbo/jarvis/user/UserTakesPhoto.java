package com.eu.habbo.jarvis.user;
import com.eu.habbo.Emulator;
import com.eu.habbo.plugin.EventHandler;
import com.eu.habbo.plugin.EventListener;
import com.eu.habbo.plugin.events.users.UserPurchasePictureEvent;
import com.mrpowergamerbr.temmiewebhook.DiscordEmbed;
import com.mrpowergamerbr.temmiewebhook.DiscordMessage;
import com.mrpowergamerbr.temmiewebhook.TemmieWebhook;
import com.mrpowergamerbr.temmiewebhook.embed.FieldEmbed;
import com.mrpowergamerbr.temmiewebhook.embed.ThumbnailEmbed;
import java.util.Arrays;
import static com.eu.habbo.jarvis.Jarvis2.jarvisLogo;
import static com.eu.habbo.jarvis.Jarvis2.jarvisName;


public class UserTakesPhoto implements EventListener {
    private static String userTakePhotoUrl = Emulator.getConfig().getValue("discord.logging.userphotourl");

    @EventHandler
    public static void OnUserTakePhotoEvent (UserPurchasePictureEvent event) {
        if (Emulator.getConfig().getInt("discord.logging.usertakesphotos") == 1);
        TemmieWebhook banLog = new TemmieWebhook(userTakePhotoUrl);
        DiscordEmbed de = DiscordEmbed.builder()
                .title(Emulator.getTexts().getValue("discord.logging.usertakesphoto.text.1").replace("%username%", event.habbo.getHabboInfo().getUsername()))
                .description(Emulator.getTexts().getValue("discord.logging.usertakesphoto.text.2"))
                .url(event.url)
                .thumbnail(ThumbnailEmbed.builder()
                        .url(event.url)
                        .height(320)
                        .build())
                .fields(Arrays.asList(
                        FieldEmbed.builder()
                                .name(Emulator.getTexts().getValue("discord.logging.usertakesphoto.text.3"))
                                .value(Emulator.getTexts().getValue("discord.logging.usertakesphoto.text.4").replace("%roomname%", event.habbo.getHabboInfo().getCurrentRoom().getName()))
                                .build()
                ))
                .build();

        DiscordMessage dm = DiscordMessage.builder()
                .username(jarvisName)
                .content("")
                .avatarUrl(jarvisLogo)
                .embeds(Arrays.asList(de))
                .build();

        banLog.sendMessage(dm);

    }
}