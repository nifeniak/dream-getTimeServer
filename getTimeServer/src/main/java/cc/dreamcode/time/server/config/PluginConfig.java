package cc.dreamcode.time.server.config;

import cc.dreamcode.platform.bukkit.component.configuration.Configuration;
import cc.dreamcode.utilities.builder.MapBuilder;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.annotation.*;

import java.util.Map;

@Configuration(
        child = "config.yml"
)
@Header("## Dream-Template (Main-Config) ##")
@Names(strategy = NameStrategy.HYPHEN_CASE, modifier = NameModifier.TO_LOWER_CASE)
public class PluginConfig extends OkaeriConfig {

    @Comment("Debug pokazuje dodatkowe informacje do konsoli. Lepiej wylaczyc. :P")
    public boolean debug = true;

    @Comment("Jako pierwszy argument podajesz nazwe serwera, a jako drugi podajesz kiedy odbędzie się start. :D")
    public Map<String, String> serverDateMap = new MapBuilder<String, String>()
            .put("survival", "10/09/2023 18:00:00")
            .put("earthsmp", "12/12/2023 18:00:00")
            .build();
}
