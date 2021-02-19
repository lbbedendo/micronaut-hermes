package io.micronaut.hermes.management.query;

import io.micronaut.core.annotation.Introspected;

import java.util.LinkedHashMap;
import java.util.Map;

@Introspected
public class ApiQuery {
    private Map<String, String> query;

    public ApiQuery withQuery(String key, String value) {
        query.put(key, value);
        return this;
    }

    public ApiQuery() {
        this.query = new LinkedHashMap<>();
    }

    public Map<String, String> getQuery() {
        return query;
    }
}
