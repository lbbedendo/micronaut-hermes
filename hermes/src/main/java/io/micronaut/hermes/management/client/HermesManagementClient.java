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
