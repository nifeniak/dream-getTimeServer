package cc.dreamcode.time.server.hook;

import cc.dreamcode.time.server.config.PluginConfig;
import cc.dreamcode.time.server.util.DateUtil;
import cc.dreamcode.utilities.TimeUtil;
import eu.okaeri.injector.annotation.Inject;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class DateStartPlaceholder extends PlaceholderExpansion {

    private @Inject PluginConfig pluginConfig;

    @NotNull
    public String getIdentifier() {
        return "datestart";
    }

    @NotNull
    public String getAuthor() {
        return "dreamcode";
    }

    @NotNull
    public String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        for (Map.Entry<String, String> serverDateEntry : this.pluginConfig.serverDateMap.entrySet()) {
            if (!params.toLowerCase().contains(serverDateEntry.getKey().toLowerCase())) {
                continue;
            }

            long timeFromDate = DateUtil.timeFromDate(serverDateEntry.getValue());

            return System.currentTimeMillis() > timeFromDate ? TimeUtil.convertMills(System.currentTimeMillis() - timeFromDate)
                    : ("za " + TimeUtil.convertMills((timeFromDate - System.currentTimeMillis())));

        }

        return "Brak informacji";
    }
}
