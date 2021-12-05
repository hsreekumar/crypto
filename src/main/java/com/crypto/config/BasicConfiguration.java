package com.crypto.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class BasicConfiguration extends Configuration {
    @NotNull private final String secret;

    @JsonCreator
    public BasicConfiguration(@JsonProperty("defaultSize") final String secret) {
        this.secret = secret;
    }

    public String getSecret() {
        return secret;
    }
}
