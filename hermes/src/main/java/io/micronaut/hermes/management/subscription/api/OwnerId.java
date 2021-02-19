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
