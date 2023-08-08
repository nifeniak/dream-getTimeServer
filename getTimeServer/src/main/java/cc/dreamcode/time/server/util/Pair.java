package cc.dreamcode.time.server.util;

import lombok.Data;

public @Data class Pair<K, V> {

    private final K key;
    private final V value;


}