package cc.dreamcode.time.server.hook;

import cc.dreamcode.time.server.config.MessageConfig;
import cc.dreamcode.time.server.config.PluginConfig;
import cc.dreamcode.time.server.util.DateUtil;
import cc.dreamcode.time.server.util.Pair;
import cc.dreamcode.utilities.TimeUtil;
import cc.dreamcode.utilities.bukkit.ChatUtil;
import eu.okaeri.injector.annotation.Inject;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class EditionStartPlaceholder extends PlaceholderExpansion {


    private @Inject PluginConfig pluginConfig;
    private @Inject MessageConfig messageConfig;

    @NotNull
    public String getIdentifier() {
        return "edition_start";
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
        for (Map.Entry<String, Pair<String, String>> serverDateEntry : this.pluginConfig.serverDateMap.entrySet()) {
            if (!params.toLowerCase().contains(serverDateEntry.getKey().toLowerCase())) {
                continue;
            }

            long timeFromDate = DateUtil.timeFromDate(serverDateEntry.getValue().getKey());

            return System.currentTimeMillis() > timeFromDate ? (ChatUtil.fixColor(this.messageConfig.from.replace("{TIME}", TimeUtil.convertMills(System.currentTimeMillis() - timeFromDate))))
                    : (ChatUtil.fixColor(this.messageConfig.in.replace("{TIME}", TimeUtil.convertMills((timeFromDate - System.currentTimeMillis())))));

        }

        return "Brak informacji";
    }
}
