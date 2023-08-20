package cc.dreamcode.time.server.config.command;

import cc.dreamcode.command.annotations.RequiredPermission;
import cc.dreamcode.command.bukkit.BukkitCommand;
import cc.dreamcode.time.server.config.MessageConfig;
import cc.dreamcode.time.server.config.PluginConfig;
import eu.okaeri.injector.annotation.Inject;
import lombok.NonNull;
import org.bukkit.command.CommandSender;

import java.util.List;

@RequiredPermission(permission = "time.server.command.reload")
public class ReloadConfigCommand extends BukkitCommand {

    private @Inject PluginConfig pluginConfig;
    private @Inject MessageConfig messageConfig;

    public ReloadConfigCommand() {
        super("timeserverreloadconfig");
    }

    @Override
    public void content(@NonNull CommandSender sender, @NonNull String[] args) {
        this.pluginConfig.load();
        this.messageConfig.load();
        this.messageConfig.successfullyReloaded.send(sender);
    }

    @Override
    public List<String> tab(@NonNull CommandSender sender, @NonNull String[] args) {
        return null;
    }
}
