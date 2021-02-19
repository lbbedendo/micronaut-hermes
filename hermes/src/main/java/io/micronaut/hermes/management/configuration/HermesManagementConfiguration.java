package io.micronaut.hermes.management.configuration;

import io.micronaut.context.annotation.ConfigurationProperties;
import io.micronaut.core.util.Toggleable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ConfigurationProperties(HermesManagementConfiguration.PREFIX)
public class HermesManagementConfiguration implements Toggleable {

    public static final String PREFIX = "hermes.management";

    private List<SubscriptionConfiguration> subscriptions;
    private boolean enabled;

    public List<SubscriptionConfiguration> getSubscriptions() {
        return new ArrayList<>(subscriptions);
    }

    public void setSubscriptions(List<SubscriptionConfiguration> subscriptions) {
        this.subscriptions = new ArrayList<>(subscriptions);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getTopicsFormatted() {
        return subscriptions.stream()
                .map(SubscriptionConfiguration::getTopic)
                .collect(Collectors.joining("\n"));
    }
}
