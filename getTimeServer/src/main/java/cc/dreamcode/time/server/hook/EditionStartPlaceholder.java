package cc.dreamcode.time.server.hook;

import cc.dreamcode.time.server.config.MessageConfig;
import cc.dreamcode.time.server.config.PluginConfig;
import cc.dreamcode.time.server.config.datestart.PlaceholderObject;
import cc.dreamcode.time.server.util.DateUtil;
import cc.dreamcode.utilities.TimeUtil;
import cc.dreamcode.utilities.bukkit.ChatUtil;
import eu.okaeri.injector.annotation.Inject;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class EditionStartPlaceholder extends PlaceholderExpansion {


    private @Inject PluginConfig pluginConfig;
    private @Inject MessageConfig messageConfig;

    @NotNull
    public String getIdentifier() {
        return "editionstart";
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
        Optional<PlaceholderObject> placeholderObjectOptional = this.pluginConfig.placeholderObjects
                .stream()
                .filter(placeholderObject -> placeholderObject.getKey().equalsIgnoreCase(params))
                .findFirst();

        if (!placeholderObjectOptional.isPresent()) {
            return this.messageConfig.noInformation;
        }

        long timeFromDate = DateUtil.timeFromDate(placeholderObjectOptional.get().getStart());

        return System.currentTimeMillis() > timeFromDate ? (ChatUtil.fixColor(this.messageConfig.from.replace("{TIME}", TimeUtil.convertSeconds(System.currentTimeMillis() - timeFromDate))))
                : (ChatUtil.fixColor(this.messageConfig.in.replace("{TIME}", TimeUtil.convertSeconds((timeFromDate - System.currentTimeMillis())))));
    }
}
