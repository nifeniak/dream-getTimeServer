package cc.dreamcode.time.server;

import cc.dreamcode.command.bukkit.BukkitCommandProvider;
import cc.dreamcode.notice.minecraft.bukkit.serdes.BukkitNoticeSerdes;
import cc.dreamcode.platform.DreamVersion;
import cc.dreamcode.platform.bukkit.DreamBukkitConfig;
import cc.dreamcode.platform.bukkit.DreamBukkitPlatform;
import cc.dreamcode.platform.bukkit.component.CommandComponentResolver;
import cc.dreamcode.platform.bukkit.component.ConfigurationComponentResolver;
import cc.dreamcode.platform.bukkit.component.ListenerComponentResolver;
import cc.dreamcode.platform.bukkit.component.RunnableComponentResolver;
import cc.dreamcode.platform.component.ComponentManager;
import cc.dreamcode.time.server.config.MessageConfig;
import cc.dreamcode.time.server.config.PluginConfig;
import cc.dreamcode.time.server.config.command.ReloadConfigCommand;
import cc.dreamcode.time.server.hook.EditionEndPlaceholder;
import cc.dreamcode.time.server.hook.EditionStartPlaceholder;
import eu.okaeri.configs.serdes.OkaeriSerdesPack;
import eu.okaeri.configs.serdes.commons.SerdesCommons;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import lombok.Getter;
import lombok.NonNull;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public final class BukkitTimeServerPlugin extends DreamBukkitPlatform implements DreamBukkitConfig {

    @Getter
    private static BukkitTimeServerPlugin bukkitTimeServerPlugin;

    @Override
    public void load(@NonNull ComponentManager componentManager) {
        bukkitTimeServerPlugin = this;
    }

    @Override
    public void enable(@NonNull ComponentManager componentManager) {
        this.registerInjectable(BukkitCommandProvider.create(this, this.getInjector()));

        componentManager.registerResolver(CommandComponentResolver.class);
        componentManager.registerResolver(ListenerComponentResolver.class);
        componentManager.registerResolver(RunnableComponentResolver.class);

        componentManager.registerResolver(ConfigurationComponentResolver.class);
        componentManager.registerComponent(MessageConfig.class, messageConfig -> this.getInject(BukkitCommandProvider.class)
                .ifPresent(bukkitCommandProvider ->
                        bukkitCommandProvider.setRequiredPermissionMessage(messageConfig.noPermission.getText().replace("{PERM}", "time.server.command.reload"))));

        componentManager.registerComponent(PluginConfig.class, pluginConfig -> componentManager.setDebug(pluginConfig.debug));

        componentManager.registerComponent(ReloadConfigCommand.class);

        componentManager.registerComponent(EditionEndPlaceholder.class, PlaceholderExpansion::register);
        componentManager.registerComponent(EditionStartPlaceholder.class, PlaceholderExpansion::register);
    }

    @Override
    public void disable() {
        this.getInject(EditionEndPlaceholder.class).ifPresent(PlaceholderExpansion::unregister);
        this.getInject(EditionStartPlaceholder.class).ifPresent(PlaceholderExpansion::unregister);
    }

    @Override
    public @NonNull DreamVersion getDreamVersion() {
        return DreamVersion.create("dream-getTimeServer", "1.0", "nifeniak");
    }

    @Override
    public @NonNull OkaeriSerdesPack getConfigSerdesPack() {
        return registry -> {
            registry.register(new SerdesBukkit());
            registry.register(new SerdesCommons());
            registry.register(new BukkitNoticeSerdes());
        };
    }
}
