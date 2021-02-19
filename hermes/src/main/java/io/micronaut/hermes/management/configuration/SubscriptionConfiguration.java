package io.micronaut.hermes.management.configuration;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Introspected
public class SubscriptionConfiguration {
    @NotEmpty
    private String topic;
    @NotEmpty
    private String endpoint;
    @NotEmpty
    private String name;
    @NotEmpty
    private String description;
    @NotNull
    private OwnerConfiguration owner;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OwnerConfiguration getOwner() {
        return owner;
    }

    public void setOwner(OwnerConfiguration owner) {
        this.owner = owner;
    }
}
