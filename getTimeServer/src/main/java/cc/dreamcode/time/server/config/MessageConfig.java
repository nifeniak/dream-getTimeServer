package cc.dreamcode.time.server.config;

import cc.dreamcode.notice.minecraft.MinecraftNoticeType;
import cc.dreamcode.notice.minecraft.bukkit.BukkitNotice;
import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;

@Configuration(
        child = "message.yml"
)
@Headers({
        @Header("## dream-getTimeServer (Message-Config) ##"),
        @Header("Dostepne type: (DO_NOT_SEND, CHAT, ACTION_BAR, SUBTITLE, TITLE, TITLE_SUBTITLE)")
})
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class MessageConfig extends OkaeriConfig {


    public BukkitNotice noPermission = new BukkitNotice(MinecraftNoticeType.CHAT, "&cNie posiadasz uprawnien. &8(&4{PERM}&8)");
    public BukkitNotice successfullyReloaded = new BukkitNotice(MinecraftNoticeType.CHAT, "&aPomyślnie przeładowano!");

    @Comment("Tekst który pojawia się przed czasem jeśli edycja jeszcze nie wystartowała:")
    public String in = "za {TIME}";
    @Comment("Tekst który pojawia się przed czasem jeśli edycja juz wystartowała:")
    public String from = "od {TIME}";

    @Comment("Jak serwer nie zostanie znaleziony:")
    public String noInformation = "Brak informacji";
    @Comment("Jak edycja się skończyła:")
    public String ended = "Edycja się skończyła od {TIME}";
}
