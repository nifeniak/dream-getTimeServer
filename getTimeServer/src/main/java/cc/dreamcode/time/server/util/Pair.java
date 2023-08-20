package cc.dreamcode.time.server.util;

import eu.okaeri.configs.OkaeriConfig;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Pair extends OkaeriConfig {

    private String start;
    private String end;

}
