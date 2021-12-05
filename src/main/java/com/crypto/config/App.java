package com.crypto.config;

import com.crypto.rest.CryptoRest;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<BasicConfiguration> {

    public static void main(final String[] args) throws Exception {
        new App().run("server", "config.yml");
    }

    @Override
    public void run(final BasicConfiguration basicConfiguration, final Environment environment) {
        environment
          .jersey()
          .register(new CryptoRest(basicConfiguration.getSecret()));

    }

    @Override
    public void initialize(final Bootstrap<BasicConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }

}
