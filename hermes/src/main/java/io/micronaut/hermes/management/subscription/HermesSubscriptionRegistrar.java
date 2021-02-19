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
package io.micronaut.hermes.management.subscription;

import io.micronaut.hermes.management.client.HermesManagementClient;
import io.micronaut.hermes.management.configuration.HermesManagementConfiguration;
import io.micronaut.hermes.management.configuration.SubscriptionConfiguration;
import io.micronaut.hermes.management.exceptions.HermesSubscriptionException;
import io.micronaut.hermes.management.query.ApiQuery;
import io.micronaut.hermes.management.subscription.api.CreateSubscriptionCommand;
import io.micronaut.hermes.management.subscription.api.OwnerId;
import io.micronaut.context.annotation.Requires;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.core.util.StringUtils;
import io.micronaut.discovery.event.ServiceReadyEvent;
import io.micronaut.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;
import java.util.List;

import static io.micronaut.http.HttpStatus.NOT_FOUND;

@Singleton
@Requires(beans = { HermesManagementConfiguration.class, HermesManagementClient.class })
@Requires(property = HermesManagementConfiguration.PREFIX + ".enabled",
        value = StringUtils.TRUE, defaultValue = StringUtils.FALSE)
public class HermesSubscriptionRegistrar implements ApplicationEventListener<ServiceReadyEvent> {
    private static final Logger LOG = LoggerFactory.getLogger(HermesSubscriptionRegistrar.class);

    private final HermesManagementClient hermesManagementClient;
    private final HermesManagementConfiguration hermesManagementConfiguration;

    public HermesSubscriptionRegistrar(HermesManagementClient hermesManagementClient,
                                       HermesManagementConfiguration hermesManagementConfiguration) {
        this.hermesManagementClient = hermesManagementClient;
        this.hermesManagementConfiguration = hermesManagementConfiguration;
    }

    @Override
    public void onApplicationEvent(ServiceReadyEvent event) {
        LOG.info("Ensuring hermes subscription on topics:\n {}",
                hermesManagementConfiguration.getTopicsFormatted());
        ensureSubscriptionOnTopics(hermesManagementConfiguration.getSubscriptions());
    }

    private void ensureSubscriptionOnTopics(List<SubscriptionConfiguration> subscriptions) {
        for (SubscriptionConfiguration subscription : subscriptions) {
            ensureSubscriptionOnTopic(subscription);
        }
    }

    private void ensureSubscriptionOnTopic(SubscriptionConfiguration subscription) {
        String topicName = subscription.getTopic();
        HttpResponse<?> topicDetailsResponse = hermesManagementClient.getTopic(topicName);
        if (topicDetailsResponse.status() == NOT_FOUND) {
            throw new HermesSubscriptionException("Topic \"" + topicName + "\" not found!");
        }
        String subscriptionName = subscription.getName();
        if (!hasSubscriptions(subscription)) {
            LOG.info("No subscription found for \"{}\" on topic \"{}\". Creating new subscription...",
                    subscriptionName, topicName);
            HttpResponse<?> response = hermesManagementClient.createSubscription(
                    topicName, newSubscriptionCommand(subscription));
            LOG.info("Subscription response: {}", response.status());
        } else {
            LOG.info("Updating subscription \"{}\" on topic \"{}\"", subscriptionName, topicName);
            HttpResponse<?> response = hermesManagementClient.updateSubscription(topicName,
                    subscriptionName, newSubscriptionCommand(subscription));
            LOG.info("Subscription response: {}", response.status());
        }
    }

    private boolean hasSubscriptions(SubscriptionConfiguration subscription) {
        ApiQuery query = new ApiQuery().withQuery("name", subscription.getName());
        List<String> subscriptions = hermesManagementClient.querySubscriptions(subscription.getTopic(), query);
        return !subscriptions.isEmpty();
    }

    private static CreateSubscriptionCommand newSubscriptionCommand(SubscriptionConfiguration subscription) {
        return new CreateSubscriptionCommand(
                subscription.getTopic(),
                subscription.getName(),
                subscription.getDescription(),
                subscription.getEndpoint(),
                new OwnerId(subscription.getOwner().getSource(), subscription.getOwner().getId())
        );
    }
}
