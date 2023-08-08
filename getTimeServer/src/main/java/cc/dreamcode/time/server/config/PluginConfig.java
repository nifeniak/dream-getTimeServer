package cc.dreamcode.time.server.config;

import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import cc.dreamcode.time.server.util.Pair;
import cc.dreamcode.utilities.builder.MapBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;

import java.util.Map;

@Configuration(
        child = "config.yml"
)
@Header("## getTimeServer (Main-Config) ##")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfig extends OkaeriConfig {


    @Comment("Debug pokazuje dodatkowe informacje do konsoli. Lepiej wylaczyc. :P")
    public boolean debug = true;

    @Comment({
            "Jako pierwszy argument podajesz nazwe serwera, a jako drugi podajesz kiedy odbędzie się start a trzeci to kiedy się skończy edycja. :D",
            "Dostępne placeholdery to: ",
            " - \"edition_end_<nazwa_serwera>\" - Zwraca kiedy edycja się skończy.",
            " - \"edition_start_<nazwa_serwera>\" - Zwraca kiedy edycja się zacznie."
    })
    public Map<String, Pair<String, String>> serverDateMap = new MapBuilder<String, Pair<String, String>>()
            .put("survival", new Pair<>("10/09/2023 18:00:00", "11/09/2023 18:00:00"))
            .put("earthsmp", new Pair<>("11/11/2023 18:00:00", "11/12/2023 18:00:00"))
            .build();
}
