package com.mycompany.helloworld;

import com.mycompany.helloworld.health.ConfigHealthCheck;
import com.mycompany.helloworld.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "helloworld";
    }

    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/app"));
    }

    @Override
    public void run(HelloWorldConfiguration configuration, Environment environment) {

        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getDefaultMessage()
        );

        final ConfigHealthCheck healthCheck;
        healthCheck = new ConfigHealthCheck(configuration.getDefaultMessage());

        environment.healthChecks().register("defaultMessage", healthCheck);
        environment.jersey().register(resource);
    }

}
