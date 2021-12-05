package com.crypto.config;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

public class CryptoConfiguration extends Configuration {
    @NotNull private final String secret;
    @NotNull private final String iv;

    @JsonCreator
    public CryptoConfiguration(@JsonProperty("secret") final String secret, @JsonProperty("iv") final String iv) {
        this.secret = secret;
        this.iv = iv;
    }

    public String getSecret() {
        return secret;
    }

	public String getIv() {
		return iv;
	}
}
