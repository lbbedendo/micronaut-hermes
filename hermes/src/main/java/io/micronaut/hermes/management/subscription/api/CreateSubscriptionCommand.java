/*
 * Copyright 2017-2021 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
