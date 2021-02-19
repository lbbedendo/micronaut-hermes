package io.micronaut.hermes.management.configuration;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotEmpty;

@Introspected
public class OwnerConfiguration {
    @NotEmpty
    private String source;
    @NotEmpty
    private String id;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
