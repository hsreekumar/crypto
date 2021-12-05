package com.crypto.config;

import com.crypto.rest.CryptoRest;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<CryptoConfiguration> {

    public static void main(final String[] args) throws Exception {
        new App().run("server", "config.yml");
    }

    @Override
    public void run(final CryptoConfiguration cryptoConfiguration, final Environment environment) {
        environment
          .jersey()
          .register(new CryptoRest(cryptoConfiguration.getSecret(), cryptoConfiguration.getIv()));

    }

    @Override
    public void initialize(final Bootstrap<CryptoConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

}
