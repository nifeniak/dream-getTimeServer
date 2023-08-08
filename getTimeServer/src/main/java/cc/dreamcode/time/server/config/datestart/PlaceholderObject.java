package cc.dreamcode.time.server.config.datestart;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
public @Data class PlaceholderObject extends OkaeriConfig {

    private final String key;

    private final String start, end;
}
