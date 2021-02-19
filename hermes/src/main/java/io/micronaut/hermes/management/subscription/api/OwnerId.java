package io.micronaut.hermes.management.subscription.api;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotNull;

@Introspected
public class OwnerId {
    @NotNull
    private final String source;

    @NotNull
    private final String id;

    public OwnerId(@NotNull String source, @NotNull String id) {
        this.source = source;
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public String getId() {
        return id;
    }
}
