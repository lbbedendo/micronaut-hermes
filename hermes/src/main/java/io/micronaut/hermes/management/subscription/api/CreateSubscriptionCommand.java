package io.micronaut.hermes.management.subscription.api;

import io.micronaut.core.annotation.Introspected;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Introspected
public class CreateSubscriptionCommand {
    @NotNull
    private final String topicName;

    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9_.-]+")
    private final String name;

    @NotNull
    private final String description;

    @NotNull
    private final String endpoint;

    @Valid
    @NotNull
    private final OwnerId owner;

    public CreateSubscriptionCommand(@NotNull String topicName,
                                     @NotEmpty @Pattern(regexp = "[a-zA-Z0-9_.-]+") String name,
                                     @NotNull String description,
                                     @NotNull String endpoint,
                                     @Valid @NotNull OwnerId owner) {
        this.topicName = topicName;
        this.name = name;
        this.description = description;
        this.endpoint = endpoint;
        this.owner = owner;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public OwnerId getOwner() {
        return owner;
    }
}
