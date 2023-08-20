package cc.dreamcode.time.server.config.datestart;

import eu.okaeri.configs.schema.GenericsDeclaration;
import eu.okaeri.configs.serdes.DeserializationData;
import eu.okaeri.configs.serdes.ObjectSerializer;
import eu.okaeri.configs.serdes.SerializationData;
import org.jetbrains.annotations.NotNull;

public class PlaceholderObjectSerdes implements ObjectSerializer<PlaceholderObject> {

    @Override
    public boolean supports(@NotNull Class<? super PlaceholderObject> type) {
        return PlaceholderObject.class.isAssignableFrom(type);
    }

    @Override
    public void serialize(@NotNull PlaceholderObject object, @NotNull SerializationData data, @NotNull GenericsDeclaration generics) {
        data.add("key", object.getKey());
        data.add("start", object.getStart());
        data.add("end", object.getEnd());
    }

    @Override
    public PlaceholderObject deserialize(@NotNull DeserializationData data, @NotNull GenericsDeclaration generics) {
        return new PlaceholderObject(
                data.get("key", String.class),
                data.get("start", String.class),
                data.get("end-settings", String.class)
        );
    }
}
