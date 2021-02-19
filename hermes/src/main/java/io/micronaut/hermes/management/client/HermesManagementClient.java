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
package io.micronaut.hermes.management.client;

import io.micronaut.hermes.management.query.ApiQuery;
import io.micronaut.hermes.management.subscription.api.CreateSubscriptionCommand;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Put;
import io.micronaut.http.client.annotation.Client;

import javax.annotation.Nullable;
import javax.validation.Valid;
import java.util.List;

@Client(id = HermesManagementConfiguration.ID)
public interface HermesManagementClient {
    @Get("/groups")
    List<String> listGroups();

    @Get("/topics/{groupName}")
    List<String> listTopics(String groupName);

    @Get("/topics/{topicName}")
    HttpResponse<?> getTopic(String topicName);

    @Get("/topics/{topicName}/subscriptions")
    List<String> listSubscriptions(String topicName);

    @Get("/topics/{topicName}/subscriptions{?tracked}")
    List<String> listSubscriptions(String topicName, @Nullable Boolean tracked);

    @Post("/topics/{topicName}/subscriptions/query")
    List<String> querySubscriptions(String topicName, @Body ApiQuery apiQuery);

    @Post("/topics/{topicName}/subscriptions")
    HttpResponse<?> createSubscription(String topicName,
                                       @Valid @Body CreateSubscriptionCommand createSubscriptionCommandCommand);

    @Put("/topics/{topicName}/subscriptions/{subscriptionName}")
    HttpResponse<?> updateSubscription(String topicName,
                                       String subscriptionName,
                                       @Valid @Body CreateSubscriptionCommand createSubscriptionCommandCommand);
}
